<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		
		<div>
		<c:set var="messageOK" scope="request">
			<spring:message code="view.message.info.messageSended"/>
		</c:set>
		</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
