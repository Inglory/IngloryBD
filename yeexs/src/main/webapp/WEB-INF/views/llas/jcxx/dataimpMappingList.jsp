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
		<li class="active"><a href="${ctx}/jcxx/dataimpMapping/">数据导入规则列表</a></li>
		<shiro:hasPermission name="jcxx:dataimpMapping:edit"><li><a href="${ctx}/jcxx/dataimpMapping/form">数据导入规则添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dataimpMapping" action="${ctx}/jcxx/dataimpMapping/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>规则</th>
				<th>源字段</th>
				<th>目标字段</th>
				<shiro:hasPermission name="jcxx:dataimpMapping:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dataimpMapping">
			<tr>
				<td><a href="${ctx}/jcxx/dataimpMapping/form?id=${dataimpMapping.id}">
					${dataimpMapping.ruleId}
				</a></td>
				<td>
					${dataimpMapping.sourceField}
				</td>
				<td>
					${dataimpMapping.destField}
				</td>
				<shiro:hasPermission name="jcxx:dataimpMapping:edit"><td>
    				<a href="${ctx}/jcxx/dataimpMapping/form?id=${dataimpMapping.id}">修改</a>
					<a href="${ctx}/jcxx/dataimpMapping/delete?id=${dataimpMapping.id}" onclick="return confirmx('确认要删除该数据导入规则吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>