<%--
	/**
	 * Description 用户登录页面。
	 *
	 * Author Leaon
	 * 
	 * Date					Author				Description			
	 * ---------------------------------------------------------------------------------------------
	 * 2012-8-17			Leaon				创建login.jsp。
	 *
	 */
--%>    
   
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="commons/resource/page/_global.jsp"%>

<%
	String base = request.getContextPath();
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + base;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=path%>" />
        
		<title>iPlat-Console</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<%@ include file="commons/resource/page/_head.jsp"%>
	</head>
	<body>
		<%@ include file="/commons/resource/page/_top.jsp"%>
		<%@ include file="/commons/resource/page/_navigation.jsp"%>
		<%-- ================================================================================== --%>
		系统登陆：
		<form action="<%=path %>/sys-login.do" method="post">
			<table>
				<tr>
					<td>用户:</td>
					<td><input type='text' name='username'
						value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" /></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td><input name="reset" type="reset" /></td>
					<td><input name="submit" type="submit" /></td>
				</tr>
			</table>
		</form>
		<%--
	    	<pg:pager url="url" items="items"
					maxPageItems="maxPageItems" maxIndexPages="maxIndexPages"
					export="offset,currentPageNumber=pageNumber" scope="request">
				<pg:param name="method" value="method" />
				<pg:index>
						<jsp:include page="/commons/public/page/_page.jsp" flush="true" />
				</pg:index>	
			</pg:pager>
		--%>	
		<%-- ================================================================================== --%>
		<%@ include file="/commons/resource/page/_link.jsp"%>
		<%@ include file="/commons/resource/page/_bottom.jsp"%>
	</body>
</html>
