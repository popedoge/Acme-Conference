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
	<spring:message code="lorem.format" />
</jstl:set>
<jstl:set var="stdf">
	yyMMdd
</jstl:set>

<script type="text/javascript">
	var table = document.getElementById("lorem-table");
	var tbody = table.getElementsByTagName("tbody")[0];
	var rows = tbody.getElementsByTagName("tr");
	// add event handlers so rows light up and are clickable
	for (i = 0; i < rows.length; i++) {
		var value = rows[i].getElementsByTagName("td")[0].firstChild.nodeValue;
		if (value == 'mraible') {
			rows[i].style.backgroundColor = "red";
		}
	}
</script>

<display:table htmlId="lorem-table" name="lorems" id="row"
	requestURI="${requestURI}" pagesize="10" class="displaytag">
	<display:column titleKey="lorem.ticker" property="ticker" />
	<display:column titleKey="lorem.date">
		<fmt:formatDate value="${row.publicationMoment}" pattern="${df}" />
		<div class="hidden-date">
			<fmt:formatDate value="${row.publicationMoment}" pattern="${stdf}" />
		</div>
	</display:column>
	<!-- TODO: ADD NECESSARY COLUMS -->

	<!-- ACTION COLUMS -->
	<display:column>
		<!-- view -->
		<!-- ??? -->
	</display:column>
	<display:column>
		<!-- edit -->
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${!row.locked}">
				<a href="lorem/admin/edit.do?id=${row.id}"> <i
					class="fa fa-pencil" aria-hidden="true"></i>
				</a>
			</jstl:if>
		</security:authorize>
	</display:column>
	<display:column>
		<!-- delete -->
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${!row.locked}">
				<a href="lorem/admin/delete.do?id=${row.id}"> <i
					class="fa fa-times" aria-hidden="true"></i>
				</a>
			</jstl:if>
		</security:authorize>
	</display:column>
	<display:column>
		<!-- lock -->
		<security:authorize access="hasRole('ADMIN')">
			<jstl:if test="${!row.locked}">
				<a href="lorem/admin/lock.do?id=${row.id}"> <i
					class="fa fa-unlock" aria-hidden="true"></i>
				</a>
			</jstl:if>
			<jstl:if test="${row.locked}">
				<i class="fa fa-lock" aria-hidden="true"></i>
			</jstl:if>
		</security:authorize>
	</display:column>


</display:table>