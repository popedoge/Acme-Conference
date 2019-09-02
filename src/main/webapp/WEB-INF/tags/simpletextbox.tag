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

<%-- Attributes --%>

<%@ attribute name="path" required="true"%>
<%@ attribute name="id" required="false"%>
<%@ attribute name="placeholder" required="false"%>
<%@ attribute name="size" required="false"%>
<%@ attribute name="maxlength" required="false"%>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<%-- Definition --%>

	<form:label path="${path}">
	</form:label>
	<form:input id="${id}" path="${path}" placeholder="${placeholder}" size="${size}" maxlength="${maxlength}"/>
	<form:errors path="${path}" cssClass="error" />

