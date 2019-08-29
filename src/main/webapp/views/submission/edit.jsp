<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<script type="text/javascript" src="scripts/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="styles/bootstrap-datetimepicker.css"
	type="text/css">


<div>
	<button type="button" name="back" onclick="history.back()">
		<spring:message code="back" />
	</button>
</div>
<form:form modelAttribute="submissionForm"
	action="conference/admin/edit.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="owner" value="${submissionForm.owner.id}" />
	<form:hidden path="conference" value="${submissionForm.conference.id}" />
	<form:hidden path="ticker" />
	<form:hidden path="status" />


	<div>
		<form:hidden path="paperId" />
		<acme:textbox code="submission.paper.title" path="paperTitle" />
		<acme:textbox code="submission.paper.author" path="paperAuthor" />
		<acme:textbox code="submission.paper.summary" path="paperSummary" />
		<acme:textbox code="submission.paper.url" path="paperUrl" />
	</div>
	<div>
		<form:hidden path="cameraId" />
		<acme:textbox code="submission.paper.title" path="cameraTitle" />
		<acme:textbox code="submission.paper.author" path="cameraAuthor" />
		<acme:textbox code="submission.paper.summary" path="cameraSummary" />
		<acme:textbox code="submission.paper.url" path="cameraURL" />
	</div>

	<div>
		<input type="submit" name="save" value="<spring:message code="save"/>" />
	</div>
</form:form>