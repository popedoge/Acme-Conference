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



<form:form modelAttribute="mail" action="messaging/send.do">
	<form:hidden id="recipientsfield" path="recipients" />
	<form:hidden path="lock" />
	<jstl:if test="${!mail.lock}">
		<div>
			<spring:message code="message.recipients" />
			<div id="confirmed"></div>
			<input id="recipientsearch" type="text" name="fname"><button id="add-button" type="button"><spring:message code="add"/></button><br>
			<form:errors cssClass="error" path="recipients" />
		</div>
	</jstl:if>
	<div>
		<form:label path="subject">
			<spring:message code="message.subject" />
		</form:label>
		<form:input path="subject" />
		<form:errors cssClass="error" path="subject" />
	</div>
	<div>
		<form:label path="body">
			<spring:message code="message.body" />
		</form:label>
		<form:textarea path="body" />
		<form:errors cssClass="error" path="body" />
	</div>
	<div>
		<form:select id="topic" path="topic">
			<form:options items="${topics}" itemLabel="title" itemValue="id" />
			<form:option value="0" label="----" />
		</form:select>
	</div>
	<div>
		<input type="submit" name="save" value="<spring:message code="save"/>" />
	</div>
</form:form>

<script type="text/javascript" src="scripts/writemessage.js"></script>