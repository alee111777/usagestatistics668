<!-- Written by: Kirill -->
<%@ include file="/WEB-INF/template/include.jsp"%>

<!--<openmrs:require privilege="View Patient Privacy Records" otherwise="/login.htm" redirect="/module/usagestatistics/users.htm"/>-->

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp" %>
<%@ include file="template/localInclude.jsp" %>

<b class="boxHeader">
	<spring:message code="usagestatistics668.encounter.title"/>
</b>
<form method="get" class="box">

	<table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<td align="left">
				<spring:message code="usagestatistics668.query.from" />

				<privacy_tag:dateRange fromField="from" fromValue="${from}" untilField="until" untilValue="${until}" />
				
				<spring:message code="usagestatistics668.query.forEncounter"/>
                                <openmrs_tag:encounterField formFieldName="encounterId" searchLabelCode="Patient.find" initialValue="${model.obj.encounterId}" linkUrl="${pageContext.request.contextPath}/admin/encounters/encounter.form" />
                                <input style="width: 16px" type="button" class="smallButton" id="clearUserBtn" onclick="document.usagesForm.patientId.value='';document.usagesForm.submit();" value="X" />
				
                                <spring:message code="usagestatistics668.query.with"/>
				
				<privacy_tag:usageFilter formFieldName="usageFilter" initialValue="${usageFilter}" />
				
				<spring:message code="usagestatistics668.query.type"/>
			</td>
			<td align="right">
				<input type="submit" value="<spring:message code="usagestatistics668.query.update"/>" />
				<input type="submit" name="export" value="<spring:message code="usagestatistics668.query.export"/>" />
			</td>
		</tr>
	</table>
		
	<table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<th align="center"><spring:message code="usagestatistics668.results.type"/></th>
			<th><spring:message code="usagestatistics668.results.date"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.user"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.patient"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.encounter"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.void"/></th>
		</tr>

                <c:forEach items="${encounterList}" var="row" varStatus="rowStatus" >
                   <tr>
                   <td align="center">${row.access_type}</td>
                   <td align="center">${row.timestamp}</td>
                   <td align="center">${userList[rowStatus.index]}</td>
                   <td align="center">${patientList[rowStatus.index]}</td>
                   <td>n/a</td>
                   <td>n/a</td>
                   <tr>
                </c:forEach>
                
                <br/>${userList[rowNum]}<br/>
                
		<c:if test="${empty encounterList}">
			<tr>
				<td colspan="8" style="padding: 10px; text-align: center"><spring:message code="usagestatistics668.noresults"/></td>
			</tr>
		</c:if>
	</table>
	
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>