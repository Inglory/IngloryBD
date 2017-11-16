<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>母线平衡关系管理</title>
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
		<li class="active"><a href="${ctx}/tjgx/xsRelationParline/">母线平衡关系列表</a></li>
		<shiro:hasPermission name="tjgx:xsRelationParline:edit"><li><a href="${ctx}/tjgx/xsRelationParline/form">母线平衡关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsRelationParline" action="${ctx}/tjgx/xsRelationParline/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计线路：</label>
				<form:select path="parlineId" class="input-medium">
					<form:option value="" label="请选择母线"/>
					<form:options items="${ListMuxian}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计算线路：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label="请选择线路"/>
					<form:options items="${ListlineAll}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>电量类型：</label>
				<form:select path="quaKind" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('srsc')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>统计线路</th>
				<th>计算线路</th>
				<th>电量类型</th>
				<th>计算方式</th>
				<shiro:hasPermission name="tjgx:xsRelationParline:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsRelationParline">
			<tr>
				<td><a href="${ctx}/tjgx/xsRelationParline/form?id=${xsRelationParline.id}">
					${xsRelationParline.parlineName}
				</a></td>
				<td>
					${xsRelationParline.lineName}
				</td>
				<td>
					${fns:getDictLabel(xsRelationParline.quaKind, 'dllx', '')}
				</td>
				<td>
					${fns:getDictLabel(xsRelationParline.calKind, 'jsfs', '')}
				</td>
				<shiro:hasPermission name="tjgx:xsRelationParline:edit"><td>
    				<a href="${ctx}/tjgx/xsRelationParline/form?id=${xsRelationParline.id}">修改</a>
					<a href="${ctx}/tjgx/xsRelationParline/delete?id=${xsRelationParline.id}" onclick="return confirmx('确认要删除该母线平衡关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>