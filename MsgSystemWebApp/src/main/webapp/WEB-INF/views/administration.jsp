<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="administrationActive" value="active" scope="request"/>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="content">
		<div class="box">
			<div class="box-head">
				<h2>
					<spring:message code="view.administration.title" />
				</h2>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
