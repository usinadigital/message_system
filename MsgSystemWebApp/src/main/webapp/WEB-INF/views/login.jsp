<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<%@page session="true"%>
<html>
<head>
<title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>

	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="content">
			<div class="box-login">
				<div class="box-head">
					<h2>
						<spring:message code="view.login.title" />
					</h2>
				</div>
				<br>
				<form name='loginForm'  class="form" action="<c:url value='/j_spring_security_check' />" method="POST">

					<table align="center" width="100%" border="0">
						<tr>
							<td><input type="username" class="field size100" name="username" placeholder="<spring:message code="view.login.username"/>" /></td>
						</tr>
						<tr>
							<td><input type="password" class="field size100" name="password" placeholder="<spring:message code="view.login.password"/>" /></td>
						</tr>
						<tr><td></td></tr>
						<tr><td></td></tr>
						<tr>
							<td><input name="submit"  class="button size100" type="submit" value="<spring:message code="view.login.button.submit"/>" /></td>
						</tr>
					</table>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
				
				<c:if test="${not empty error}">
					<c:set var="messageERROR" value="${error}" scope="request"/>
				</c:if>
				<c:if test="${not empty msg}">
					<c:set var="messageOK" value="${msg}" scope="request"/>
				</c:if>
				
			</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</body>
</html>
