/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.HostConfig;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * TODO
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-17			Leaon				创建IplatInitOperations.java。
 *
 */
public class IplatInitOperations extends InitOperations {

	// ~ Fields ====================================================================================

	// ~ Constructors ==============================================================================

	// ~ Methods ===================================================================================

	/* (non-Javadoc)
	 * @see org.apache.struts2.dispatcher.ng.InitOperations#initDispatcher(org.apache.struts2.dispatcher.ng.HostConfig)
	 */
	public Dispatcher initDispatcher( HostConfig filterConfig ) {  
        Dispatcher dispatcher = createDispatcher(filterConfig);  
        dispatcher.init();  
        return dispatcher;  
    }  
      
    private Dispatcher createDispatcher( HostConfig filterConfig ) {
        Map<String, String> params = new HashMap<String, String>();  
        for ( Iterator<String> e = filterConfig.getInitParameterNames(); e.hasNext(); ) {  
            String name = e.next();  
            String value = filterConfig.getInitParameter(name);  
            //此处增加初始化参数判断，如果是config则需要将通配符转换为实际的配置文件路径。
            params.put(name, matchConfig(name, value));    
        }  
        return new Dispatcher(filterConfig.getServletContext(), params);  
    }  
      
    /** 
     * 使用PathMatchingResourcePatternResolver匹配所有加载的文件 
     */  
    private String matchConfig(String name, String value){  
        if("config".equals(name)&&StringUtils.isNotBlank(value)){  
            PathMatchingResourcePatternResolver pattern = new PathMatchingResourcePatternResolver();  
            String[] files = value.split("\\s*[,]\\s*");  
            for (String file : files) {  
                if(StringUtils.isBlank(file)){
                	continue;  
                }
                if (pattern.getPathMatcher().isPattern(file.substring(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX.length()))) {  
                    String reg = file.substring(file.indexOf(':')+1).replace('.', '#');  
                    reg = reg.replaceAll("#", "\\\\.");  
                    reg = reg.replace('*', '#');  
                    reg = reg.replaceAll("#", ".*");  
                    reg = reg.replace('?', '#');  
                    reg = reg.replaceAll("#", ".?");  
                    reg = ".*("+reg+")$";  
                    try {  
                        Resource[] rs = pattern.getResources(file);  
                        String temp = "";  
                        for(Resource r : rs){  
                            temp += "," + r.getURL().getFile().replaceAll(reg, "$1");  
                        }  
                        if(StringUtils.isNotBlank(temp)) temp = temp.substring(1);  
                        value = value.replace(file, temp);
                    } catch (IOException e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return value;
    }  

	
	// ~ Getters & Setters =========================================================================
}
