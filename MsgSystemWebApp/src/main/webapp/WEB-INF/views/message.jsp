<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
   
<body>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="body">
			<div class="body">
				<h1>
					<spring:message code="view.message.title" />
				</h1>
				<form:form method="POST" action="message" commandName="message">
					<table>
						<tr>
							<td><form:label path="text"><spring:message code="view.message.label.message" /></form:label></td>
							<td><form:input path="text" /></td>
							<td>${textError}</td>
						</tr>
						<tr>
							<td><form:checkboxes path="categories" items="${cats.keySet()}" element="div"/></td>
							<td>${categoriesError}</td>	
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Submit" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</body>
