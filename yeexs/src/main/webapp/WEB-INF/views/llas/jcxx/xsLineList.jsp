<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>线路管理</title>
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
		<li class="active"><a href="${ctx}/jcxx/xsLine/">线路列表</a></li>
		<shiro:hasPermission name="jcxx:xsLine:edit"><li><a href="${ctx}/jcxx/xsLine/form">线路添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsLine" action="${ctx}/jcxx/xsLine/" method="post" class="breadcrumb form-search">
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
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>线路编号</th>
				<th>线路名称</th>
				<th>变电站</th>
				<th>母线标志</th>
				<th>电压等级</th>
				<th>专线标志</th>
				<th>线路电阻</th>
				<th>空载损耗</th>
				<th>短路损耗</th>
				<th>温度</th>
				<th>K1系数</th>
				<th>K系数</th>
				<th>用电小时数</th>
				<th>倍率</th>
				<th>计数容量</th>
				<th>总容量</th>
				<th>执行力率标准</th>
				<th>计划线损率</th>
				<th>换表标志</th>
				<th>正反向标志</th>
				<th>报表显示标志</th>
				<shiro:hasPermission name="jcxx:xsLine:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsLine">
			<tr>
				<td><a href="${ctx}/jcxx/xsLine/form?id=${xsLine.id}">
					${xsLine.lineCode}
				</a></td>
				<td>
					${xsLine.lineName}
				</td>
				<td>
					${xsLine.trsubstationName}
				</td>
				<td>
					${fns:getDictLabel(xsLine.isparline, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(xsLine.voltage, 'dydj', '')}
				</td>
				<td>
					${fns:getDictLabel(xsLine.isspecline, 'yes_no', '')}
				</td>
				<td>
					${xsLine.resistance}
				</td>
				<td>
					${xsLine.unloadWaste}
				</td>
				<td>
					${xsLine.cirWaste}
				</td>
				<td>
					${xsLine.temperature}
				</td>
				<td>
					${xsLine.k1}
				</td>
				<td>
					${xsLine.k}
				</td>
				<td>
					${xsLine.hours}
				</td>
				<td>
					${xsLine.intefactor}
				</td>
				<td>
					${xsLine.maxcapacity}
				</td>
				<td>
					${xsLine.capacity}
				</td>
				<td>
					${xsLine.powerfactor}
				</td>
				<td>
					${xsLine.scheWrate}
				</td>
				<td>
					${fns:getDictLabel(xsLine.changesign, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(xsLine.prflag, 'ifelse', '')}
				</td>
				<td>
					${xsLine.rptvissign}
				</td>
				<shiro:hasPermission name="jcxx:xsLine:edit"><td>
    				<a href="${ctx}/jcxx/xsLine/form?id=${xsLine.id}">修改</a>
					<a href="${ctx}/jcxx/xsLine/delete?id=${xsLine.id}" onclick="return confirmx('确认要删除该线路吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>