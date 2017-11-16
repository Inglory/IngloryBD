<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计区间管理</title>
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
		<li class="active"><a href="${ctx}/llas/tjqjManage/">统计区间列表</a></li>
		<shiro:hasPermission name="llas:tjqjManage:edit">
		<%-- <li><a href="${ctx}/llas/tjqjManage/form">新建统计区间</a></li> --%>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xsOperator" action="${ctx}/llas/tjqjManage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>负责人：</label>
				<form:input path="principal" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>制表人：</label>
				<form:input path="lister" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>负责人</th>
				<th>制表人</th>
				<th>制表日期</th>
				<th>用电时间</th>
				<th>年月</th>
				<th>起始日期</th>
				<th>结束日期</th>
				<th>统计状态</th>
				<shiro:hasPermission name="llas:tjqjManage:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xsOperator">
			<tr>
				<td>
					${xsOperator.serialNumber}
				</td>
				<td>
					${xsOperator.principal}
				</td>
				<td>
					${xsOperator.lister}
				</td>
				<td>
					${xsOperator.day}
				</td>
				<td>
					${xsOperator.hours}
				</td>
				<td>
					${xsOperator.ym}
				</td>
				<td>
					<fmt:formatDate value="${xsOperator.qsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${xsOperator.jsrq}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')=='未统计'}"><font color="green">
						${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')}  	
						</font>
						</c:when>
						<c:when test="${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')=='已统计'}"><font color="red">
						${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')}  	
						</font>
						</c:when>
						<c:otherwise>   
 						${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')}  							
  						</c:otherwise> 
					</c:choose>
				</td>
				<shiro:hasPermission name="llas:tjqjManage:edit">
				<td>
				<c:choose>
					<c:when test="${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')=='未统计'}">
					<a href="${ctx}/llas/tjqjManage/form?id=${xsOperator.id}">修改</a>
					<a href="${ctx}/llas/tjqjManage/delete?id=${xsOperator.id}" onclick="return confirmx('确认要删除该统计区间信息吗？', this.href)">删除</a>
					</c:when>
					<c:when test="${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')=='已结存'}">
					<a href="${ctx}/llas/tjqjManage/newtjqj?id=${xsOperator.id}">新增下一阶段统计区间</a>
					</c:when>
					<c:when test="${fns:getDictLabel(xsOperator.jcbz, 'jcbz', '')=='已统计'}">
					<a href="${ctx}/llas/tjqjManage/sjjc?id=${xsOperator.id}">数据结存</a>
					</c:when>
				</c:choose>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>