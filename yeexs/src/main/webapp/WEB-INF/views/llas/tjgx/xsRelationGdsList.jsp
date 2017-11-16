<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供电所损失关系管理</title>
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
		<li class="active"><a href="${ctx}/tjgx/xsRelationGds/">供电所损失关系列表</a></li>
		<shiro:hasPermission name="tjgx:xsRelationGds:edit"><li><a href="${ctx}/tjgx/xsRelationGds/form">供电所损失关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsRelationGds" action="${ctx}/tjgx/xsRelationGds/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供电所：</label>
				<form:select path="businessid" class="input-medium">
					<form:option value="" label="请选择供电所"/>
					<form:options items="${gdsList}" itemLabel="mc" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计算线路：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label="请选择线路"/>
					<form:options items="${ListlineAll}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>直供标志：</label>
				<form:radiobuttons path="isDirectsupply" items="${fns:getDictList('ifelse')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供电所</th>
				<th>计算线路</th>
				<th>直供标志</th>
				<shiro:hasPermission name="tjgx:xsRelationGds:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsRelationGds">
			<tr>
				<td><a href="${ctx}/tjgx/xsRelationGds/form?id=${xsRelationGds.id}">
					${xsRelationGds.gdsMc}
				</a></td>
				<td>
					${xsRelationGds.lineName}
				</td>
				<td>
					${fns:getDictLabel(xsRelationGds.isDirectsupply, 'ifelse', '')}
				</td>
				<shiro:hasPermission name="tjgx:xsRelationGds:edit"><td>
    				<a href="${ctx}/tjgx/xsRelationGds/form?id=${xsRelationGds.id}">修改</a>
					<a href="${ctx}/tjgx/xsRelationGds/delete?id=${xsRelationGds.id}" onclick="return confirmx('确认要删除该供电所损失关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>