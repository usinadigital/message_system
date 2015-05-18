<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<h1>
				<spring:message code="home.title" />
			</h1>
			<div class="prova">Prova CSS</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
