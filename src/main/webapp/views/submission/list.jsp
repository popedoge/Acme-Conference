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
<jstl:set var="userId">
	<security:authentication property="principal.id" />
</jstl:set>

<button type="button" name="back" onclick="history.back()">
	<spring:message code="back" />
</button>

<!-- TODO: AUTO ASSIGN VIEW -->
<!-- auto assign -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="submission/admin/autoassign.do"> <spring:message
				code="autoassign" />
		</a>
	</div>
	<div>
		<jstl:if test="${not empty notif}">
			<span class="error"><spring:message code="${notif}" /></span>
		</jstl:if>
	</div>
</security:authorize>

<display:table name="submissions" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column property="ticker" />
	<display:column>
		<a href="actor/profile.do?id=${row.owner.id}"> <jstl:out
				value="${row.owner.user.username}" />
		</a>
	</display:column>
	<display:column>
		<spring:message code="${row.status}" />
	</display:column>
	<!-- view -->
	<display:column>
		<a href="submission/view.do?id=${row.id}"> <i class="fa fa-eye"
			aria-hidden="true"></i>
		</a>
	</display:column>
	<!-- edit -->
	<display:column>
		<security:authorize access="hasRole('AUTHOR')">
			<jstl:if test="${row.owner.user.id==userId}">
				<a href="submission/author/edit.do?id=${row.id}"> <i
					class="fa fa-pencil" aria-hidden="true"></i></a>
			</jstl:if>
		</security:authorize>
	</display:column>
	<!-- create report -->
	<display:column>
		<security:authorize access="hasRole('REVIEWER')">
			<jstl:if test="${row.status == 'submission.pending'}">
				<a href="report/reviewer/edit.do?id=${row.id}"> <spring:message
						code="submission.report" />
				</a>
			</jstl:if>
		</security:authorize>
	</display:column>
	<!-- view reports -->
	<display:column>
		<security:authorize access="hasRole('AUTHOR')">
			<jstl:if test="${row.status == 'submission.pending'}">
				<a href="report/author/list.do?id=${row.id}"> <spring:message
						code="submission.report.list" />
				</a>
			</jstl:if>
		</security:authorize>
	</display:column>
	<!-- assign reviewer -->
	<display:column>
		<security:authorize access="hasRole('ADMIN')">
			<a href="submission/admin/reviewer/list.do?submissionId=${row.id}">
				<spring:message code="submission.reviewers" />
			</a>
		</security:authorize>
	</display:column>

</display:table>