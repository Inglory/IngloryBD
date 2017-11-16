<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>35kV线路统计关系管理</title>
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
		<li class="active"><a href="${ctx}/tjgx/xsRelation35/">35kV线路统计关系列表</a></li>
		<shiro:hasPermission name="tjgx:xsRelation35:edit"><li><a href="${ctx}/tjgx/xsRelation35/form">35kV线路统计关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsRelation35" action="${ctx}/tjgx/xsRelation35/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计线路：</label>
				<form:select path="parlineId" class="input-medium">
					<form:option value="" label="请选择35kV线路"/>
					<form:options items="${Listline35}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>计算线路：</label>
				<form:select path="lineId" class="input-medium">
					<form:option value="" label="请选择6-10kV线路"/>
					<form:options items="${Listline610}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>电量类型：</label>
				<form:select path="quaKind" class="input-medium">
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
				<th>统计线路</th>
				<th>计算线路</th>
				<th>电量类型</th>
				<th>计算方式</th>
				<shiro:hasPermission name="tjgx:xsRelation35:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsRelation35">
			<tr>
				<td><a href="${ctx}/tjgx/xsRelation35/form?id=${xsRelation35.id}">
						${xsRelation35.parlineName}
				</a></td>
				<td>
						${xsRelation35.lineName}
				</td>
				<td>
					${fns:getDictLabel(xsRelation35.quaKind, 'dllx', '')}
				</td>
				<td>
					${fns:getDictLabel(xsRelation35.calKind, 'jsfs', '')}
				</td>
				<shiro:hasPermission name="tjgx:xsRelation35:edit"><td>
    				<a href="${ctx}/tjgx/xsRelation35/form?id=${xsRelation35.id}">修改</a>
					<a href="${ctx}/tjgx/xsRelation35/delete?id=${xsRelation35.id}" onclick="return confirmx('确认要删除该35kV线路统计关系吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>