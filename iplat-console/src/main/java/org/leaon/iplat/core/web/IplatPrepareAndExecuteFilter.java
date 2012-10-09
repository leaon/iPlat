/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.web;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * TODO
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-17			Leaon				创建IplatPrepareAndExecuteFilter.java。
 *
 */
public class IplatPrepareAndExecuteFilter extends StrutsPrepareAndExecuteFilter {

	// ~ Fields ====================================================================================

	// ~ Constructors ==============================================================================

	// ~ Methods ===================================================================================

	/* (non-Javadoc)
	 * @see org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		InitParamResolver init = new InitParamResolver(); //在这里使用我们自己写的TestInitOperations类，其继承自struts2的InitOpertions   
        try {  
            FilterHostConfig config = new FilterHostConfig(filterConfig);  
            init.initLogging(config);  
            Dispatcher dispatcher = init.initDispatcher(config);  
            init.initStaticContentLoader(config, dispatcher);  
  
            prepare = new PrepareOperations(filterConfig.getServletContext(), dispatcher);  
            execute = new ExecuteOperations(filterConfig.getServletContext(), dispatcher);  
            this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);  
  
            postInit(dispatcher, filterConfig);  
        } finally {  
            init.cleanup();  
        }  
    }  

	
	// ~ Getters & Setters =========================================================================
	
}
