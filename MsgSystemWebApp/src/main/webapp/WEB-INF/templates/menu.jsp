<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="menu">
Menu
    <ul>
        <li>
            <spring:url value="/home" var="homeUrl" htmlEscape="true"/>
            <a href="${homeUrl}">Home</a>
        </li>
        <li>
            <spring:url value="/message" var="messageUrl" htmlEscape="true"/>
            <a href="${messageUrl}">Message</a>
        </li>
        <li>
            <spring:url value="/administration" var="administrationUrl" htmlEscape="true"/>
            <a href="${administrationUrl}">Administration</a>
        </li>
        <li>
            <spring:url value="/login?logout" var="logoutUrl" htmlEscape="true"/>
            <a href="${logoutUrl}">Logout</a>
        </li>
    </ul>
</div>
