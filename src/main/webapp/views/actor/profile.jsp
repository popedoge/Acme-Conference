
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
		<a href="user/settings/edit.do" style="text-decoration: none;">
			<div class="inline">
				<i class="fa fa-cog" aria-hidden="true"></i>
				<spring:message code="settings" />
			</div>
		</a> <a href="actor/edit.do" style="text-decoration: none;">
			<div class="inline">
				<i class="fa fa-pencil" aria-hidden="true"></i>
				<spring:message code="information" />
			</div>
		</a>
	</div>
	<!-- 	<div> -->
	<%-- 		<a href="user/review.do"> <spring:message code="reviewer" /> --%>
	<!-- 		</a> -->
	<!-- 	</div> -->
</jstl:if>

<jstl:if test="${actor.role == 'REVIEWER'}">
	<div>
		<h3>
			<spring:message code="reviewer" />
		</h3>
		<spring:message code="expertise" />
		:&nbsp;
		<jstl:out value="${actor.expertise}" />
	</div>
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
				&nbsp;
				<jstl:if test="${not empty actor.middleName}">
					"<jstl:out value="${actor.middleName}" />"&nbsp;
				</jstl:if>
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
<jstl:if test="${owner}">
	<div>
		<a href="actor/social/edit.do"> <spring:message code="social.new" />
		</a>
	</div>
</jstl:if>
<jstl:if test="${not empty profiles}">
	<div>
		<jstl:forEach items="${profiles}" var="profile">

			<div class="inline-outer" style="length: 200px;">
				<a href="${profile.url}" targer="_blank"
					style="text-decoration: none;">
					<div class="inline icon"
						style="background-image:url('${profile.network.icon}');"></div>
					<div class="inline" style="vertical-align: initial;">
						<jstl:out value="${profile.network.name}" />
					</div>
				</a>
				<jstl:if test="${owner}">
					<a href="actor/social/edit.do?id=${profile.id}"
						style="text-decoration: none;">
						<div class="inline">
							<i class="fa fa-pencil" aria-hidden="true"></i>
						</div>
					</a>
					<a href="actor/social/delete.do?id=${profile.id}"
						style="text-decoration: none;">
						<div class="inline">
							<i class="fa fa-times" aria-hidden="true"></i>
						</div>
					</a>
				</jstl:if>
			</div>

		</jstl:forEach>
	</div>
</jstl:if>
<br />