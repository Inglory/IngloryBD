<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>历史线路信息管理</title>
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
		<li class="active"><a href="${ctx}/jcxx/xsLineHis/">历史线路信息列表</a></li>
		<shiro:hasPermission name="jcxx:xsLineHis:edit"><li><a href="${ctx}/jcxx/xsLineHis/form">历史线路信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsLineHis" action="${ctx}/jcxx/xsLineHis/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>线路名称：</label>
				<form:input path="lineName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>变电站名称：</label>
				<form:input path="trsubstationName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>起码日期：</label>
				<input name="qmdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsLineHis.qmdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>止码日期：</label>
				<input name="zmdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsLineHis.zmdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>变电站名称</th>
				<th>母线标志</th>
				<th>电压等级</th>
				<th>专线标志</th>
				<th>正向有功起码</th>
				<th>正向有功止码</th>
				<th>正向有功追补</th>
				<th>正向有功换表</th>
				<th>正向有功抄见</th>
				<th>正向无功起码</th>
				<th>正向无功止码</th>
				<th>正向无功追补</th>
				<th>正向无功换表</th>
				<th>正向无功抄见</th>
				<th>正向有功合计</th>
				<th>正向无功合计</th>
				<th>反向有功起码</th>
				<th>反向有功止码</th>
				<th>反向有功追补</th>
				<th>反向有功换表</th>
				<th>反向有功抄见</th>
				<th>反向有功合计</th>
				<th>反向无功起码</th>
				<th>反向无功止码</th>
				<th>反向无功追补</th>
				<th>反向无功换表</th>
				<th>反向无功抄见</th>
				<th>反向无功合计</th>
				<th>有功合计</th>
				<th>无功合计</th>
				<th>线路电阻</th>
				<th>空载损耗</th>
				<th>短路损耗</th>
				<th>温度</th>
				<th>K1系数</th>
				<th>K系数</th>
				<th>用电小时数</th>
				<th>倍率</th>
				<th>计数容量</th>
				<th>总容量</th>
				<th>执行力率标准</th>
				<th>计划线损率</th>
				<th>换表标志</th>
				<th>正反向标志</th>
				<th>起码日期</th>
				<th>止码日期</th>
				<th>报表显示标志</th>
				<shiro:hasPermission name="jcxx:xsLineHis:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsLineHis">
			<tr>
				<td><a href="${ctx}/jcxx/xsLineHis/form?id=${xsLineHis.id}">
					${xsLineHis.lineCode}
				</a></td>
				<td>
					${xsLineHis.lineName}
				</td>
				<td>
					${xsLineHis.trsubstationName}
				</td>
				<td>
					${fns:getDictLabel(xsLineHis.isparline, 'ifelse', '')}
				</td>
				<td>
					${fns:getDictLabel(xsLineHis.voltage, 'dydj', '')}
				</td>
				<td>
					${fns:getDictLabel(xsLineHis.isspecline, 'ifelse', '')}
				</td>
				<td>
					${xsLineHis.fpSvalue}
				</td>
				<td>
					${xsLineHis.fpEvalue}
				</td>
				<td>
					${xsLineHis.fpSubjoinqua}
				</td>
				<td>
					${xsLineHis.fpChmeter}
				</td>
				<td>
					${xsLineHis.fpExcerpqua}
				</td>
				<td>
					${xsLineHis.fupSvalue}
				</td>
				<td>
					${xsLineHis.fupEvalue}
				</td>
				<td>
					${xsLineHis.fupSubjoinqua}
				</td>
				<td>
					${xsLineHis.fupChmeter}
				</td>
				<td>
					${xsLineHis.fupExcerpqua}
				</td>
				<td>
					${xsLineHis.fpTotalqua}
				</td>
				<td>
					${xsLineHis.fupTotalqua}
				</td>
				<td>
					${xsLineHis.rpSvalue}
				</td>
				<td>
					${xsLineHis.rpEvalue}
				</td>
				<td>
					${xsLineHis.rpSubjoinqua}
				</td>
				<td>
					${xsLineHis.rpChmeter}
				</td>
				<td>
					${xsLineHis.rpExcerpqua}
				</td>
				<td>
					${xsLineHis.rpTotalqua}
				</td>
				<td>
					${xsLineHis.rupSvalue}
				</td>
				<td>
					${xsLineHis.rupEvalue}
				</td>
				<td>
					${xsLineHis.rupSubjoinqua}
				</td>
				<td>
					${xsLineHis.rupChmeter}
				</td>
				<td>
					${xsLineHis.rupExcerpqua}
				</td>
				<td>
					${xsLineHis.rupTotalqua}
				</td>
				<td>
					${xsLineHis.pTotalqua}
				</td>
				<td>
					${xsLineHis.upTotalqua}
				</td>
				<td>
					${xsLineHis.resistance}
				</td>
				<td>
					${xsLineHis.unloadWaste}
				</td>
				<td>
					${xsLineHis.cirWaste}
				</td>
				<td>
					${xsLineHis.temperature}
				</td>
				<td>
					${xsLineHis.k1}
				</td>
				<td>
					${xsLineHis.k}
				</td>
				<td>
					${xsLineHis.hours}
				</td>
				<td>
					${xsLineHis.intefactor}
				</td>
				<td>
					${xsLineHis.maxcapacity}
				</td>
				<td>
					${xsLineHis.capacity}
				</td>
				<td>
					${xsLineHis.powerfactor}
				</td>
				<td>
					${xsLineHis.scheWrate}
				</td>
				<td>
					${fns:getDictLabel(xsLineHis.changesign, 'ifelse', '')}
				</td>
				<td>
					${fns:getDictLabel(xsLineHis.prflag, 'ifelse', '')}
				</td>
				<td>
					<fmt:formatDate value="${xsLineHis.qmdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xsLineHis.zmdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(xsLineHis.rptvissign, '', '')}
				</td>
				<shiro:hasPermission name="jcxx:xsLineHis:edit"><td>
    				<a href="${ctx}/jcxx/xsLineHis/form?id=${xsLineHis.id}">修改</a>
					<a href="${ctx}/jcxx/xsLineHis/delete?id=${xsLineHis.id}" onclick="return confirmx('确认要删除该历史线路信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>