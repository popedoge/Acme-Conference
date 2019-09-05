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

<form:form action="lorem/edit.do" modelAttribute="lorem">
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	<form:hidden path="reference" value="${lorem.reference.id}"/>
	<form:hidden path="ticker"/>
	<form:hidden path="publicationMoment"/>
	<form:hidden path="locked"/>
	<!-- TODO: ADD OTHER ATTRIBUTES -->
	
	<!-- body -->
	<acme:textarea code="lorem.body" path="body"/>
	
	<acme:submit name="save" code="save" />
</form:form>