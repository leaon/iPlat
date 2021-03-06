<%--
	/**
	 * Description 系统首页面，用户登录后所显示的主页面。
	 *
	 * Author Leaon
	 * 
	 * Date					Author				Description			
	 * ---------------------------------------------------------------------------------------------
	 * 2012-8-17			Leaon				创建home.jsp。
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
		
		<%@ include file="commons/resource/page/_style.jsp"%>
		<%@ include file="commons/resource/page/_script.jsp"%>
	</head>
	<frameset rows="80,*,20" cols="*" frameborder="yes" border="1"
		framespacing="1" s>
		<frame src="/commons/resource/page/_header.jsp" name="header"
			scrolling="no" noresize="noresize" id="header" title="header" />
		<frameset cols="80,*,40" frameborder="yes" border="1" framespacing="1">
			<frame src="/commons/resource/page/_menu.jsp" name="menu"
				scrolling="auto" id="menu" title="menu" />
			<frame src="/commons/resource/page/_main.jsp" name="main" id="main"
				title="main" />
			<frame src="/commons/resource/page/_tool.jsp" name="tool" id="tool"
				title="tool" />
		</frameset>
		<frame src="commons/resource/page/_status.jsp" name="status"
			scrolling="no" id="status" title="status" />
	</frameset>
	<noframes>
		<body>
			您的浏览器不支持框架页，请使用其他浏览器！
		</body>
	</noframes>
</html>
