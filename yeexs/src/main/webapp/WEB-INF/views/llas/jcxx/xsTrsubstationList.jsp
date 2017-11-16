<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站信息管理</title>
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
		<li class="active"><a href="${ctx}/jcxx/xsTrsubstation/">变电站信息列表</a></li>
		<shiro:hasPermission name="jcxx:xsTrsubstation:edit"><li><a href="${ctx}/jcxx/xsTrsubstation/form">变电站信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsTrsubstation" action="${ctx}/jcxx/xsTrsubstation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>变电站编号：</label>
				<form:input path="trsubstationCode" htmlEscape="false" maxlength="8" class="input-medium"/>
			</li>
			<li><label>变电站名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="60" class="input-medium"/>
			</li>
			<li><label>电压等级：</label>
				<form:select path="voltargrade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dydj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>变电站编号</th>
				<th>变电站名称</th>
				<th>简称</th>
				<th>电压等级</th>
				<shiro:hasPermission name="jcxx:xsTrsubstation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsTrsubstation">
			<tr>
				<td><a href="${ctx}/jcxx/xsTrsubstation/form?id=${xsTrsubstation.id}">
					${xsTrsubstation.trsubstationCode}
				</a></td>
				<td>
					${xsTrsubstation.name}
				</td>
				<td>
					${xsTrsubstation.shortname}
				</td>
				<td>
					${fns:getDictLabel(xsTrsubstation.voltargrade, 'dydj', '')}
				</td>
				<shiro:hasPermission name="jcxx:xsTrsubstation:edit"><td>
    				<a href="${ctx}/jcxx/xsTrsubstation/form?id=${xsTrsubstation.id}">修改</a>
					<a href="${ctx}/jcxx/xsTrsubstation/delete?id=${xsTrsubstation.id}" onclick="return confirmx('确认要删除该变电站信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>