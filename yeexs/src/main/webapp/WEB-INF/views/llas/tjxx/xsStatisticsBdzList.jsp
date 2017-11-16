<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站损失信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tjxx/xsStatisticsBdz/">变电站损失信息列表</a></li>
		<shiro:hasPermission name="tjxx:xsStatisticsBdz:edit"><li><a href="${ctx}/tjxx/xsStatisticsBdz/form">变电站损失信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsStatisticsBdz" action="${ctx}/tjxx/xsStatisticsBdz/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>ym：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>trsubstation_id：</label>
				<form:input path="trsubstationId" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ym</th>
				<th>trsubstation_id</th>
				<th>power_qua</th>
				<th>nonpower_qua</th>
				<th>sale_qua</th>
				<th>waste_qua</th>
				<th>trwaste_qua</th>
				<th>powerrate</th>
				<th>waste_rate</th>
				<th>comp_wrate</th>
				<th>salequa_insdh</th>
				<th>wastequa_insdh</th>
				<th>trwastequa_insdh</th>
				<th>wasterate_insdh</th>
				<th>compwrate_insdh</th>
				<th>yglj</th>
				<th>wglj</th>
				<th>sslj</th>
				<th>sclj</th>
				<shiro:hasPermission name="tjxx:xsStatisticsBdz:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsStatisticsBdz">
			<tr>
				<td><a href="${ctx}/tjxx/xsStatisticsBdz/form?id=${xsStatisticsBdz.id}">
					${xsStatisticsBdz.ym}
				</a></td>
				<td>
					${xsStatisticsBdz.trsubstationId}
				</td>
				<td>
					${xsStatisticsBdz.powerQua}
				</td>
				<td>
					${xsStatisticsBdz.nonpowerQua}
				</td>
				<td>
					${xsStatisticsBdz.saleQua}
				</td>
				<td>
					${xsStatisticsBdz.wasteQua}
				</td>
				<td>
					${xsStatisticsBdz.trwasteQua}
				</td>
				<td>
					${xsStatisticsBdz.powerrate}
				</td>
				<td>
					${xsStatisticsBdz.wasteRate}
				</td>
				<td>
					${xsStatisticsBdz.compWrate}
				</td>
				<td>
					${xsStatisticsBdz.salequaInsdh}
				</td>
				<td>
					${xsStatisticsBdz.wastequaInsdh}
				</td>
				<td>
					${xsStatisticsBdz.trwastequaInsdh}
				</td>
				<td>
					${xsStatisticsBdz.wasterateInsdh}
				</td>
				<td>
					${xsStatisticsBdz.compwrateInsdh}
				</td>
				<td>
					${xsStatisticsBdz.yglj}
				</td>
				<td>
					${xsStatisticsBdz.wglj}
				</td>
				<td>
					${xsStatisticsBdz.sslj}
				</td>
				<td>
					${xsStatisticsBdz.sclj}
				</td>
				<shiro:hasPermission name="tjxx:xsStatisticsBdz:edit"><td>
    				<a href="${ctx}/tjxx/xsStatisticsBdz/form?id=${xsStatisticsBdz.id}">修改</a>
					<a href="${ctx}/tjxx/xsStatisticsBdz/delete?id=${xsStatisticsBdz.id}" onclick="return confirmx('确认要删除该变电站损失信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>