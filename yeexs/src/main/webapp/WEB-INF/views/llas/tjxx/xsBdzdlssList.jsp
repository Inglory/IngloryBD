<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站电量损失管理</title>
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
		<li class="active"><a href="${ctx}/tjxx/xsBdzdlss/">变电站电量损失列表</a></li>
		<shiro:hasPermission name="tjxx:xsBdzdlss:edit"><li><a href="${ctx}/tjxx/xsBdzdlss/form">变电站电量损失添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsBdzdlss" action="${ctx}/tjxx/xsBdzdlss/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年月：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsBdzdlss.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsBdzdlss.jsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>变电站：</label>
				<form:select path="bdzid" class="input-medium">
					<form:option value="" label="请选择变电站"/><form:options items="${bdzList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>变电站编号：</label
				<form:input path="bdzbh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>变电站名称：</label>
				<form:input path="bdzmc" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a class="btn btn-primary" href="${ctx}/tjxx/xsBdzdlss/tongji">变电站电量损失统计</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>年月</th>
				<th>起始日期</th>
				<th>结束日期</th>
				<th>变电站编号</th>
				<th>变电站名称</th>
				<th>本月输入</th>
				<th>本月输出</th>
				<th>本月损失</th>
				<th>损失率</th>
				<th>累计输入</th>
				<th>累计输出</th>
				<th>累计损失</th>
				<th>累计损失率</th>
				<th>无功电量</th>
				<th>累计无功</th>
				<th>力率</th>
				<th>累计力率</th>
				<shiro:hasPermission name="tjxx:xsBdzdlss:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsBdzdlss">
			<tr>
				<td><a href="${ctx}/tjxx/xsBdzdlss/form?id=${xsBdzdlss.id}">
					${xsBdzdlss.ym}
				</a></td>
				<td>
					<fmt:formatDate value="${xsBdzdlss.qsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsBdzdlss.jsrq}" pattern="yyyy-MM-dd"/>
				</td>

				<td>
					${xsBdzdlss.bdzbh}
				</td>
				<td>
					${xsBdzdlss.bdzmc}
				</td>
				<td>
					${xsBdzdlss.bysr}
				</td>
				<td>
					${xsBdzdlss.bysc}
				</td>
				<td>
					${xsBdzdlss.byss}
				</td>
				<td>
					${xsBdzdlss.byssl}
				</td>
				<td>
					${xsBdzdlss.ljsr}
				</td>
				<td>
					${xsBdzdlss.ljsc}
				</td>
				<td>
					${xsBdzdlss.ljss}
				</td>
				<td>
					${xsBdzdlss.ljssl}
				</td>
				<td>
					${xsBdzdlss.wgdl}
				</td>
				<td>
					${xsBdzdlss.ljwg}
				</td>
				<td>
					${xsBdzdlss.glys}
				</td>
				<td>
					${xsBdzdlss.ljglys}
				</td>
				<shiro:hasPermission name="tjxx:xsBdzdlss:edit"><td>
    				<a href="${ctx}/tjxx/xsBdzdlss/form?id=${xsBdzdlss.id}">修改</a>
					<a href="${ctx}/tjxx/xsBdzdlss/delete?id=${xsBdzdlss.id}" onclick="return confirmx('确认要删除该变电站电量损失吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>