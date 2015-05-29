<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="top">
	<h1>
		<a href="#">Message System</a>
	</h1>
	<sec:authorize access="isAuthenticated()">
	<div id="top-navigation">
		
		Welcome
		<strong><sec:authentication property="name"/></strong>
		<span>|</span>
		<a href="javascript:formSubmit()">Logout</a>
		
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<a href="?language=en">EN</a><span>|</span><a href="?language=pt">PT</a>
	</div>
	
	<form action="${pageContext.servletContext.contextPath}/j_spring_security_logout" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	
	</sec:authorize>
</div>
