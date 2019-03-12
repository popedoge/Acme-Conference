<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="user/settings/edit.do" modelAttribute="form">
	<acme:checkbox code="preferences.name" path="displayName"/>
	<acme:checkbox code="preferences.email" path="displayEmail"/>
	<acme:checkbox code="preferences.address" path="displayAddress"/>
	<acme:checkbox code="preferences.number" path="displayNumber"/>
	<acme:textbox code="preferences.signature" path="messageSignature" />
	<acme:submit name="save" code="save" />
</form:form>