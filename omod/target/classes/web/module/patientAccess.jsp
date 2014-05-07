<!-- Written by: Kirill -->
<%@ include file="/WEB-INF/template/include.jsp"%>

<!--<openmrs:require privilege="View Patient Privacy Records" otherwise="/login.htm" redirect="/module/usagestatistics/users.htm"/>-->

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp" %>
<%@ include file="template/localInclude.jsp" %>

<b class="boxHeader">
	<spring:message code="usagestatistics668.patient.title"/>
</b>
<form method="get" class="box">

	<table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<td align="left">
				<spring:message code="usagestatistics668.query.from" />

				<privacy_tag:dateRange fromField="from" fromValue="${from}" untilField="until" untilValue="${until}" />
				
				<spring:message code="usagestatistics668.query.forPatient"/>

				<privacy_tag:usageFilter formFieldName="usageFilter" initialValue="${usageFilter}" />
				
				<spring:message code="usagestatistics668.query.type"/>

			</td>
			<td align="right">
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
			<!--<th align="center"><spring:message code="usagestatistics668.results.void"/></th>-->
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

<!--
<br/>

<b class="boxHeader">
	<spring:message code="usagestatistics668.results.patient"/>
</b>
<div class="box" style="text-align: center">
	<img src="chart.htm?chart=patient&amp;from=${privacy:formatDate(from)}&amp;until=${privacy:formatDate(until)}&amp;usageFilter=${usageFilter.ordinal};width=700&amp;height=200" width="700" height="200" />
</div>

<br/>       
-->
<%@ include file="/WEB-INF/template/footer.jsp"%>