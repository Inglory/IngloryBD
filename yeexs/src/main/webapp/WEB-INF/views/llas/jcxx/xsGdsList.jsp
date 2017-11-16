<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供电所信息管理</title>
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
		<li class="active"><a href="${ctx}/jcxx/xsGds/">供电所信息列表</a></li>
		<shiro:hasPermission name="jcxx:xsGds:edit"><li><a href="${ctx}/jcxx/xsGds/form">供电所信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsGds" action="${ctx}/jcxx/xsGds/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>供电所编号：</label>
				<form:input path="bh" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>供电所名称：</label>
				<form:input path="mc" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>供电所编号</th>
				<th>供电所名称</th>
				<th>组织机构名称</th>
				<shiro:hasPermission name="jcxx:xsGds:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsGds">
			<tr>
				<td><a href="${ctx}/jcxx/xsGds/form?id=${xsGds.id}">
					${xsGds.bh}
				</a></td>
				<td>
					${xsGds.mc}
				</td>
				<td>
					${xsGds.office.name}
				</td>
				<shiro:hasPermission name="jcxx:xsGds:edit"><td>
    				<a href="${ctx}/jcxx/xsGds/form?id=${xsGds.id}">修改</a>
					<a href="${ctx}/jcxx/xsGds/delete?id=${xsGds.id}" onclick="return confirmx('确认要删除该供电所信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>