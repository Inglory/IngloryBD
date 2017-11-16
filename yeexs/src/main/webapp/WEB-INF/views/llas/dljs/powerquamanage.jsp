<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电力营销业务系统电量导入管理</title>
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
		<li class="active"><a href="${ctx}/dljs/powerquamanage/">电力营销业务系统电量导入管理</a></li>
</ul>
	<form:form id="searchForm" modelAttribute="xsLinewastage" action="${ctx}/dljs/powerquamanage/" method="post" class="breadcrumb form-search">
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
					value="<fmt:formatDate value="${xsLinewastage.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsLinewastage.jsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a class="btn btn-primary" href="${ctx}/dljs/powerquamanage/impDataFromMIS">电力营销业务系统电量导入</a></li>
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
				<th>burgle</th>
				<th>copyerror</th>
				<th>最终得分</th>
				<th>其他得分</th>
				<shiro:hasPermission name="dljs:powerquamanage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsLinewastage">
			<tr>
				<td><a href="${ctx}/dljs/powerquamanage/form?id=${xsLinewastage.id}">
					${xsLinewastage.lineCode}
				</a></td>
				<td>
					${xsLinewastage.linename}
				</td>
				<td>
					<fmt:formatDate value="${xsLinewastage.qsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsLinewastage.jsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${xsLinewastage.saleQua}
				</td>
				<td>
					${xsLinewastage.salequaInsdh}
				</td>
				<td>
					${xsLinewastage.actExcerpqua}
				</td>
				<td>
					${xsLinewastage.trwasteExsdh}
				</td>
				<td>
					${xsLinewastage.trwasteInsdh}
				</td>
				<td>
					${xsLinewastage.sdhTrwaste}
				</td>
				<td>
					${xsLinewastage.comTrwaste}
				</td>
				<td>
					${xsLinewastage.comtrwasteInsdh}
				</td>
				<td>
					${xsLinewastage.sdhComtrwaste}
				</td>
				<td>
					${xsLinewastage.spelineWaste}
				</td>
				<td>
					${xsLinewastage.qua1}
				</td>
				<td>
					${xsLinewastage.qua2}
				</td>
				<td>
					${xsLinewastage.wasteQua}
				</td>
				<td>
					${xsLinewastage.theoryTrwaste}
				</td>
				<td>
					${xsLinewastage.theoryWrate}
				</td>
				<td>
					${xsLinewastage.theoryCompwrate}
				</td>
				<td>
					${xsLinewastage.actWrate}
				</td>
				<td>
					${xsLinewastage.compWrate}
				</td>
				<td>
					${xsLinewastage.wratePrscore}
				</td>
				<td>
					${xsLinewastage.wrateScore}
				</td>
				<td>
					${xsLinewastage.actPowerrate}
				</td>
				<td>
					${xsLinewastage.powerPrscore}
				</td>
				<td>
					${xsLinewastage.powerScore}
				</td>
				<td>
					${xsLinewastage.burgle}
				</td>
				<td>
					${xsLinewastage.copyerror}
				</td>
				<td>
					${xsLinewastage.finalscore}
				</td>
				<td>
					${xsLinewastage.others}
				</td>
				<shiro:hasPermission name="dljs:powerquamanage:edit"><td>
    				<a href="${ctx}/dljs/powerquamanage/form?id=${xsLinewastage.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>