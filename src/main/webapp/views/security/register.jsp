
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
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/libphonenumber-js/1.7.10/libphonenumber-js.min.js"></script>


<form:form action="register/save.do" modelAttribute="regForm"
	id="regForm">
	<div class="box">

		<div>
			<b><spring:message code="actor.account" /></b>
		</div>
		<acme:textbox code="actor.displayname" path="username" />
		<acme:password code="actor.password" path="password" id="password"
			tooltip="actor.password.tooltip" />
		<div>
			<spring:message code="actor.confirmpass" />
			<input type="password" id="comparepass" /> <i id="confirmpassstatus"
				class="fa" aria-hidden="true"></i>
		</div>
	</div>
	<div class="box">
		<div>
			<b><spring:message code="actor.personalinfo" /></b>
		</div>
		<form:hidden path="form.id" />
		<form:hidden path="role" />
		<acme:textbox code="actor.firstname" path="form.firstName" />
		<acme:textbox code="actor.lastname" path="form.lastName" />
		<acme:textbox code="actor.email" path="form.email" />
		<acme:textbox code="actor.address" path="form.address" />
		<acme:textbox code="actor.photo" path="form.photo" />
		<!--  phone number -->
		<acme:textbox code="actor.phonenumber" path="form.phoneNumber"
			id="phone-input" />
	</div>
	<div>
		<div>
			<form:checkbox path="acceptTerms" />
			<spring:message code="terms.accept" />
			&nbsp;<a href="terms/conditions.do"><spring:message
					code="terms.terms" /></a>
		</div>
		<div>
			<jstl:set var="terms-fail">
				<form:errors path="acceptTerms" />
			</jstl:set>
			<spring:message code="${terms-fail}" cssClass="error"/>
		</div>
	</div>
	<div>
		<acme:submit name="save" code="actor.register" />
	</div>
</form:form>

<script type="text/javascript" src="scripts/password.js"></script>