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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- receives: messagebox list, messagebox, messages list -->
<jstl:if test="${not empty messageBox}">
	<div>
		<jstl:if test="${messageBox.parent != null}">
			<h3>
				<jstl:out value="${messageBox.name}" />
			</h3>
			<a href="messaging/view.do?id=${messageBox.parent.id}">Go back</a>
		</jstl:if>
	</div>
	</br>
</jstl:if>
<!-- create messagebox -->
<div>
	<a href="messaging/create.do?=${messageBox.id}"> <spring:message
			code="create" />
	</a>
</div>

<!-- message boxes -->
<jstl:if test="${not empty messageBoxes}">
	<display:table name="messageBoxes" id="row"
		requestURI="${boxRequestURI}" pagesize="10" class="displaytag">
		<!-- Name of messagebox links to open the messagebox -->
		<display:column>
			<a href="messaging/view.do?id=${row.id}"> <jstl:out
					value="${row.name}" />
			</a>
		</display:column>
		<display:column>
			<jstl:if test="${row.category == 'USERBOX'}">
				<a href="messaging/edit.do?id=${row.id}"> <i
					class="fa fa-pencil" aria-hidden="true"></i>
				</a>
			</jstl:if>
		</display:column>
		<display:column>
			<jstl:if test="${row.category == 'USERBOX'}">
				<a href="messaging/delete.do?id=${row.id}"> <i
					class="fa fa-times" aria-hidden="true"></i>
				</a>
			</jstl:if>
		</display:column>
	</display:table>
</jstl:if>

<!-- messages -->
<jstl:if test="${not empty messages}">
	<display:table name="messages" id="row"
		requestURI="${messageRequestURI}" pagesize="10" class="displaytag">
		<display:column titleKey="message.priority">
			<div role="group" aria-labelledby="${row.priority}">
				<jstl:choose>
					<jstl:when test="${row.priority == 'HIGH'}">
						<i class="fa fa-envelope" aria-hidden="true"
							style="color: #D52121"></i>
						<i class="fa fa-exclamation" aria-hidden="true"
							style="color: #D52121"></i>
					</jstl:when>
					<jstl:when test="${row.priority == 'LOW'}">
						<i class="fa fa-envelope" aria-hidden="true"
							style="color: #6885D9"></i>
					</jstl:when>
					<jstl:when test="${row.priority == 'NEUTRAL'}">
						<i class="fa fa-envelope" aria-hidden="true"
							style="color: #A7A7A7"></i>
					</jstl:when>

				</jstl:choose>
			</div>
		</display:column>
		<!-- name of message is link to open message -->
		<display:column titleKey="message.subject">
			<a href="messaging/viewmessage.do?id=${row.id}&box=${messageBox.id}"> <jstl:out
					value="${row.subject}" />
			</a>
		</display:column>
		<display:column titleKey="message.sender">
			<jstl:out value="${row.senderAlias}" />
		</display:column>
		<display:column property="deliveryDate" titleKey="message.delivery">
			<fmt:formatDate value="${row.deliveryDate}" pattern="dd/MM/yyyy" />
		</display:column>
		<display:column>
			<a
				href="messaging/remove.do?messageId=${row.id}&boxId=${messageBox.id}">
				<i class="fa fa-times" aria-hidden="true"></i>
			</a>
		</display:column>
	</display:table>
</jstl:if>
