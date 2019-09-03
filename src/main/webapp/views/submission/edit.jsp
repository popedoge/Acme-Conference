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
<form:form modelAttribute="submission"
	action="submission/author/edit.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<jstl:forEach items="${submission.reviewers}" var="reviewer"
		varStatus="tagStatus">
		<form:hidden path="reviewers[${tagStatus.index}]"
			value="${reviewer.id}" />
		<form:errors path="reviewers[${tagStatus.index}]" cssClass="error" />
	</jstl:forEach>
	<form:hidden path="owner" value="${submission.owner.id}" />
	<form:errors path="owner" />
	<form:hidden path="conference" value="${submission.conference.id}" />
	<form:errors path="conference" />
	<form:hidden path="ticker" />
	<form:errors path="ticker" />
	<form:hidden path="status" />
	<form:errors path="status" />
	<br />
	<div class="box">
		<b><spring:message code="submission.paper" /></b>
		<form:hidden path="paper.id" />
		<form:hidden path="paper.cameraReady" />
		<form:errors path="paper.id" />
		<form:hidden path="paper.version" />
		<form:errors path="paper.version" />
		<acme:textbox code="submission.paper.title" path="paper.title" />
		<acme:textbox code="submission.paper.author" path="paper.author" />
		<acme:textbox code="submission.paper.summary" path="paper.summary" />
		<acme:textbox code="submission.paper.url" path="paper.URL" />
	</div>
	<div class="box">
		<b><spring:message code="submission.camerapaper" /></b>
		<form:hidden path="cameraPaper.id" />
		<form:errors path="cameraPaper.id" />
		<form:hidden path="cameraPaper.cameraReady" />
		<form:hidden path="cameraPaper.version" />
		<form:errors path="cameraPaper.version" />
		<acme:textbox code="submission.paper.title" path="cameraPaper.title" />
		<acme:textbox code="submission.paper.author" path="cameraPaper.author" />
		<acme:textbox code="submission.paper.summary"
			path="cameraPaper.summary" />
		<acme:textbox code="submission.paper.url" path="cameraPaper.URL" />
	</div>

	<div>
		<input type="submit" name="save" value="<spring:message code="save"/>" />
	</div>
</form:form>