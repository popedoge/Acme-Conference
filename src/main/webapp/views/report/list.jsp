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
<jstl:set var="df">
	<spring:message code="dateformat" />
</jstl:set>

<button type="button" name="back" onclick="history.back()">
	<spring:message code="back" />
</button>

<display:table name="reports" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<!-- conference -->
	<display:column>
		<a href="conference/view.do?id=${report.submission.conference.id}"> <jstl:out
				value="${report.submission.conference.title}" />
		</a>
	</display:column>
	<!-- submission -->
	<display:column>
		<a href="submission/view.do?id=${report.submission.id}"> <jstl:out
				value="${report.submission.ticker}" />
		</a>
	</display:column>
	<!-- edit -->
	<display:column titleKey="report.originality" property="originalityScore">		
	</display:column>
	<display:column titleKey="report.quality" property="qualityScore">		
	</display:column>
	<display:column titleKey="report.readability" property="readabilityScore">		
	</display:column>
	<display:column  property="decision">		
	</display:column>
	<display:column  property="comments">		
	</display:column>

</display:table>