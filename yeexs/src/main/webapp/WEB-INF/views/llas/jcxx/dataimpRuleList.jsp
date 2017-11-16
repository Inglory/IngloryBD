<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据导入规则管理</title>
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
		<li class="active"><a href="${ctx}/jcxx/dataimpRule/">数据导入规则列表</a></li>
		<shiro:hasPermission name="jcxx:dataimpRule:edit"><li><a href="${ctx}/jcxx/dataimpRule/form">数据导入规则添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dataimpRule" action="${ctx}/jcxx/dataimpRule/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>导入规则名称：</label>
				<form:input path="ruleName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>源类型：</label>
				<form:select path="sourceType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('data_storage_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>导出源：</label>
				<form:input path="source" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>目的表类型：</label>
				<form:select path="destinationType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('data_storage_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>导入目的表：</label>
				<form:input path="destination" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>导入规则名称</th>
				<th>源类型</th>
				<th>导出源</th>
				<th>目的表类型</th>
				<th>导入目的表</th>
				<shiro:hasPermission name="jcxx:dataimpRule:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dataimpRule">
			<tr>
				<td><a href="${ctx}/jcxx/dataimpRule/form?id=${dataimpRule.id}">
					${dataimpRule.ruleName}
				</a></td>
				<td>
					${fns:getDictLabel(dataimpRule.sourceType, 'data_storage_type', '')}
				</td>
				<td>
					${dataimpRule.source}
				</td>
				<td>
					${fns:getDictLabel(dataimpRule.destinationType, 'data_storage_type', '')}
				</td>
				<td>
					${dataimpRule.destination}
				</td>
				<shiro:hasPermission name="jcxx:dataimpRule:edit"><td>
    				<a href="${ctx}/jcxx/dataimpRule/form?id=${dataimpRule.id}">修改</a>
					<a href="${ctx}/jcxx/dataimpRule/delete?id=${dataimpRule.id}" onclick="return confirmx('确认要删除该数据导入规则吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>