<%--
 * textbox.tag
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty"%>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="scripts/bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="styles/bootstrap-datetimepicker.css"
	type="text/css">
<%-- Attributes --%>

<%@ attribute name="path" required="true"%>
<%@ attribute name="code" required="true"%>
<%@ attribute name="icon" required="false"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="placeholder" required="false"%>
<%@ attribute name="readonly" required="false"%>
<%@ attribute name="format" required="false"%>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<%-- Definition --%>

<div class="input-append date form_datetime">
	<form:label path="${path}">
		<jstl:if test="${icon != null}">
			<i class="${icon}" aria-hidden="true"></i>
		</jstl:if>
		<jstl:if test="${icon == null}">
			<spring:message code="${code}" />
		</jstl:if>
	</form:label>
	<form:input path="${path}" size="20" id="${id}" readonly="${readonly}"
		placeholder="${placeholder}"/>
	<span class="add-on"><i class="icon-th"></i></span>
	<form:errors path="${path}" cssClass="error" />
</div>
<script type="text/javascript">
	$(function() {
		$('.form_datetime').datetimepicker({
			format: "dd/mm/yyyy hh:ii"
		});
	});
</script>


