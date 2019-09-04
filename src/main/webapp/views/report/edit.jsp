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

<form:form action="report/reviewer/edit.do" modelAttribute="report">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="owner" value="${report.owner.id}" /><form:errors path="owner" cssClass="error"/>
	<form:hidden path="submission" value="${report.submission.id}" /><form:errors path="submission" cssClass="error"/>
	<acme:textbox code="report.originality" path="originalityScore" />
	<acme:textbox code="report.quality" path="qualityScore" />
	<acme:textbox code="report.readability" path="readabilityScore" />
	<form:select path="decision">
		<jstl:forEach items="${decisionList}" var="item">
			<form:option value="${item}">
				<spring:message code="${item}" />
			</form:option>
		</jstl:forEach>
	</form:select>
	<form:errors path="decision" cssClass="error"/>

	<acme:textbox code="report.comments" path="comments" />
	<acme:submit name="save" code="save" />
</form:form>