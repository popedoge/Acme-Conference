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
	<security:authorize access="hasRole('ADMIN')">
		<!-- evaluate -->
		<jstl:if test="${options.evaluate}">
			<div>
				<a href="conference/admin/eval.do?id=${conference.id}"> <spring:message
						code="conference.eval" />
				</a>
			</div>
		</jstl:if>
		<!-- message registered -->
		<jstl:if test="${options.msgReg}">
			<div>
				<a
					href="messaging/admin/broadcast/registered.do?id=${conference.id}">
					<spring:message code="conference.broadcast.registered" />
				</a>
			</div>
		</jstl:if>
		<!-- message submitted -->
		<jstl:if test="${options.msgSub}">
			<div>
				<a href="messaging/admin/broadcast/submitted.do?id=${conference.id}">
					<spring:message code="conference.broadcast.submitted" />
				</a>
			</div>
		</jstl:if>
	</security:authorize>

	<security:authorize access="hasRole('AUTHOR')">
		<jstl:if test="${options.reg}">
			<div>
				<!-- register -->
				<a href="registration/edit.do?id=${conference.id}"> <spring:message
						code="conference.register" />
				</a>
			</div>
		</jstl:if>
		<jstl:if test="${options.sub}">
			<div>
				<!-- make submission -->
				<a href="submission/author/create.do?id=${conference.id}"> <spring:message
						code="conference.submit" />
				</a>
			</div>
		</jstl:if>
	</security:authorize>


	<security:authorize access="hasRole('REVIEWER')">
		<div>
			<!-- view submissions -->
			<a href="submission/reviewer/list.do?id=${conference.id}"> <spring:message
					code="conference.submission.view" />
			</a>
		</div>
	</security:authorize>
</div>

<jstl:if test="${not empty notif}">
	<span class="error"><spring:message code="${notif}" /></span>
</jstl:if>
<div>
	<h3>
		<jstl:out value="${conference.title}" />
	</h3>
	<div>
		<span style="color: gray;"><fmt:formatDate
				value="${conference.startDate}" pattern="${df}" />&nbsp;-&nbsp;<span
			style="color: gray;"><fmt:formatDate
					value="${conference.endDate}" pattern="${df}" /></span> </span>
	</div>
	<div>
		<b><spring:message code="conference.venue" />:</b>&nbsp;
		<jstl:out value="${conference.venue}"></jstl:out>
	</div>
	<div>
		<b><spring:message code="conference.summary" />:</b>&nbsp;
		<jstl:out value="${conference.summary}"></jstl:out>
	</div>
	<div>
		<b><spring:message code="conference.fee" />:</b>&nbsp;
		<jstl:out value="${conference.fee}"></jstl:out>
		&nbsp;EUR
	</div>
	<br />
	<!-- activities -->
	<span><b><jstl:out value="${conference.activities.size()}" /></b>&nbsp;<spring:message
			code="conference.activities" /></span>
	<security:authorize access="hasRole('ADMIN')">
		<jstl:if test="${options.addActivity}">
			<form action="activity/create.do" method="get" id="activityForm">
				<input type="hidden" id="conferenceId" name="conferenceId"
					value="${conference.id}" /> <select name="type" id="type"
					form="activityForm">
					<option value="0"><spring:message code="activity.panel" /></option>
					<option value="1"><spring:message
							code="activity.presentation" /></option>
					<option value="2"><spring:message code="activity.tutorial" /></option>

				</select> <input type="submit" value="<spring:message code="add"/>" />
			</form>
		</jstl:if>
	</security:authorize>
	<div>
		<jstl:forEach var="activity" items="${activities}">
			<div class="box">
				<div>
					<span><b><jstl:out value="${activity.title}" /></b>&nbsp;-&nbsp;<jstl:out
							value="${activity.speakers}" /></span>
				</div>
				<div>
					<span style="color: gray"><jstl:out value="${activity.startDate}" />&nbsp;-&nbsp;<jstl:out
							value="${activity.endDate}" /></span>
				</div>
				<div>
					<jstl:out value="${activity.summary}" />
				</div>
				<div></div>
				<div class="inline-outer">
					<div class="inline">
						<!-- view activity -->
						<a href="activity/view.do?id=${activity.id}&conferenceId=${conference.id}"> <spring:message code="activity.view" />
						</a>
					</div>
					<security:authorize access="hasRole('ADMIN')">
						<jstl:if test="${options.addActivity}">
							<!-- edit -->
							<div class="inline">
								<a href="activity/edit.do?id=${activity.id}&conferenceId=${conference.id}"> <i class="fa fa-pencil" aria-hidden="true"></i>
								</a>
							</div>
							<!-- delete -->
							<div class="inline">
								<a href="activity/delete.do?id=${activity.id}&conferenceId=${conference.id}"> <i class="fa fa-times" aria-hidden="true"></i>
								</a>
							</div>
						</jstl:if>
					</security:authorize>
				</div>
			</div>
		</jstl:forEach>
	</div>
	<!-- attendance -->
	<br /> <span><b><jstl:out value="${attendees.size()}" /></b>&nbsp;<spring:message
			code="conference.attendance" /></span>
	<security:authorize access="isAuthenticated()">
		<div>
			<jstl:forEach var="attendee" items="${attendees}">
				<div class="box">
					<a href="actor/profile.do?id=${attendee.owner.id}"> <b><jstl:out
								value="${attendee.owner.user.username}" /></b>
					</a> <br /> <span><jstl:out value="${attendee.owner.name}" />&nbsp;<jstl:out
							value="${attendee.owner.name}" /></span>
				</div>
			</jstl:forEach>
		</div>
	</security:authorize>
</div>
<!-- TODO: finish display (remember activities) -->