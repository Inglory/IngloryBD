<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>母线平衡统计管理</title>
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
		<li class="active"><a href="${ctx}/tjxx/xsParlinebalance/">母线平衡统计列表</a></li>
		<shiro:hasPermission name="tjxx:xsParlinebalance:edit"><li><a href="${ctx}/tjxx/xsParlinebalance/form">母线平衡统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsParlinebalance" action="${ctx}/tjxx/xsParlinebalance/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年月：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsParlinebalance.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsParlinebalance.jsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>线路：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label="请选择母线"/>
					<form:options items="${ListMuxian}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>线路名称：</label>
				<form:input path="lineName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>变电站：</label>
				<form:select path="trsubstationId" class="input-medium">
					<form:option value="" label="请选择变电站"/><form:options items="${bdzList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a class="btn btn-primary" href="${ctx}/tjxx/xsParlinebalance/tongji">母线平衡统计</a></li>
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
				<th>线路</th>
				<th>线路编号</th>
				<th>线路名称</th>
				<th>变电站</th>
				<th>有功电量</th>
				<th>销售电量</th>
				<th>损失电量</th>
				<th>不平衡电量</th>
				<th>不平衡率</th>
				<th>电压等级</th>
				<th>有功累计</th>
				<th>无功累计</th>
				<th>损失累计</th>
				<th>销售累计</th>
				<shiro:hasPermission name="tjxx:xsParlinebalance:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsParlinebalance">
			<tr>
				<td><a href="${ctx}/tjxx/xsParlinebalance/form?id=${xsParlinebalance.id}">
					${xsParlinebalance.ym}
				</a></td>
				<td>
					<fmt:formatDate value="${xsParlinebalance.qsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsParlinebalance.jsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(xsParlinebalance.lineId, '', '')}
				</td>
				<td>
					${xsParlinebalance.lineCode}
				</td>
				<td>
					${xsParlinebalance.lineName}
				</td>
				<td>
					${fns:getDictLabel(xsParlinebalance.trsubstationId, '', '')}
				</td>
				<td>
					${xsParlinebalance.powerQua}
				</td>
				<td>
					${xsParlinebalance.saleQua}
				</td>
				<td>
					${xsParlinebalance.wasteQua}
				</td>
				<td>
					${xsParlinebalance.nonbalQua}
				</td>
				<td>
					${xsParlinebalance.nonbalRate}
				</td>
				<td>
					${xsParlinebalance.voltage}
				</td>
				<td>
					${xsParlinebalance.yglj}
				</td>
				<td>
					${xsParlinebalance.wglj}
				</td>
				<td>
					${xsParlinebalance.sslj}
				</td>
				<td>
					${xsParlinebalance.sclj}
				</td>
				<shiro:hasPermission name="tjxx:xsParlinebalance:edit"><td>
    				<a href="${ctx}/tjxx/xsParlinebalance/form?id=${xsParlinebalance.id}">修改</a>
					<a href="${ctx}/tjxx/xsParlinebalance/delete?id=${xsParlinebalance.id}" onclick="return confirmx('确认要删除该母线平衡统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>