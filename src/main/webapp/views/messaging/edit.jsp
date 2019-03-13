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

<form:form modelAttribute="messageBox"
	action="messaging/edit.do">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="category" />
	
	<form:label path="parent">
		<spring:message code="messagebox.parent" />
	</form:label>
	<form:select id="parent" path="parent">
		<form:options items="${messageBoxes}" itemLabel="name" itemValue="id" />
		<form:option value="0" label="---" />
	</form:select>
	<form:errors cssClass="error" path="parent" />
	<br/>
	<form:hidden path="owner" value="${messageBox.owner.id}"/>
	<form:label path="name">
		<spring:message code="messagebox.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br/>
	<input type="submit" name="save" value="<spring:message code="save"/>"/>
</form:form>