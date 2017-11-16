<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>35kV线损统计结果管理</title>
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
		<li class="active"><a href="${ctx}/tjxx/xsStatistics35/">35kV线损统计结果列表</a></li>
		<shiro:hasPermission name="tjxx:xsStatistics35:edit"><li><a href="${ctx}/tjxx/xsStatistics35/form">35kV线损统计结果添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsStatistics35" action="${ctx}/tjxx/xsStatistics35/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年月：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsStatistics35.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsStatistics35.jsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>线路：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label="请选择35kV线路"/>
					<form:options items="${Listline35}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>线路名称：</label>
				<form:input path="linename" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a class="btn btn-primary" href="${ctx}/tjxx/xsStatistics35/tongji">35kV线损统计</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>起始日期</th>
				<th>结束日期</th>

				<th>线路编号</th>
				<th>线路名称</th>
				<th>有功电量</th>
				<th>无功电量</th>
				<th>销售电量</th>
				<th>损失电量</th>
				<th>变损电量</th>
				<th>线损率</th>
				<th>综合线损率</th>
				<th>销售电量（含四到户）</th>
				<th>损失电量（含四到户）</th>
				<th>变损电量（含四到户）</th>
				<th>线损率（含四到户）</th>
				<th>综合线损率（含四到户）</th>
				<th>力率</th>
				<th>有功累计</th>
				<th>无功累计</th>
				<th>损失累计</th>
				<th>销售累计</th>
				<shiro:hasPermission name="tjxx:xsStatistics35:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsStatistics35">
			<tr>
				<td><a href="${ctx}/tjxx/xsStatistics35/form?id=${xsStatistics35.id}">
					${xsStatistics35.ym}
				</a></td>
				<td>
					<fmt:formatDate value="${xsStatistics35.qsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsStatistics35.jsrq}" pattern="yyyy-MM-dd"/>
				</td>

				<td>
					${xsStatistics35.lineCode}
				</td>
				<td>
					${xsStatistics35.linename}
				</td>
				<td>
					${xsStatistics35.powerQua}
				</td>
				<td>
					${xsStatistics35.nonpowerQua}
				</td>
				<td>
					${xsStatistics35.saleQua}
				</td>
				<td>
					${xsStatistics35.wasteQua}
				</td>
				<td>
					${xsStatistics35.trwasteQua}
				</td>
				<td>
					${xsStatistics35.wasteRate}
				</td>
				<td>
					${xsStatistics35.compWrate}
				</td>
				<td>
					${xsStatistics35.salequaInsdh}
				</td>
				<td>
					${xsStatistics35.wastequaInsdh}
				</td>
				<td>
					${xsStatistics35.trwastequaInsdh}
				</td>
				<td>
					${xsStatistics35.wasterateInsdh}
				</td>
				<td>
					${xsStatistics35.compwrateInsdh}
				</td>
				<td>
					${xsStatistics35.powerrate}
				</td>
				<td>
					${xsStatistics35.yglj}
				</td>
				<td>
					${xsStatistics35.wglj}
				</td>
				<td>
					${xsStatistics35.sslj}
				</td>
				<td>
					${xsStatistics35.sclj}
				</td>
				<shiro:hasPermission name="tjxx:xsStatistics35:edit"><td>
    				<a href="${ctx}/tjxx/xsStatistics35/form?id=${xsStatistics35.id}">修改</a>
					<a href="${ctx}/tjxx/xsStatistics35/delete?id=${xsStatistics35.id}" onclick="return confirmx('确认要删除该35kV线损统计结果吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>