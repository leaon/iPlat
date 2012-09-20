<%--
	/**
	 * Description 分页的页面，该页面将被用于集成在需要进行数据分页展示的JSP页面中。
	 *
	 * author Leaon
	 * 
	 * Date					author				Description			
	 * ---------------------------------------------------------------------------------------------
	 * 2012-8-18			Leaon				创建_page.jsp。
	 *
	 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=path%>" />
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	<body>
--%>
		<div id="page">
			<table border=cellpadding= cellspacing=align=right>
				<tr align=center valign=top>
					<pg:page export="first,last">
						<td>
							${first} - ${last} of ${total}
						</td>
					</pg:page>
					<pg:first export="pageUrl" unless="current">
						<td>
							<s:if test="${pageUrl!=null }">
								<a href="${pageUrl}">
									<b>
										[第一页]
									</b>
								</a>
							</s:if>
						</td>
					</pg:first>
					<pg:prev export="pageUrl" ifnull="${true }">
						<td align=right>
							<s:if test="${pageUrl!=null }">
								<a href="${pageUrl}">
									<b>
										[前一页]
									</b>
								</a>
							</s:if>
						</td>
					</pg:prev>
					<pg:pages>
						<td align=right>
							<s:if test="${pageNumber == currentPageNumber}">
								<font color=#aa>${pageNumber}&nbsp;</font>
							</s:if>
							<s:else>
								<a href="${pageUrl}">${pageNumber}&nbsp;</a>
							</s:else>
						</td>
					</pg:pages>
					<pg:next export="pageUrl" ifnull="${true }">
						<td>
							<s:if test="${pageUrl!=null }">
								<a href="${pageUrl}">
									<b>
										[下一页]
									</b>
								</a>
							</s:if>
						</td>
					</pg:next>
					<pg:last export="pageUrl" unless="current">
						<td>
							<s:if test="${pageUrl!=null }">
								<a href="${pageUrl}">
									<b>
										[最后页]
									</b>
								</a>
							</s:if>
						</td>
					</pg:last>
				</tr>
			</table>
		</div>
<%-- 		
	</body>
</html>
--%>
