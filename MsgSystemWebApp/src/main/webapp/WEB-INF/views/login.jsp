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
			<div class="box">
				<div class="box-head">
					<h1><spring:message code="view.login.title" /></h1>
				</div>
				<c:if test="${not empty error}">
					<div>${error}</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div>${msg}</div>
				</c:if>

				<form name='loginForm'
					action="<c:url value='/j_spring_security_check' />" method='POST'>

					<table>
						<tr>
							<td><spring:message code="view.login.username" /></td>
							<td><input type='text' name='username'></td>
						</tr>
						<tr>
							<td><spring:message code="view.login.password" /></td>
							<td><input type='password' name='password' /></td>
						</tr>
						<tr>
							<td colspan='2'><input name="submit" type="submit"
								value="submit" /></td>
						</tr>
					</table>

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

				</form>
			</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</body>
</html>
