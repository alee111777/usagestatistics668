<!-- Written by: Kirill -->
<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp" %>
<%@ include file="template/localInclude.jsp" %>

<b class="boxHeader">
	<spring:message code="usagestatistics668.patient.title"/>
</b>
<form method="post" class="box">

	<table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<td align="left">
				<spring:message code="usagestatistics668.query.from" />

				<privacy_tag:dateRange fromField="from" fromValue="${from}" untilField="until" untilValue="${until}" />
				
				<spring:message code="usagestatistics668.query.forPatient"/>
                                
                                <input type="text" name="patientId" placeholder="Enter Patient ID"/>
                                
                                <spring:message code="usagestatistics668.query.with"/>
                                
				<privacy_tag:usageFilter formFieldName="usageFilter" initialValue="${usageFilter}" />
				
				<spring:message code="usagestatistics668.query.type"/>
                                
                                <spring:message code="usagestatistics668.quantity.pretitle"/>
                                
                                <privacy_tag:quantityFilter formFieldName="quantityFilter" initialValue="${quantityFilter}" />
                                
                                <spring:message code="usagestatistics668.quantity.title"/>
                                
                                <input type="submit" value="<spring:message code="usagestatistics668.query.update"/>" />

			</td>
		</tr>
	</table>
                                
        <table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<th align="center"><spring:message code="usagestatistics668.results.type"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.date"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.user"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.patient"/></th>
		</tr>

                <c:forEach items="${patientList}" var="row" varStatus="rowStatus" >
                   <tr>
                        <td align="center">${row.access_type}</td>
                        <td align="center">${row.timestamp}</td>
                        <td align="center">${row.user_id}</td>
                        <td align="center">${row.patient_id}</td>
                   </tr>
                </c:forEach>
                
		<c:if test="${empty patientList}">
			<tr>
				<td colspan="8" style="padding: 10px; text-align: center"><spring:message code="usagestatistics668.noresults"/></td>
			</tr>
		</c:if>
	</table>

</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>