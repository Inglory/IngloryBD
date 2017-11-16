<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>35kV线损统计结果管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tjxx/xsStatistics35/">35kV线损统计结果列表</a></li>
		<li class="active"><a href="${ctx}/tjxx/xsStatistics35/form?id=${xsStatistics35.id}">35kV线损统计结果<shiro:hasPermission name="tjxx:xsStatistics35:edit">${not empty xsStatistics35.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tjxx:xsStatistics35:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsStatistics35" action="${ctx}/tjxx/xsStatistics35/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年月：</label>
			<div class="controls">
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起始日期：</label>
			<div class="controls">
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsStatistics35.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsStatistics35.jsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路：</label>
			<div class="controls">
				<form:select path="lineId" class="input-xlarge ">
					<form:option value="" label="请选择35kV线路"/>
					<form:options items="${Listline35}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路编号：</label>
			<div class="controls">
				<form:input path="lineCode" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路名称：</label>
			<div class="controls">
				<form:input path="linename" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有功电量：</label>
			<div class="controls">
				<form:input path="powerQua" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无功电量：</label>
			<div class="controls">
				<form:input path="nonpowerQua" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售电量：</label>
			<div class="controls">
				<form:input path="saleQua" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失电量：</label>
			<div class="controls">
				<form:input path="wasteQua" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变损电量：</label>
			<div class="controls">
				<form:input path="trwasteQua" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线损率：</label>
			<div class="controls">
				<form:input path="wasteRate" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合线损率：</label>
			<div class="controls">
				<form:input path="compWrate" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售电量（含四到户）：</label>
			<div class="controls">
				<form:input path="salequaInsdh" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失电量（含四到户）：</label>
			<div class="controls">
				<form:input path="wastequaInsdh" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变损电量（含四到户）：</label>
			<div class="controls">
				<form:input path="trwastequaInsdh" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线损率（含四到户）：</label>
			<div class="controls">
				<form:input path="wasterateInsdh" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合线损率（含四到户）：</label>
			<div class="controls">
				<form:input path="compwrateInsdh" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">力率：</label>
			<div class="controls">
				<form:input path="powerrate" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有功累计：</label>
			<div class="controls">
				<form:input path="yglj" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无功累计：</label>
			<div class="controls">
				<form:input path="wglj" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失累计：</label>
			<div class="controls">
				<form:input path="sslj" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售累计：</label>
			<div class="controls">
				<form:input path="sclj" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="tjxx:xsStatistics35:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>