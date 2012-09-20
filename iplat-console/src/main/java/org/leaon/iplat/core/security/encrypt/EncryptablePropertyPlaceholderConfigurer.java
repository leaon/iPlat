/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.encrypt;

import org.apache.log4j.Logger;
import org.jasypt.commons.CommonUtils;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 基于Jasyptde 加密参数文件解析器，扩展Spring PropertyPlaceholderConfigurer，用于Spring加载并解析加密的参数文件。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-10			Leaon				创建EncryptablePropertyPlaceholderConfigurer.java。
 *
 */
public class EncryptablePropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(EncryptablePropertyPlaceholderConfigurer.class);
	
	/**
	 * 加密器，只有一个会被实例化，另一个为NULL。  
	 */
    private final StringEncryptor stringEncryptor;    
    private final TextEncryptor textEncryptor;    
   
    /**   
     * 构造方法，初始化用于加密解密参数文件的加密器。   
     *    
     * @param stringEncryptor   
     *            基于{@link StringEncryptor}} 的加密器。   
     */   
    public EncryptablePropertyPlaceholderConfigurer(    
            final StringEncryptor stringEncryptor) {    
        super();    
        CommonUtils.validateNotNull(stringEncryptor, "加密器不能为空");    
        this.stringEncryptor = stringEncryptor;    
        this.textEncryptor = null;    
    }    
   
    /**   
     * 构造方法，初始化用于加密解密参数文件的加密器。   
     *    
     * @param textEncryptor   
     *            基于{@link TextEncryptor}} 的加密器。   
     */ 
    public EncryptablePropertyPlaceholderConfigurer(final TextEncryptor textEncryptor) {    
        super();    
        CommonUtils.validateNotNull(textEncryptor, "加密器不能为空");    
        this.stringEncryptor = null;    
        this.textEncryptor = textEncryptor;    
    }    
   
    /*   
     * (non-Javadoc)   
     *    
     * @see org.springframework.beans.factory.config.PropertyResourceConfigurer#convertPropertyValue(java.lang.String)   
     */   
    @Override   
    protected String convertPropertyValue(final String originalValue) {
        if (!PropertyValueEncryptionUtils.isEncryptedValue(originalValue)) {
            return originalValue;
        }
        if (this.stringEncryptor != null) {
            return PropertyValueEncryptionUtils.decrypt(originalValue,    
                    this.stringEncryptor);    
   
        }    
        return PropertyValueEncryptionUtils.decrypt(originalValue, this.textEncryptor);    
    }    
   
    /*   
     * (non-Javadoc)   
     *    
     * @since 1.8   
     * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#resolveSystemProperty(java.lang.String)   
     */   
    @Override   
    protected String resolveSystemProperty(final String key) {
        return convertPropertyValue(super.resolveSystemProperty(key));    
    }

}
