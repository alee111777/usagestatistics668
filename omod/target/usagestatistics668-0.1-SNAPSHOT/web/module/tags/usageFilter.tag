<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/taglibs/c-rt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/taglibs/spring.tld" %>

<%@ attribute name="formFieldName" required="true" %>
<%@ attribute name="initialValue" type="org.openmrs.module.usagestatistics668.ActionCriteria" required="false" %>
<%@ attribute name="showNoUsage" type="java.lang.Boolean" required="false" %>

<select name="${formFieldName}">
	<c:if test="${showNoUsage != false}">
		<option value="0" ${initialValue.ordinal == 0 ? 'selected="selected"' : ""}>&lt;<spring:message code="usagestatistics668.typeFilter.any"/>&gt;</option>
	</c:if>
	<option value="2" ${initialValue.ordinal == 1 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.type.viewed"/></option>
	<option value="3" ${initialValue.ordinal == 2 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.type.edited"/></option>
</select>
