<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>10kV线损统计关系管理</title>
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
		<li class="active"><a href="${ctx}/tjgx/xsRelation10/">10kV线损统计关系列表</a></li>
		<shiro:hasPermission name="tjgx:xsRelation10:edit"><li><a href="${ctx}/tjgx/xsRelation10/form">10kV线损统计关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsRelation10" action="${ctx}/tjgx/xsRelation10/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>线路名称（不计算）：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>线路名称（不计算）</th>
				<shiro:hasPermission name="tjgx:xsRelation10:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsRelation10">
			<tr>
				<td><a href="${ctx}/tjgx/xsRelation10/form?id=${xsRelation10.id}">
					${xsRelation10.lineCode}
				</a></td>
				<td>
					${fns:getDictLabel(xsRelation10.lineId, '', '')}
				</td>
				<shiro:hasPermission name="tjgx:xsRelation10:edit"><td>
    				<a href="${ctx}/tjgx/xsRelation10/form?id=${xsRelation10.id}">修改</a>
					<a href="${ctx}/tjgx/xsRelation10/delete?id=${xsRelation10.id}" onclick="return confirmx('确认要删除该10kV线损统计关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>