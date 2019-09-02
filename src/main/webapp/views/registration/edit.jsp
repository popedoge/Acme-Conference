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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<script type="text/javascript" src="scripts/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="styles/bootstrap-datetimepicker.css"
	type="text/css">


<div>
	<button type="button" name="back" onclick="history.back()">
		<spring:message code="back" />
	</button>
</div>
<form:form modelAttribute="registration" action="registration/edit.do">
	<form:hidden path="id"/><form:errors path="id" cssClass="error" />
	<form:hidden path="version"/><form:errors path="version" cssClass="error" />
	<form:hidden path="owner" value="${registration.owner.id}"/><form:errors path="owner" cssClass="error" />
	<form:hidden path="conference" value="${registration.conference.id}"/><form:errors path="conference" cssClass="error" />
	<acme:textbox code="card.holder" path="creditCard.holder" />
	<acme:textbox code="card.number" path="creditCard.number" />
	<acme:textbox code="card.brand" path="creditCard.brand" />
	<div>
		<spring:message code="card.expiration"/>
		<acme:simpletextbox path="creditCard.expirationMonth" size="1" maxlength="2" />/<acme:simpletextbox  path="creditCard.expirationYear" size="1" maxlength="2"/>
	</div>
	<acme:textbox code="card.cvv" path="creditCard.cvv" />



	<div>
		<input type="submit" name="save" value="<spring:message code="save"/>" />
	</div>
</form:form>