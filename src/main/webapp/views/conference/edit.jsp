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
<form:form modelAttribute="conference" action="conference/admin/edit.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="locked" />
	<form:hidden path="owner" value="${conference.owner.id}" />
	<form:errors path="owner" cssClass="error" />
	<jstl:forEach items="${submission.activities}" var="activity"
		varStatus="tagStatus">
		<form:hidden path="activities[${tagStatus.index}]"
			value="${activity.id}" />
		<form:errors path="activities[${tagStatus.index}]" cssClass="error" />
	</jstl:forEach>
	<acme:textbox code="conference.title" path="title" />
	<acme:textbox code="conference.acronym" path="acronym" />
	<acme:textbox code="conference.venue" path="venue" />
	<acme:textarea code="conference.summary" path="summary" />
	<acme:textbox code="conference.fee" path="fee" />

	<acme:datepicker code="conference.start" path="startDate" />
	<acme:datepicker code="conference.end" path="endDate" />

	<acme:datepicker code="conference.submission" path="submissionDL" />
	<acme:datepicker code="conference.notification" path="notificationDL" />
	<acme:datepicker code="conference.camera" path="cameraDL" />



	<div>
		<input type="submit" name="save" value="<spring:message code="save"/>" />
	</div>
</form:form>
<div>
	<a href="conference/list.do">
		<spring:message code="cancel"/>
	</a>
</div>