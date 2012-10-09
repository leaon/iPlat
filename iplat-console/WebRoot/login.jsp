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

<%@ include file="/commons/resource/page/_global.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=path%>" />
        
		<title>iPlat-Console</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		
		<%@ include file="/commons/resource/page/_head.jsp"%>
	</head>
	<body>
		<%@ include file="/commons/resource/page/_top.jsp"%>
		<%-- ================================================================================== --%>
		<div class="container">
			<div class="row">
				<div class="span6">
					<div class="alert alert-block">
						<h4 class="alert-heading">会话过期</h4>
						由于您长时间未访问系统，请您重新登录！ <br> <br> <br> <br> <br>
											<br> <br> <br> <br> <br>
					</div>
				</div>
				<div class="span6" style="background-image: url('<%=path %>/commons/resource/image/login.png');">
					<div class="row">
						<div class="span6">
							<h2>登录系统</h2>
						</div>
					</div>
					<div class="row">
						<div class="span1">
							<img id="photo" src="<%=path%>/commons/resource/image/photo.jpg" />
						</div>
						<div class="span5">
							<div class="row">
								<div class="span3">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-user"></i></span><input
											class="input-medium" id="username" type="text"
											placeholder="用户名" style="margin: 0;">
									</div>
								</div>
								<div class="span2">
									<label class="checkbox"><input type="checkbox"
										id="rememberme" value="0">记住密码</label>
								</div>
							</div>
							<div class="row">
								<div class="span3">
									<div class="input-prepend">
										<span class="add-on"><i class="icon-lock"></i></span> <input
											class="input-medium" id="password" type="password"
											placeholder="密码" style="margin: 0;">
									</div>
								</div>
								<div class="span2">
									<label class="checkbox"><input type="checkbox"
										id="rememberme" value="0">自动登录</label>
								</div>
							</div>
							<div class="row">
								<div class="span2">
									<label class="checkbox"><input type="checkbox"
										id="rememberme" value="0">记住密码</label>
								</div>
								<div class="span2">
									<label class="checkbox"><input type="checkbox"
										id="rememberme" value="0">自动登录</label>
								</div>
							</div>
							<div class="row">———————————————————————————————————</div>
							<div class="row">
								<div class="span1 offset4">
									<button class="btn btn-primary" type="submit">登 录</button>
								</div>
								<div class="span2 offset1">
									<button class="btn btn-success" type="button">注    册</button>
								</div>
							</div>
						</div>
					</div>
				</div>	
			</div>
		</div>

	<%-- ================================================================================== --%>
		<%@ include file="/commons/resource/page/_link.jsp"%>
		<%@ include file="/commons/resource/page/_bottom.jsp"%>
	</body>
</html>
