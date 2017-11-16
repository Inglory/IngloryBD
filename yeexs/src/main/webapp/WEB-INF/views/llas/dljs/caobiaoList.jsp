<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抄表管理</title>
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
		<li class="active"><a href="${ctx}/dljs/xsLine/">用电采集系统表码导入</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="xsLine" action="${ctx}/dljs/xsLine/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>线路编号：</label>
				<form:input path="lineCode" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>线路名称：</label>
				<form:input path="lineName" htmlEscape="false" maxlength="40" class="input-medium"/>
			</li>
			<li><label>变电站：</label>
				<form:select path="trsubstationId" class="input-xlarge">
					<form:option value="" label="请选择变电站"/><form:options items="${bdzList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>母线标志：</label>
				<form:radiobuttons path="isparline" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>电压等级：</label>
				<form:select path="voltage" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dydj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>专线标志：</label>
				<form:radiobuttons path="isspecline" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>换表标志：</label>
				<form:radiobuttons path="changesign" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>正反向标志：</label>
				<form:radiobuttons path="prflag" items="${fns:getDictList('ifelse')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><a class="btn btn-primary" href="${ctx}/dljs/xsLine/impDataFromEDAS">用电采集系统表码导入</a></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>线路编号</th>
				<th>线路名称</th>
				<th>起码日期</th>
				<th>止码日期</th>
				<th>正向有功起码</th>
				<th>正向有功止码</th>
				<th>正向有功追补</th>
				<th>正向有功换表</th>
				<th>正向有功抄见</th>
				<th>正向无功起码</th>
				<th>正向无功止码</th>
				<th>正向无功追补</th>
				<th>正向无功换表</th>
				<th>正向无功抄见</th>
				<th>正向有功合计</th>
				<th>正向无功合计</th>
				<th>反向有功起码</th>
				<th>反向有功止码</th>
				<th>反向有功追补</th>
				<th>反向有功换表</th>
				<th>反向有功抄见</th>
				<th>反向无功起码</th>
				<th>反向无功止码</th>
				<th>反向无功追补</th>
				<th>反向无功换表</th>
				<th>反向无功抄见</th>
				<th>反向有功合计</th>
				<th>反向无功合计</th>
				<th>有功合计</th>
				<th>无功合计</th>
				<shiro:hasPermission name="dljs:xsLine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsLine">
			<tr>
				<td><a href="${ctx}/dljs/xsLine/form?id=${xsLine.id}">
					${xsLine.lineCode}
				</a></td>
				<td>
					${xsLine.lineName}
				</td>
				<td>
					<fmt:formatDate value="${xsLine.qmdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsLine.zmdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${xsLine.fpSvalue}
				</td>
				<td>
					${xsLine.fpEvalue}
				</td>
				<td>
					${xsLine.fpSubjoinqua}
				</td>
				<td>
					${xsLine.fpChmeter}
				</td>
				<td>
					${xsLine.fpExcerpqua}
				</td>
				<td>
					${xsLine.fupSvalue}
				</td>
				<td>
					${xsLine.fupEvalue}
				</td>
				<td>
					${xsLine.fupSubjoinqua}
				</td>
				<td>
					${xsLine.fupChmeter}
				</td>
				<td>
					${xsLine.fupExcerpqua}
				</td>
				<td>
					${xsLine.fpTotalqua}
				</td>
				<td>
					${xsLine.fupTotalqua}
				</td>
				<td>
					${xsLine.rpSvalue}
				</td>
				<td>
					${xsLine.rpEvalue}
				</td>
				<td>
					${xsLine.rpSubjoinqua}
				</td>
				<td>
					${xsLine.rpChmeter}
				</td>
				<td>
					${xsLine.rpExcerpqua}
				</td>
				<td>
					${xsLine.rupSvalue}
				</td>
				<td>
					${xsLine.rupEvalue}
				</td>
				<td>
					${xsLine.rupSubjoinqua}
				</td>
				<td>
					${xsLine.rupChmeter}
				</td>
				<td>
					${xsLine.rupExcerpqua}
				</td>
				<td>
					${xsLine.rpTotalqua}
				</td>
				<td>
					${xsLine.rupTotalqua}
				</td>
				<td>
						${xsLine.pTotalqua}
				</td>
				<td>
					${xsLine.upTotalqua}
				</td>
				<shiro:hasPermission name="dljs:xsLine:edit"><td>
    				<a href="${ctx}/dljs/xsLine/form?id=${xsLine.id}">修改</a>
					<%-- <a href="${ctx}/dljs/xsLine/delete?id=${xsLine.id}" onclick="return confirmx('确认要删除该抄表吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>