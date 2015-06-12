<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="<tiles:getAsString name="mystyle"/>"/>
	<title><spring:message code="webapp.name"/></title>
</head>

<body>
<div id="header">
	<div class="shell">
		<tiles:insertAttribute name="top" />
		<tiles:insertAttribute name="navigation" />
	</div>
</div>

<div id="container">
	<div class="shell">
		<tiles:insertAttribute name="messages" />
		<br />
		<div id="main">
			<div class="cl">&nbsp;</div>
			<tiles:insertAttribute name="content" />
			<div class="cl">&nbsp;</div>			
		</div>
	</div>
</div>

<div id="footer">
	<div class="shell">
		<tiles:insertAttribute name="footer" />
	</div>	
</div>

</body>
</html>
