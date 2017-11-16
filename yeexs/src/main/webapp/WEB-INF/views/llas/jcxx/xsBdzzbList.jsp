<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站主变信息管理</title>
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
		<li class="active"><a href="${ctx}/jcxx/xsBdzzb/">变电站主变信息列表</a></li>
		<shiro:hasPermission name="jcxx:xsBdzzb:edit"><li><a href="${ctx}/jcxx/xsBdzzb/form">变电站主变信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsBdzzb" action="${ctx}/jcxx/xsBdzzb/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>主变编号：</label>
				<form:input path="zbbh" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>主变名称：</label>
				<form:input path="zbmc" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>所属变电站：</label>
				<form:select path="ssbdz" class="input-medium">
					<form:option value="" label="请选择变电站"/><form:options items="${bdzList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:select path="zt" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('zt')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>主变编号</th>
				<th>主变名称</th>
				<th>所属变电站</th>
				<th>状态</th>
				<shiro:hasPermission name="jcxx:xsBdzzb:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsBdzzb">
			<tr>
				<td><a href="${ctx}/jcxx/xsBdzzb/form?id=${xsBdzzb.id}">
					${xsBdzzb.zbbh}
				</a></td>
				<td>
					${xsBdzzb.zbmc}
				</td>
				<td>
					${xsBdzzb.trsubstationName}
				</td>
				<td>
					${fns:getDictLabel(xsBdzzb.zt, 'zt', '')}
				</td>
				<shiro:hasPermission name="jcxx:xsBdzzb:edit"><td>
    				<a href="${ctx}/jcxx/xsBdzzb/form?id=${xsBdzzb.id}">修改</a>
					<a href="${ctx}/jcxx/xsBdzzb/delete?id=${xsBdzzb.id}" onclick="return confirmx('确认要删除该变电站主变信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>