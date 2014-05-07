<!-- Written by: Kirill -->
<%@ include file="/WEB-INF/template/include.jsp"%>

<!--<openmrs:require privilege="View Patient Privacy Records" otherwise="/login.htm" redirect="/module/usagestatistics/users.htm"/>-->

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp" %>
<%@ include file="template/localInclude.jsp" %>

<b class="boxHeader">
	<spring:message code="usagestatistics668.user.title"/>
</b>
<form method="get" class="box">

	<table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<td align="left">
				<spring:message code="usagestatistics668.query.from" />

				<privacy_tag:dateRange fromField="from" fromValue="${from}" untilField="until" untilValue="${until}" />
				
				<spring:message code="usagestatistics668.query.forUser"/>

                                <openmrs_tag:userField formFieldName="userId" searchLabelCode="Encounter.provider.find" initialValue="${model.user.userId}" linkUrl="${pageContext.request.contextPath}/admin/users/user.form" />
				<!--<input style="width: 16px" type="button" class="smallButton" id="clearUserBtn" onclick="document.usagesForm.userId.value='';document.usagesForm.submit();" value="X" />-->
							
                                <spring:message code="usagestatistics668.query.with"/>
				
				<privacy_tag:usageFilter formFieldName="usageFilter" initialValue="${usageFilter}" />
				
				<spring:message code="usagestatistics668.query.type"/>
                                
                                <input type="button" onclick="document.usagesForm.userId.value='';document.usagesForm.submit();" value="Submit" /> 
			</td>
                        <td align="right">
                            <spring:message code="usagestatistics668.quantity.title"/>
                            <privacy_tag:quantityFilter formFieldName="quantityFilter" initialValue="${quantityFilter}" />
                            <!--Add functionality to this submit button-->
                            <input type="button" value="Submit" /> 
                        </td>
<!--			<td align="right">
				<input type="submit" value="<spring:message code="usagestatistics668.query.update"/>" />
				<input type="submit" name="export" value="<spring:message code="usagestatistics668.query.export"/>" />
			</td>-->
		</tr>
	</table>
		
	<table cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<th align="center"><spring:message code="usagestatistics668.results.type"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.date"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.user"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.patient"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.record"/></th>
			<th align="center"><spring:message code="usagestatistics668.results.name"/></th>
			<!--<th align="center"><spring:message code="usagestatistics668.results.void"/></th>-->
		</tr>
                
                <c:forEach items="${userList}" var="row" varStatus="rowStatus" >
                   <tr>
                        <td align="center">${row.access_type}</td>
                        <td align="center">${row.timestamp}</td>
                        <td align="center">${row.user_id}</td>
                        <td align="center">${row.patient_id}</td>
                   </tr>
                </c:forEach>
                
		<c:if test="${empty userList}">
			<tr>
				<td colspan="8" style="padding: 10px; text-align: center"><spring:message code="usagestatistics668.noresults"/></td>
			</tr>
		</c:if>
	</table>
	
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>