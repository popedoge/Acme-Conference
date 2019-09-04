<%--
 * index.jsp
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

<!-- submissions -->
<div class="box">
	<spring:message code="stats.sub" />
	<div>
		<b><spring:message code="stats.min" /></b>:&nbsp;
		<jstl:out value="${stats.maxSubmissionsPerConference}" />
	</div>
	<div>
		<b><spring:message code="stats.max" /></b>:&nbsp;
		<jstl:out value="${stats.minSubmissionsPerConference}" />
	</div>
	<div>
		<b><spring:message code="stats.std" /></b>:&nbsp;
		<jstl:out value="${stats.stdSubmissionsPerConference}" />
	</div>
</div>


<!-- registrations -->
<div class="box">
	<spring:message code="stats.reg" />
	<div>
		<b><spring:message code="stats.min" /></b>:&nbsp;
		<jstl:out value="${stats.maxRegistrationsPerConference}" />
	</div>
	<div>
		<b><spring:message code="stats.max" /></b>:&nbsp;
		<jstl:out value="${stats.minRegistrationsPerConference}" />
	</div>
	<div>
		<b><spring:message code="stats.std" /></b>:&nbsp;
		<jstl:out value="${stats.stdRegistrationsPerConference}" />
	</div>
</div>

<!-- fee -->
<div class="box">
	<spring:message code="stats.fee" />
	<div>
		<b><spring:message code="stats.min" /></b>:&nbsp;
		<jstl:out value="${stats.maxConferenceFee}" />
	</div>
	<div>
		<b><spring:message code="stats.max" /></b>:&nbsp;
		<jstl:out value="${stats.maxConferenceFee}" />
	</div>
	<div>
		<b><spring:message code="stats.std" /></b>:&nbsp;
		<jstl:out value="${stats.stdConferenceFee}" />
	</div>
</div>