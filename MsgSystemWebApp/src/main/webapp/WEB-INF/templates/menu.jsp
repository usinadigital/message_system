<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="menu">
	<div class="prova">Menu</div>
	<spring:url value="/home" var="homeUrl" htmlEscape="true" />
	<a href="${homeUrl}">Home</a>
	<spring:url value="/message" var="messageUrl" htmlEscape="true" />
	<a href="${messageUrl}">Message</a>
	<spring:url value="/administration" var="administrationUrl"
		htmlEscape="true" />
	<a href="${administrationUrl}">Administration</a>
	<spring:url value="/login?logout" var="logoutUrl" htmlEscape="true" />
	<a href="${logoutUrl}">Logout</a>
	<p></p>
	<a href="?language=en_UK">English</a>|<a href="?language=pt_BR">Portuguese</a>
</div>
