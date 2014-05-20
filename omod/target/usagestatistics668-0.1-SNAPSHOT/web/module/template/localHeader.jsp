<h2><spring:message code="usagestatistics668.title"/></h2>

<ul id="menu">
        <li class="first<c:if test='<%= request.getRequestURI().contains("patientChart") %>'> active</c:if>">
		<a href="patientChart.htm"><spring:message code="usagestatistics668.menu.summary"/></a>
	</li>
	<li class="test<c:if test='<%= request.getRequestURI().contains("accessPatient") %>'> active</c:if>">
		<a href="patientAccess.htm"><spring:message code="usagestatistics668.menu.patient"/></a>
	</li>
	<li class="test<c:if test='<%= request.getRequestURI().contains("accessEncounter") %>'> active</c:if>">
		<a href="accessEncounter.htm"><spring:message code="usagestatistics668.menu.encounter"/></a>
	</li>
	<li class="test<c:if test='<%= request.getRequestURI().contains("accessVisit") %>'> active</c:if>">
		<a href="accessVisit.htm"><spring:message code="usagestatistics668.menu.visit"/></a>
	</li>
</ul>