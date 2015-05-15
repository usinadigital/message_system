<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<h1>Message page !</h1>
			<p>The time on the server is XXX.</p>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
