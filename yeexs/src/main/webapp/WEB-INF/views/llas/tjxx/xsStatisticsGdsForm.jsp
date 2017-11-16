<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供电所电量损失统计管理</title>
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
		<li><a href="${ctx}/tjxx/xsStatisticsGds/">供电所电量损失统计列表</a></li>
		<li class="active"><a href="${ctx}/tjxx/xsStatisticsGds/form?id=${xsStatisticsGds.id}">供电所电量损失统计<shiro:hasPermission name="tjxx:xsStatisticsGds:edit">${not empty xsStatisticsGds.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tjxx:xsStatisticsGds:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsStatisticsGds" action="${ctx}/tjxx/xsStatisticsGds/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">年月：</label>
			<div class="controls">
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供电所：</label>
			<div class="controls">
				<form:select path="businessid" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供电所编号：</label>
			<div class="controls">
				<form:input path="gdsbh" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供电所名称：</label>
			<div class="controls">
				<form:input path="gdsmc" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起始日期：</label>
			<div class="controls">
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsStatisticsGds.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsStatisticsGds.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否直供：</label>
			<div class="controls">
				<form:input path="isIncludeds" htmlEscape="false" maxlength="4" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有功电量：</label>
			<div class="controls">
				<form:input path="powerQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无功电量：</label>
			<div class="controls">
				<form:input path="nonpowerQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售电量：</label>
			<div class="controls">
				<form:input path="saleQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失电量：</label>
			<div class="controls">
				<form:input path="wasteQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变损电量：</label>
			<div class="controls">
				<form:input path="trwasteQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">力率：</label>
			<div class="controls">
				<form:input path="powerrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失率：</label>
			<div class="controls">
				<form:input path="wasteRate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合损失率：</label>
			<div class="controls">
				<form:input path="compWrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售电量（含四到户）：</label>
			<div class="controls">
				<form:input path="salequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失电量（含四到户）：</label>
			<div class="controls">
				<form:input path="wastequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变损电量（含四到户）：</label>
			<div class="controls">
				<form:input path="trwastequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失率（含四到户）：</label>
			<div class="controls">
				<form:input path="wasterateInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合损失率（含四到户）：</label>
			<div class="controls">
				<form:input path="compwrateInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="tjxx:xsStatisticsGds:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>