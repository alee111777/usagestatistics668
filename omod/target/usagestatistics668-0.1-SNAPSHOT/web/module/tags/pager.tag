<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="privacy_tag" tagdir="/WEB-INF/tags/module/usagestatistics668" %>

<%@ attribute name="pagingInfo" required="true" type="org.openmrs.module.usagestatistics668.util.PagingInfo" %>

<div style="background-color: #DDD; padding: 2px; overflow: hidden">
	<div style="float: left">
		<privacy_tag:pagerControls pagingInfo="${pagingInfo}" />
	</div>		
	<div style="float: right; margin: 2px">		
		<privacy_tag:pagerInfo pagingInfo="${pagingInfo}" />
	</div>
</div>