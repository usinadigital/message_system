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
					<table border="0">
						<tr>
							<td valign="top"><form:label path="text"><spring:message code="view.message.label.message" /></form:label></td>
							<td><form:textarea path="text" rows="5" cols="30" /></td>
							<td valign="top"><div class="formError">${textError}</div></td>
						</tr>
						<tr>
							<td><td><form:checkboxes path="categories" items="${categories}" itemLabel="name" itemValue="id" element="div"/></td></td>
							<td><div class="formError">${categoriesError}</div></td>	
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
