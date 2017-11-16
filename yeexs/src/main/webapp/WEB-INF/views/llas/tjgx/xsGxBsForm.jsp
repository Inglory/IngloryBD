<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站损失关系管理</title>
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
		<li><a href="${ctx}/tjgx/xsGxBs/">变电站损失关系列表</a></li>
		<li class="active"><a href="${ctx}/tjgx/xsGxBs/form?id=${xsGxBs.id}">变电站损失关系<shiro:hasPermission name="tjgx:xsGxBs:edit">${not empty xsGxBs.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tjgx:xsGxBs:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsGxBs" action="${ctx}/tjgx/xsGxBs/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">统计对象：</label>
			<div class="controls">
				<form:select path="tjdx" class="input-xlarge required">
					<form:option value="" label="请选择变电站"/><form:options items="${bdzList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计电量类型：</label>
			<div class="controls">
				<form:select path="tjdllx" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('tjdllx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量点：</label>
			<div class="controls">
				<form:select path="jld" class="input-xlarge required">
					<form:option value="" label="请选择线路"/>
					<form:options items="${ListlineAll}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算电量：</label>
			<div class="controls">
				<form:select path="jsdl" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dllx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算方式：</label>
			<div class="controls">
				<form:select path="jsfs" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('jsfs')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="tjgx:xsGxBs:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>