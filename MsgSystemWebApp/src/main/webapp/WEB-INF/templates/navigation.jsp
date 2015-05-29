<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="navigation">
	<ul>
		<li>
			<a href="${pageContext.servletContext.contextPath}/home" class="${homeActive}">
				<span><spring:message code="view.home.title"/></span>
			</a>
		</li>
		<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
			<li><a href="${pageContext.servletContext.contextPath}/administration" class="${administrationActive}"><span><spring:message code="view.administration.title"/></span></a></li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_MESSAGE_SENDER')">
			<li><a href="${pageContext.servletContext.contextPath}/message" class="${messageActive}"><span><spring:message code="view.message.title"/></span></a></li>
		</sec:authorize>
	</ul>
</div>
