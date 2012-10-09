<%--
	/**
	 * Description 系统首页面，用户登录后所显示的主页面。
	 *
	 * Author Leaon
	 * 
	 * Date					Author				Description			
	 * ---------------------------------------------------------------------------------------------
	 * 2012-8-17			Leaon				创建index.jsp。
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
	<body>
		<div id="wapper">
			<%@ include file="/commons/resource/page/_header.jsp"%> <%-- 头部页 --%>
		
			<%@ include file="/commons/resource/page/_nav.jsp"%>	<%-- 导航页 --%>
			
			<%@ include file="/commons/resource/page/_left.jsp"%>	<%-- 左侧页 --%>
			
			<%@ include file="/commons/resource/page/_center.jsp"%>	<%-- 主导页 --%>
			
			<%@ include file="/commons/resource/page/_tool.jsp"%>	<%-- 右侧页 --%>
			
			<%@ include file="/commons/resource/page/_status.jsp"%>	<%-- 状态页 --%>
			
			<%@ include file="/commons/resource/page/_footer.jsp"%>	<%-- 脚部页 --%>
		</div>
				
		<script type="text/javascript">							<%-- 脚本区--%>
			
		</script>
	</body>
</html>
