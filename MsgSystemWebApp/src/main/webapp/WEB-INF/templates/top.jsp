<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/login?logout" var="logoutUrl" htmlEscape="true" />

<div id="top">
	<h1>
		<a href="#">Message System</a>
	</h1>
	<div id="top-navigation">Welcome
		<a href="#">
			<strong>
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="name"/>
				</sec:authorize>
			</strong>
		</a> 
		<span>|</span>
		<a href="${logoutUrl}">Logout</a>
	</div>
</div>
