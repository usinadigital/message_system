<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${not empty messageOK}">
<div class="msg msg-ok">
	<p>
		<strong>${messageOK}</strong>
	</p>
</div>
</c:if>

<c:if test="${not empty messageERROR1}">
<div class="msg msg-error">
	<p>
		<strong>${messageERROR1}</strong>
	</p>
</div>
</c:if>

<c:if test="${not empty messageERROR2}">
<div class="msg msg-error">
	<p>
		<strong>${messageERROR2}</strong>
	</p>
</div>
</c:if>

<c:if test="${not empty messageERROR3}">
<div class="msg msg-error">
	<p>
		<strong>${messageERROR3}</strong>
	</p>
</div>
</c:if>

