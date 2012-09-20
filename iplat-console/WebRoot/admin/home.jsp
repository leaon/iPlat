<%--
	/**
	 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
	 */
--%>

<%@ include file="/commons/resource/page/_global.jsp" %>	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	/**
	 * Description 后台管理主页面，用户管理员进行后台系统管理。
	 *
	 * Author Leaon
	 * 
	 * Date					Author				Description			
	 * ---------------------------------------------------------------------------------------------
	 * 2012-8-18			Leaon				创建admin.jsp。
	 *
	 */
--%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    	<base href="<%=path%>" />
    	
    	<title>iPlat-Console 管理控制台-[吕洋]</title>
    	
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />       
    </head>
    <body>
    	<%@ include file="/commons/resource/page/_header.jsp" %>
    	
    	<%--
    		<pg:pager url="url" items="items"
					maxPageItems="maxPageItems" maxIndexPages="maxIndexPages"
					export="offset,currentPageNumber=pageNumber" scope="request">
				<pg:param name="method" value="method" />
				<pg:index>
					<jsp:include page="/commons/resource/page/_page.jsp" flush="true" />
				</pg:index>	
			</pg:pager>
		--%>
		管理员主页。
        <%@ include file="/commons/resource/page/_footer.jsp" %>
    </body>
</html>
