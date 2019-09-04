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

<jstl:set var="userId">
	<security:authentication property="principal.id" />
</jstl:set>

<display:table name="registrations" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column>
		<jstl:if test="${row.owner.user.id != userId}">
			<a href="actor/profile.do?id=${row.owner.id}"> <jstl:out
					value="${row.owner.user.username}" />
			</a>
		</jstl:if>
		<jstl:if test="${row.owner.user.id == userId}">
			<a href="registration/delete.do?id=${row.id}"> <i
				class="fa fa-times" aria-hidden="true"></i>
			</a>
		</jstl:if>
	</display:column>
	<display:column>
		<a href="conference/view.do?id=${row.conference.id}"> <jstl:out
				value="${row.conference.title}" />
		</a>
	</display:column>

</display:table>