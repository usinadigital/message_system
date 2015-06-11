<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="messageActive" value="active" scope="request"/>

<body>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="content">
			<div class="box-message">
				<div class="box-head">
					<h2><spring:message code="view.message.title" /></h2>
				</div>
				<form:form method="POST" action="message" commandName="message">
				<div class="myForm">
				<p>
					<label class="myLabel"><spring:message code="view.message.label.title" /></label>
				</p>
				<p>
					<form:input path="title" autocomplete="off"/>
				</p>
				<br>
				<p>
					<label class="myLabel"><spring:message code="view.message.label.message" /></label>
				</p>
				<p>
					<form:textarea path="text" rows="5" cols="40" />
				</p>
				<br>
				<div style="width:300px; height:150px; overflow: auto;">
					<p>
						<form:checkboxes element="div class='myCheckboxes' " path="categories" items="${categories}" itemLabel="name" itemValue="id" />
					</p>
				</div>				
				<br>
				<input type="submit" class="button" value="<spring:message code="view.message.button.submit" />" />
				</div>
				</form:form>
			</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</body>
