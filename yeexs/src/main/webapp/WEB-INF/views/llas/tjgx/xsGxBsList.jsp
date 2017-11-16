<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站损失关系管理</title>
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
		<li class="active"><a href="${ctx}/tjgx/xsGxBs/">变电站损失关系列表</a></li>
		<shiro:hasPermission name="tjgx:xsGxBs:edit"><li><a href="${ctx}/tjgx/xsGxBs/form">变电站损失关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsGxBs" action="${ctx}/tjgx/xsGxBs/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计对象：</label>
				<form:select path="tjdx" class="input-medium">
					<form:option value="" label="请选择变电站"/><form:options items="${bdzList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>统计电量类型：</label>
				<form:select path="tjdllx" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tjdllx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计量点：</label>
				<form:select path="jld" class="input-medium">
					<form:option value="" label="请选择线路"/>
					<form:options items="${ListlineAll}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计算电量：</label>
				<form:select path="jsdl" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dllx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>统计对象</th>
				<th>统计电量类型</th>
				<th>计量点</th>
				<th>计算电量</th>
				<th>计算方式</th>
				<shiro:hasPermission name="tjgx:xsGxBs:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsGxBs">
			<tr>
				<td><a href="${ctx}/tjgx/xsGxBs/form?id=${xsGxBs.id}">
					${(xsGxBs.tjdxName}
				</a></td>
				<td>
					${fns:getDictLabel(xsGxBs.tjdllx, 'tjdllx', '')}
				</td>
				<td>
					${xsGxBs.lineName}
				</td>
				<td>
					${fns:getDictLabel(xsGxBs.jsdl, 'dllx', '')}
				</td>
				<td>
					${fns:getDictLabel(xsGxBs.jsfs, 'jsfs', '')}
				</td>
				<shiro:hasPermission name="tjgx:xsGxBs:edit"><td>
    				<a href="${ctx}/tjgx/xsGxBs/form?id=${xsGxBs.id}">修改</a>
					<a href="${ctx}/tjgx/xsGxBs/delete?id=${xsGxBs.id}" onclick="return confirmx('确认要删除该变电站损失关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>