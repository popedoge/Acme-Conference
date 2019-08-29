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
<jstl:set var="df"><spring:message code="dateformat"/></jstl:set>

<button type="button" name="back" onclick="history.back()"><spring:message code="back"/></button>

<display:table name="submissions" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column titleKey="submission.ticker" property="ticker"/>
	<display:column titleKey="conference.acronym" property="acronym"/>
	<display:column titleKey="conference.summary" property="summary"/>
	<display:column titleKey="conference.fee" property="fee"/>
	<display:column class="date" titleKey="conference.start">
		<fmt:formatDate  value="${row.startDate}" pattern="${df}" />
	</display:column>
	<display:column class="date" titleKey="conference.end">
		<fmt:formatDate value="${row.endDate}" pattern="${df}" />
	</display:column>
	
</display:table>