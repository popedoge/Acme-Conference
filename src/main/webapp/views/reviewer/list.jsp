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

<display:table name="requests" id="row" requestURI="${requestURI}"
	pagesize="10" class="displaytag">
	<display:column titleKey="reviewer.date">
		<fmt:formatDate value="${row.moment}" pattern="dd/MM/yyyy HH:mm" />
	</display:column>
	<display:column titleKey="reviewer.user">
		<a href="actor/profile?id=${row.requestee.id}">
			<jstl:out value="${row.requestee.name}"/>
			<jstl:out value="${row.requestee.lastname}"/>
		</a>
	</display:column>
	<display:column titleKey="reviewer.status">
		<jstl:out value="${row.status}"/>
	</display:column>
	<display:column>
		<a href="">
		</a>
	</display:column>
	<display:column>
		<a href="">
		</a>
	</display:column>
</display:table>