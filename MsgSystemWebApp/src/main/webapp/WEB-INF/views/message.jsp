<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<body>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
			<div class="body">
				<h1>
					<spring:message code="message.title" />
				</h1>
				<p>Ciao</p>
			</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</body>
