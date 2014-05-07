<!-- Written by: Michael -->
<%@ include file="/WEB-INF/template/include.jsp"%>

<!--<openmrs:require privilege="View Patient Privacy Records" otherwise="/login.htm" redirect="/module/usagestatistics/users.htm"/>-->

<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp" %>
<%@ include file="template/localInclude.jsp" %>

<b class="boxHeader">
	<spring:message code="usagestatistics668.summary.title"/>
</b>
<form method="get" class="box">
    <%@ include file="template/summaryHeader.jsp" %>
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
		
	
	
</form>
<b class="boxHeader">
	<spring:message code="usagestatistics668.results.patient"/>
</b>
<div class="box" style="text-align: center">
	<img src="chart.htm?chart=patient&amp;from=${privacy:formatDate(from)}&amp;until=${privacy:formatDate(until)}&amp;usageFilter=${usageFilter.ordinal};width=700&amp;height=200" width="700" height="200" />
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>