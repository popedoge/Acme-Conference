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

<div>
	<a href="conference/view.do?id=${submission.conference.id}"><b>
			<jstl:out value="${submission.conference.title}" />
	</b> </a>
</div>
<div>
	<span style="color: gray;"><jstl:out
			value="${submission.ticker}"></jstl:out></span>
</div>
<br />
<div>
	<spring:message code="submission.status" />
	:&nbsp;
	<spring:message code="${submission.status}" />
</div>
<br />
<!-- paper -->
<div class="box">
	<div>
		<b><jstl:out value="${submission.paper.title}" /></b><span
			style="color: gray;">&nbsp;~&nbsp;<jstl:out
				value="${submission.paper.author}" /></span>
	</div>
	<div>
		<jstl:out value="${submission.paper.summary}" />
	</div>
	<div>
		<a href="${submission.paper.URL}"><jstl:out
				value="${submission.paper.URL}" /></a>
	</div>
</div>
<!-- camera paper -->
<jstl:if test="${not empty submission.cameraPaper}">
	<br />
	<div class="box">
		<div>
			<b><jstl:out value="${submission.cameraPaper.title}" /></b><span
				style="color: gray;">&nbsp;~&nbsp;<jstl:out
					value="${submission.cameraPaper.author}" /></span>
		</div>
		<div>
			<jstl:out value="${submission.cameraPaper.summary}" />
		</div>
		<div>
			<a href="${submission.cameraPaper.URL}"><jstl:out
					value="${submission.cameraPaper.URL}" /></a>
		</div>
	</div>
</jstl:if>
<security:authorize access="hasRole('REVIEWER')">
	<br />
	<div>
		<a href="report/edit.do?id=${row.id}"> <spring:message
				code="submission.report" />
		</a>
	</div>
</security:authorize>
<br />