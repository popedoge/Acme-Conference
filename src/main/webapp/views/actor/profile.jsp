
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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jstl:if test="${owner}">
	<div class="inline-container">
		<a href="preferences/edit.do" style="text-decoration: none;">
			<div class="inline">
				<i class="fa fa-cog" aria-hidden="true"></i>
				<spring:message code="settings"/>
			</div>
		</a> <a href="actor/edit.do" style="text-decoration: none;">
			<div class="inline">
				<i class="fa fa-pencil" aria-hidden="true"></i>
				<spring:message code="information"/>
			</div>
		</a>
	</div>
	<br/>
	<br/>
</jstl:if>

<div>
	<div class="inline-outer">
		<div class="thumb inline"
			style="background-image:url('${actor.photo}')"></div>
		<div class="inline">
			<div>
				<b><security:authentication property="principal.username" /></b>
			</div>
			<div>
				<jstl:out value="${actor.firstName}" />
				<jstl:out value="${actor.lastName}" />
			</div>
		</div>
	</div>
	<jstl:if test="${not empty actor.email}">
		<div>
			<i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;
			<jstl:out value="${actor.email}" />
		</div>
	</jstl:if>
	<jstl:if test="${not empty actor.phoneNumber}">
		<div>
			<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;
			<jstl:out value="${actor.phoneNumber}" />
		</div>
	</jstl:if>
	<jstl:if test="${not empty actor.address}">
		<div>
			<i class="fa fa-home" aria-hidden="true"></i>&nbsp;
			<jstl:out value="${actor.address}" />
		</div>
	</jstl:if>
</div>
<br />
