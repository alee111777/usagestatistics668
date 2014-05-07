<ul id="menu">
        <li class="first<c:if test='<%= request.getRequestURI().contains("patientChart") %>'> active</c:if>">
		<a href="patientChart.htm"><spring:message code="usagestatistics668.summarymenu.patient"/></a>
	</li>
	<li class="test<c:if test='<%= request.getRequestURI().contains("encounterChart") %>'> active</c:if>">
		<a href="encounterChart.htm"><spring:message code="usagestatistics668.summarymenu.encounter"/></a>
	</li>
	<li class="test<c:if test='<%= request.getRequestURI().contains("visitChart") %>'> active</c:if>">
		<a href="visitChart.htm"><spring:message code="usagestatistics668.summarymenu.visit"/></a>
	</li>
</ul>