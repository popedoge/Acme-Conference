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

<jstl:if test="${not empty box}">
	<div>
		<a href="messaging/view.do?id=${box.id}"> <spring:message
				code="back" />
		</a>
	</div>
</jstl:if>
<br />
<jstl:if test="${not empty mail}">
	<!-- subject -->
	<div>
		<h3>
			<jstl:out value="${mail.subject}" />
		</h3>
	</div>
	<!-- sender + send message to sender -->
	<div class="tooltip">
		<span class="tooltiptext">Send message to this user</span> <span>
			<b><jstl:out value="${mail.senderAlias}" /></b> <jstl:out
				value="${mail.sender.user.username}" />&nbsp;<a
			href="messaging/reply.do?id=${mail.id}"><i
				class="fa fa-reply" aria-hidden="true"></i>
		</a>
		</span>
	</div>
	<!-- delivery date -->

	<div style="color: gray">

		<fmt:formatDate value="${mail.deliveryDate}"
			pattern="dd/MM/yyyy HH:mm" />
	</div>
	<br />
	<!-- body -->
	<div>

		<jstl:out value="${mail.body}" />
		<br /> <br />
	</div>

</jstl:if>
