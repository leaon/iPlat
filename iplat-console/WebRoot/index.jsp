<%--
	/**
	 * Description 系统首页，该页面为用户未登录时显示的页面。
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
		<base href="<%=path %>" />
        
		<title>iPlat-Console</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<%@ include file="commons/resource/page/_head.jsp"%>
	</head>
	<body>
		<%@ include file="commons/resource/page/_top.jsp"%>
		<%@ include file="commons/resource/page/_navigation.jsp"%>
		<%-- ================================================================================== --%>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span9">
					<h1>放登录提示图片！</h1>
					<div class="row">
						<div class="span12">
							Level 1 of column
							<div class="row">
								<div class="span6">Level 2</div>
								<div class="span6">Level 2</div>
							</div>
						</div>
					</div>
				</div>
				<div class="span3">
					<div class="alert alert-error">登录失败！</div>
					<form class="well">
						<div class="control-group">
							<div class="controls">
								<div class="input-prepend">
									<span class="add-on"><i class="icon-user"></i></span>
									<input class="input-large" id="username" type="text">
								</div>
								<div class="input-prepend">
									<span class="add-on"><i class="icon-lock"></i></span>
									<input class="input-large" id="password" type="password">
								</div>
								<label class="checkbox inline"> 
									<input type="checkbox" id="rememberme" value="0">
									下次自动登录
								</label>
									<a>忘记密码?</a>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span5"><button class="btn btn-primary" type="submit">登    录</button></div>
							<div class="span5"><button class="btn btn-success" type="button">注    册</button></div>
						</div>
					</form>
				</div>
			</div>
		</div>
<%-- 		<form action="<%=path %>/sys-login.do" method="post"> --%>
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td>用户:</td> -->
<!-- 					<td><input type='text' name='username' -->
<%-- 						value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" /></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>密码:</td> -->
<!-- 					<td><input type='password' name='password' /></td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td><input name="reset" type="reset" /></td> -->
<!-- 					<td><input name="submit" type="submit" /></td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</form> -->
		首页，欢迎登陆iPlat-Console。
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
		<%@ include file="commons/resource/page/_link.jsp"%>
		<%@ include file="commons/resource/page/_bottom.jsp"%>
	</body>
</html>
