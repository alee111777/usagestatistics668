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
		
	
	
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>