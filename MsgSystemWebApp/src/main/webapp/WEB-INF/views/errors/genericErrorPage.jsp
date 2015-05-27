<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<tiles:insertDefinition name="defaultTemplate">
		<tiles:putAttribute name="content">
			<div class="box">
				<div class="box-head">
					<h2>
						<spring:message code="view.genericErrorPage.title" />
					</h2>
				</div>
				<div>
					<c:if test="${not empty errCode}">
						<h1>${errCode}:System Errors</h1>
					</c:if>

					<c:if test="${empty errCode}">
						<h1>System Errors</h1>
					</c:if>

					<c:if test="${not empty errMsg}">
						<h2>${errMsg}</h2>
					</c:if>
				</div>
			</div>
		</tiles:putAttribute>
	</tiles:insertDefinition>
</body>
</html>
