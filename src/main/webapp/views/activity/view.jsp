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
<jstl:set var="df">
	<spring:message code="dateformat" />
</jstl:set>
<div>
	<a href="conference/view.do?id=${activity.conferenceId}"> <spring:message
			code="back" />
	</a>
</div>

<div>
	<jstl:if test="${activity.type==0}">
		<h3>
			<spring:message code="activity.panel" />
		</h3>
	</jstl:if>
	<jstl:if test="${activity.type==1}">
		<h3>
			<spring:message code="activity.presentation" />
		</h3>
	</jstl:if>
	<jstl:if test="${activity.type==2}">
		<h3>
			<spring:message code="activity.tutorial" />
		</h3>
	</jstl:if>
</div>

<div>
	<span><b><jstl:out value="${activity.title}" /></b>&nbsp;-&nbsp;<jstl:out
			value="${activity.speakers}" /></span>
</div>
<div>
	<span style="color: gray"><jstl:out
			value="${activity.startDate}" />&nbsp;-&nbsp;<jstl:out
			value="${activity.endDate}" /></span>
</div>
<div>
	<jstl:out value="${activity.summary}" />
</div>
<br />
<!-- tutorial sections -->

<jstl:if test="${activity.type == 2}">
	<security:authorize access="hasRole('ADMIN')">
		<!-- add section -->
		<div>
			<a href="section/edit.do?id=${activity.id}"> <spring:message
					code="tutorial.add" />
			</a>
		</div>
	</security:authorize>
	<jstl:forEach items="${activity.sections}" var="section"
		varStatus="tagStatus">
		<div class="box">
			<div>
				<b><jstl:out value="${section.title}" /></b>
			</div>
			<div>
				<jstl:out value="${section.summary}" />
			</div>
			<div>
				<img src="${section.pictures}">
			</div>
		</div>
	</jstl:forEach>
</jstl:if>
<!-- submission -->
<jstl:if test="${activity.type==1}">
	<div class="box">
		<div>
			<b><jstl:out value="${activity.submission.paper.title}" /></b><span
				style="color: gray;">&nbsp;~&nbsp;<jstl:out
					value="${activity.submission.paper.author}" /></span>
		</div>
		<div>
			<jstl:out value="${activity.submission.paper.summary}" />
		</div>
		<div>
			<a href="${activity.submission.paper.URL}"><jstl:out
					value="${activity.submission.paper.URL}" /></a>
		</div>
	</div>
	<!-- camera paper -->
	<jstl:if test="${not empty activity.submission.cameraPaper}">
		<div class="box">
			<div>
				<b><jstl:out value="${activity.submission.cameraPaper.title}" /></b><span
					style="color: gray;">&nbsp;~&nbsp;<jstl:out
						value="${activity.submission.cameraPaper.author}" /></span>
			</div>
			<div>
				<jstl:out value="${activity.submission.cameraPaper.summary}" />
			</div>
			<div>
				<a href="${activity.submission.cameraPaper.URL}"><jstl:out
						value="${activity.submission.cameraPaper.URL}" /></a>
			</div>
		</div>
	</jstl:if>
</jstl:if>
<div></div>

<!-- TODO: finish display (remember activities) -->