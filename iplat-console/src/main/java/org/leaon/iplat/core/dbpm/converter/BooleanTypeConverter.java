/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.dbpm.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * TODO
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-6				Leaon				创建BooleanTypeConverter.java。
 *
 */
  
     
/**   
 * @author    
 * java中的boolean和jdbc中的char之间转换;true-Y;false-N   
 */     
public class BooleanTypeConverter implements TypeHandler {
     
    /* (non-Javadoc)   
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)   
     */     
    public Object getResult(ResultSet arg0, String arg1) throws SQLException {     
        String str = arg0.getString(arg1);     
        Boolean rt = Boolean.FALSE;     
        if (str.equalsIgnoreCase("Y")){     
            rt = Boolean.TRUE;     
        }     
        return rt;      
    }     
     
    /* (non-Javadoc)   
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)   
     */     
    public Object getResult(CallableStatement arg0, int arg1)     
            throws SQLException {     
        Boolean b = arg0.getBoolean(arg1);
        return b == true ? "Y" : "N";     
    }     
     
    /* (non-Javadoc)   
     * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)   
     */     
    public void setParameter(PreparedStatement arg0, int arg1, Object arg2,     
            JdbcType arg3) throws SQLException {
        Boolean b = (Boolean) arg2;     
        String value = (Boolean) b == true ? "Y" : "N";     
        arg0.setString(arg1, value);     
    }     
}    
