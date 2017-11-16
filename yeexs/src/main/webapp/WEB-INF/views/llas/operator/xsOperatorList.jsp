<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>制表人员信息管理</title>
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
		<li class="active"><a href="${ctx}/operator/xsOperator/">制表人员信息列表</a></li>
		<shiro:hasPermission name="operator:xsOperator:edit"><li><a href="${ctx}/operator/xsOperator/form">制表人员信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsOperator" action="${ctx}/operator/xsOperator/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>制表人：</label>
				<form:input path="lister" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsOperator.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsOperator.jsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>负责人</th>
				<th>制表人</th>
				<th>制表日期</th>
				<th>用电时间</th>
				<th>年月</th>
				<th>起始日期</th>
				<th>结束日期</th>
				<th>结存标志</th>
				<shiro:hasPermission name="operator:xsOperator:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsOperator">
			<tr>
				<td><a href="${ctx}/operator/xsOperator/form?id=${xsOperator.id}">
					${xsOperator.serialNumber}
				</a></td>
				<td>
					${xsOperator.principal}
				</td>
				<td>
					${xsOperator.lister}
				</td>
				<td>
					${xsOperator.day}
				</td>
				<td>
					${xsOperator.hours}
				</td>
				<td>
					${xsOperator.ym}
				</td>
				<td>
					<fmt:formatDate value="${xsOperator.qsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsOperator.jsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')}
				</td>
				<shiro:hasPermission name="operator:xsOperator:edit"><td>
    				<a href="${ctx}/operator/xsOperator/form?id=${xsOperator.id}">修改</a>
					<a href="${ctx}/operator/xsOperator/delete?id=${xsOperator.id}" onclick="return confirmx('确认要删除该制表人员信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>