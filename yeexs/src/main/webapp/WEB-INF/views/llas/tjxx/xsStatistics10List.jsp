<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>10kV线损统计结果管理</title>
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
		<li class="active"><a href="${ctx}/tjxx/xsStatistics10/">10kV线损统计结果列表</a></li>
		<shiro:hasPermission name="tjxx:xsStatistics10:edit"><li><a href="${ctx}/tjxx/xsStatistics10/form">10kV线损统计结果添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsStatistics10" action="${ctx}/tjxx/xsStatistics10/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年月：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>线路：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>线路名称：</label>
				<form:input path="lineName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsStatistics10.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>线路</th>
				<th>线路编号</th>
				<th>线路名称</th>
				<th>起始日期</th>
				<th>结束日期</th>
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
				<th>损失率（含四到户）</th>
				<th>综合损失率（含四到户）</th>
				<th>力率</th>
				<th>专线线损</th>
				<th>理论变损</th>
				<th>理论损失率</th>
				<th>理论综合损失率</th>
				<th>线损奖罚分</th>
				<th>线损得分</th>
				<th>力率奖罚分</th>
				<th>力率得分</th>
				<th>最终得分</th>
				<th>理论线损率</th>
				<th>线损得分</th>
				<th>最后得分</th>
				<th>有功累计</th>
				<th>无功累计</th>
				<th>损失累计</th>
				<th>销售累计</th>
				<shiro:hasPermission name="tjxx:xsStatistics10:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsStatistics10">
			<tr>
				<td><a href="${ctx}/tjxx/xsStatistics10/form?id=${xsStatistics10.id}">
					${xsStatistics10.ym}
				</a></td>
				<td>
					${fns:getDictLabel(xsStatistics10.lineId, '', '')}
				</td>
				<td>
					${xsStatistics10.lineCode}
				</td>
				<td>
					${xsStatistics10.lineName}
				</td>
				<td>
					<fmt:formatDate value="${xsStatistics10.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xsStatistics10.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${xsStatistics10.powerQua}
				</td>
				<td>
					${xsStatistics10.nonpowerQua}
				</td>
				<td>
					${xsStatistics10.saleQua}
				</td>
				<td>
					${xsStatistics10.wasteQua}
				</td>
				<td>
					${xsStatistics10.trwasteQua}
				</td>
				<td>
					${xsStatistics10.wasteRate}
				</td>
				<td>
					${xsStatistics10.compWrate}
				</td>
				<td>
					${xsStatistics10.salequaInsdh}
				</td>
				<td>
					${xsStatistics10.wastequaInsdh}
				</td>
				<td>
					${xsStatistics10.trwastequaInsdh}
				</td>
				<td>
					${xsStatistics10.wasterateInsdh}
				</td>
				<td>
					${xsStatistics10.compwrateInsdh}
				</td>
				<td>
					${xsStatistics10.powerrate}
				</td>
				<td>
					${xsStatistics10.spelinewaste}
				</td>
				<td>
					${xsStatistics10.theoryTrwaste}
				</td>
				<td>
					${xsStatistics10.theoryWrate}
				</td>
				<td>
					${xsStatistics10.theoryCompwrate}
				</td>
				<td>
					${xsStatistics10.wratePrscore}
				</td>
				<td>
					${xsStatistics10.wrateScore}
				</td>
				<td>
					${xsStatistics10.powerPrscore}
				</td>
				<td>
					${xsStatistics10.powerScore}
				</td>
				<td>
					${xsStatistics10.finalscore}
				</td>
				<td>
					${xsStatistics10.theoryWaste}
				</td>
				<td>
					${xsStatistics10.wrateCheckscore}
				</td>
				<td>
					${xsStatistics10.finalcheckscore}
				</td>
				<td>
					${xsStatistics10.yglj}
				</td>
				<td>
					${xsStatistics10.wglj}
				</td>
				<td>
					${xsStatistics10.sslj}
				</td>
				<td>
					${xsStatistics10.sclj}
				</td>
				<shiro:hasPermission name="tjxx:xsStatistics10:edit"><td>
    				<a href="${ctx}/tjxx/xsStatistics10/form?id=${xsStatistics10.id}">修改</a>
					<a href="${ctx}/tjxx/xsStatistics10/delete?id=${xsStatistics10.id}" onclick="return confirmx('确认要删除该10kV线损统计结果吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>