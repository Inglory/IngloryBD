<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>历史线损计算电量管理</title>
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
		<li class="active"><a href="${ctx}/dljs/xsLinewastageHis/">历史线损计算电量列表</a></li>
		<shiro:hasPermission name="dljs:xsLinewastageHis:edit"><li><a href="${ctx}/dljs/xsLinewastageHis/form">历史线损计算电量添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsLinewastageHis" action="${ctx}/dljs/xsLinewastageHis/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>线路名称：</label>
				<form:input path="linename" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsLinewastageHis.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsLinewastageHis.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>线路编号</th>
				<th>线路名称</th>
				<th>起始日期</th>
				<th>结束日期</th>
				<th>销售电量</th>
				<th>销售电量（含四到户）</th>
				<th>实抄电量</th>
				<th>变损电量</th>
				<th>变损电量（含四到户）</th>
				<th>四到户变损电量</th>
				<th>综损电量</th>
				<th>综损电量（含四到户）</th>
				<th>四到户综损电量</th>
				<th>专线线损</th>
				<th>有功合计</th>
				<th>无功合计</th>
				<th>损失电量</th>
				<th>理论变损率</th>
				<th>理论损失率</th>
				<th>理论综损率</th>
				<th>实际损失率</th>
				<th>综合损失率</th>
				<th>损失考核得分</th>
				<th>损失得分</th>
				<th>实际力率</th>
				<th>力率考核得分</th>
				<th>力率得分</th>
				<shiro:hasPermission name="dljs:xsLinewastageHis:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsLinewastageHis">
			<tr>
				<td><a href="${ctx}/dljs/xsLinewastageHis/form?id=${xsLinewastageHis.id}">
					${xsLinewastageHis.lineCode}
				</a></td>
				<td>
					${xsLinewastageHis.linename}
				</td>
				<td>
					<fmt:formatDate value="${xsLinewastageHis.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xsLinewastageHis.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${xsLinewastageHis.saleQua}
				</td>
				<td>
					${xsLinewastageHis.salequaInsdh}
				</td>
				<td>
					${xsLinewastageHis.actExcerpqua}
				</td>
				<td>
					${xsLinewastageHis.trwasteExsdh}
				</td>
				<td>
					${xsLinewastageHis.trwasteInsdh}
				</td>
				<td>
					${xsLinewastageHis.sdhTrwaste}
				</td>
				<td>
					${xsLinewastageHis.comTrwaste}
				</td>
				<td>
					${xsLinewastageHis.comtrwasteInsdh}
				</td>
				<td>
					${xsLinewastageHis.sdhComtrwaste}
				</td>
				<td>
					${xsLinewastageHis.spelineWaste}
				</td>
				<td>
					${xsLinewastageHis.qua1}
				</td>
				<td>
					${xsLinewastageHis.qua2}
				</td>
				<td>
					${xsLinewastageHis.wasteQua}
				</td>
				<td>
					${xsLinewastageHis.theoryTrwaste}
				</td>
				<td>
					${xsLinewastageHis.theoryWrate}
				</td>
				<td>
					${xsLinewastageHis.theoryCompwrate}
				</td>
				<td>
					${xsLinewastageHis.actWrate}
				</td>
				<td>
					${xsLinewastageHis.compWrate}
				</td>
				<td>
					${xsLinewastageHis.wratePrscore}
				</td>
				<td>
					${xsLinewastageHis.wrateScore}
				</td>
				<td>
					${xsLinewastageHis.actPowerrate}
				</td>
				<td>
					${xsLinewastageHis.powerPrscore}
				</td>
				<td>
					${xsLinewastageHis.powerScore}
				</td>
				<shiro:hasPermission name="dljs:xsLinewastageHis:edit"><td>
    				<a href="${ctx}/dljs/xsLinewastageHis/form?id=${xsLinewastageHis.id}">修改</a>
					<a href="${ctx}/dljs/xsLinewastageHis/delete?id=${xsLinewastageHis.id}" onclick="return confirmx('确认要删除该历史线损计算电量吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>