<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主变电量损失统计管理</title>
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
		<li class="active"><a href="${ctx}/tjxx/xsBdzzbss/">主变电量损失统计列表</a></li>
		<shiro:hasPermission name="tjxx:xsBdzzbss:edit"><li><a href="${ctx}/tjxx/xsBdzzbss/form">主变电量损失统计添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsBdzzbss" action="${ctx}/tjxx/xsBdzzbss/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>年月：</label>
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>起始日期：</label>
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsBdzzbss.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>结束日期：</label>
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${xsBdzzbss.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>主变：</label>
				<form:select path="zbid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>主变编号：</label>
				<form:input path="zbbh" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>主变名称：</label>
				<form:input path="zbmc" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>所属变电站：</label>
				<form:select path="ssbdz" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>年月</th>
				<th>起始日期</th>
				<th>结束日期</th>
				<th>主变</th>
				<th>主变编号</th>
				<th>主变名称</th>
				<th>所属变电站</th>
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
				<shiro:hasPermission name="tjxx:xsBdzzbss:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsBdzzbss">
			<tr>
				<td><a href="${ctx}/tjxx/xsBdzzbss/form?id=${xsBdzzbss.id}">
					${xsBdzzbss.ym}
				</a></td>
				<td>
					<fmt:formatDate value="${xsBdzzbss.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xsBdzzbss.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(xsBdzzbss.zbid, '', '')}
				</td>
				<td>
					${xsBdzzbss.zbbh}
				</td>
				<td>
					${xsBdzzbss.zbmc}
				</td>
				<td>
					${fns:getDictLabel(xsBdzzbss.ssbdz, '', '')}
				</td>
				<td>
					${xsBdzzbss.bysr}
				</td>
				<td>
					${xsBdzzbss.bysc}
				</td>
				<td>
					${xsBdzzbss.byss}
				</td>
				<td>
					${xsBdzzbss.byssl}
				</td>
				<td>
					${xsBdzzbss.ljsr}
				</td>
				<td>
					${xsBdzzbss.ljsc}
				</td>
				<td>
					${xsBdzzbss.ljss}
				</td>
				<td>
					${xsBdzzbss.ljssl}
				</td>
				<td>
					${xsBdzzbss.wgdl}
				</td>
				<td>
					${xsBdzzbss.ljwg}
				</td>
				<td>
					${xsBdzzbss.glys}
				</td>
				<td>
					${xsBdzzbss.ljglys}
				</td>
				<shiro:hasPermission name="tjxx:xsBdzzbss:edit"><td>
    				<a href="${ctx}/tjxx/xsBdzzbss/form?id=${xsBdzzbss.id}">修改</a>
					<a href="${ctx}/tjxx/xsBdzzbss/delete?id=${xsBdzzbss.id}" onclick="return confirmx('确认要删除该主变电量损失统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>