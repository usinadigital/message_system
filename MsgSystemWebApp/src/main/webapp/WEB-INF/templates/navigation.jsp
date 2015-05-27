<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/home" var="homeUrl" htmlEscape="true" />
<spring:url value="/message" var="messageUrl" htmlEscape="true" />
<spring:url value="/administration" var="administrationUrl" htmlEscape="true" />

<div id="navigation">
	<ul>
		<li><a href="${homeUrl}" class="active"><span>Home</span></a></li>
		<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
			<li><a href="${administrationUrl}"><span>Administration</span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_MESSAGE_SENDER')">
			<li><a href="${messageUrl}"><span>Send Message</span></a></li>
		</sec:authorize>
	</ul>
</div>
