<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/taglibs/c-rt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/taglibs/spring.tld" %>

<%@ attribute name="formFieldName" required="true" %>
<%@ attribute name="initialValue" type="org.openmrs.module.usagestatistics668.ActionCriteria" required="false" %>
<%@ attribute name="showNoQuantity" type="java.lang.Boolean" required="false" %>

<select name="${formFieldName}">
	<c:if test="${showNoQuantity != false}">
		<option value="10" ${initialValue.ordinal == 10 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.quantity.ten"/></option>
	</c:if>
	<option value="20" ${initialValue.ordinal == 20 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.quantity.twenty"/></option>
	<option value="30" ${initialValue.ordinal == 30 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.quantity.thirty"/></option>
	<option value="40" ${initialValue.ordinal == 40 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.quantity.forty"/></option>
	<option value="50" ${initialValue.ordinal == 50 ? 'selected="selected"' : ""}><spring:message code="usagestatistics668.quantity.fifty"/></option>
</select>
