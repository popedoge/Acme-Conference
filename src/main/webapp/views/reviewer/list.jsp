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
<%-- <jstl:set var="df"><spring:message code="lorem.pattern"/></jstl:set> --%>

<button type="button" name="back" onclick="history.back()">
	<spring:message code="back" />
</button>

<jstl:if test="${not empty submission}">
	<div>
		<a href="submission/view.do?id=${submission.id}"> <jstl:out
				value="${submission.ticker}" />
		</a>
	</div>
	<div>
		<form action="submission/admin/reviewer/assign.do" method="get"
			id="assignForm">
			<input type="hidden" id="submissionId" name="submissionId"
				value="${submission.id}" /> <select name="reviewerId" id="reviewerId"
				form="assignForm">
				<jstl:forEach items="${allReviewers}" var="reviewer">
					<option value="${reviewer.id}">
						<jstl:out value="${reviewer.user.username}" />
					</option>
				</jstl:forEach>
			</select> <input type="submit"
				value="<spring:message code="reviewer.assign"/>" />
		</form>
	</div>
</jstl:if>

<display:table name="reviewers" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column>
		<a href="actor/profile.do?id=${row.id}"> <jstl:out
				value="${row.user.username}" />
		</a>
	</display:column>
	<display:column>
		<jstl:out value="${row.name}" />&nbsp;<jstl:out
			value="${row.surname}" />
	</display:column>
	<display:column titleKey="reviewer.expertise" property="expertise">

	</display:column>
</display:table>