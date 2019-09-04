<%--
 * login.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="activity/edit.do" modelAttribute="activity">
	<form:hidden path="id" />
	<form:hidden path="conferenceId" />
	<form:hidden path="type" />

	<acme:textbox code="activity.title" path="title" />
	<acme:textbox code="activity.speakers" path="speakers" />
	<acme:textbox code="activity.summary" path="summary" />
	<acme:textbox code="activity.location" path="location" />
	<acme:datepicker code="activity.start" path="startDate" />
	<acme:datepicker code="activity.end" path="endDate" />
	<!-- tutorial -->
	<jstl:if test="${activity.type == 2}">
		<jstl:forEach items="${activity.sections}" var="section"
			varStatus="tagStatus">
			<form:hidden path="sections[${tagStatus.index}]"
				value="${section.id}" />
			<form:errors path="sections[${tagStatus.index}]" cssClass="error" />
		</jstl:forEach>
	</jstl:if>
	<!-- presentation -->
	<jstl:set var="pickOne">
		<spring:message code="activity.submission.pick" />
	</jstl:set>
	<jstl:if test="${activity.type == 1}">
		<form:select path="submission">
			<form:option value="0" label="${pickOne}" />
			<form:options items="${submissions}" itemLabel="ticker"
				itemValue="id" />

		</form:select>
	</jstl:if>
	<acme:submit name="save" code="save" />
</form:form>