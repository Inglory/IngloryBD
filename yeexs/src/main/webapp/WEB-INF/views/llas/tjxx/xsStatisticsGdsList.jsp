<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供电所电量损失统计管理</title>
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
		<li class="active"><a href="${ctx}/tjxx/xsStatisticsGds/">供电所电量损失统计列表</a></li>
		<shiro:hasPermission name="tjxx:xsStatisticsGds:edit"><li><a href="${ctx}/tjxx/xsStatisticsGds/form">供电所电量损失统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsStatisticsGds" action="${ctx}/tjxx/xsStatisticsGds/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年月：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>供电所：</label>
				<form:select path="businessid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>供电所编号：</label>
				<form:input path="gdsbh" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>供电所名称：</label>
				<form:input path="gdsmc" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsStatisticsGds.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsStatisticsGds.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>供电所</th>
				<th>供电所编号</th>
				<th>供电所名称</th>
				<th>起始日期</th>
				<th>结束日期</th>
				<th>是否直供</th>
				<th>有功电量</th>
				<th>无功电量</th>
				<th>销售电量</th>
				<th>损失电量</th>
				<th>变损电量</th>
				<th>力率</th>
				<th>损失率</th>
				<th>综合损失率</th>
				<th>销售电量（含四到户）</th>
				<th>损失电量（含四到户）</th>
				<th>变损电量（含四到户）</th>
				<th>损失率（含四到户）</th>
				<th>综合损失率（含四到户）</th>
				<shiro:hasPermission name="tjxx:xsStatisticsGds:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsStatisticsGds">
			<tr>
				<td><a href="${ctx}/tjxx/xsStatisticsGds/form?id=${xsStatisticsGds.id}">
					${xsStatisticsGds.ym}
				</a></td>
				<td>
					${fns:getDictLabel(xsStatisticsGds.businessid, '', '')}
				</td>
				<td>
					${xsStatisticsGds.gdsbh}
				</td>
				<td>
					${xsStatisticsGds.gdsmc}
				</td>
				<td>
					<fmt:formatDate value="${xsStatisticsGds.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xsStatisticsGds.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${xsStatisticsGds.isIncludeds}
				</td>
				<td>
					${xsStatisticsGds.powerQua}
				</td>
				<td>
					${xsStatisticsGds.nonpowerQua}
				</td>
				<td>
					${xsStatisticsGds.saleQua}
				</td>
				<td>
					${xsStatisticsGds.wasteQua}
				</td>
				<td>
					${xsStatisticsGds.trwasteQua}
				</td>
				<td>
					${xsStatisticsGds.powerrate}
				</td>
				<td>
					${xsStatisticsGds.wasteRate}
				</td>
				<td>
					${xsStatisticsGds.compWrate}
				</td>
				<td>
					${xsStatisticsGds.salequaInsdh}
				</td>
				<td>
					${xsStatisticsGds.wastequaInsdh}
				</td>
				<td>
					${xsStatisticsGds.trwastequaInsdh}
				</td>
				<td>
					${xsStatisticsGds.wasterateInsdh}
				</td>
				<td>
					${xsStatisticsGds.compwrateInsdh}
				</td>
				<shiro:hasPermission name="tjxx:xsStatisticsGds:edit"><td>
    				<a href="${ctx}/tjxx/xsStatisticsGds/form?id=${xsStatisticsGds.id}">修改</a>
					<a href="${ctx}/tjxx/xsStatisticsGds/delete?id=${xsStatisticsGds.id}" onclick="return confirmx('确认要删除该供电所电量损失统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>