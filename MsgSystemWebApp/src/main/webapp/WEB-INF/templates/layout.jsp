<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="<tiles:getAsString name="layoutstyle"/>"/>
	<link type="text/css" rel="stylesheet" href="<tiles:getAsString name="mystyle"/>"/>
	<title>Default tiles template</title>
</head>
<body>
	<div class="prova">
		<p>CIAO</p>
	</div>
	<div class="page">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
