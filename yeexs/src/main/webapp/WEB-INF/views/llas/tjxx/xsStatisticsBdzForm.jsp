<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站损失信息管理</title>
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
		<li><a href="${ctx}/tjxx/xsStatisticsBdz/">变电站损失信息列表</a></li>
		<li class="active"><a href="${ctx}/tjxx/xsStatisticsBdz/form?id=${xsStatisticsBdz.id}">变电站损失信息<shiro:hasPermission name="tjxx:xsStatisticsBdz:edit">${not empty xsStatisticsBdz.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tjxx:xsStatisticsBdz:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsStatisticsBdz" action="${ctx}/tjxx/xsStatisticsBdz/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">ym：</label>
			<div class="controls">
				<form:input path="ym" htmlEscape="false" maxlength="40" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">trsubstation_id：</label>
			<div class="controls">
				<form:input path="trsubstationId" htmlEscape="false" maxlength="8" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">power_qua：</label>
			<div class="controls">
				<form:input path="powerQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">nonpower_qua：</label>
			<div class="controls">
				<form:input path="nonpowerQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sale_qua：</label>
			<div class="controls">
				<form:input path="saleQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">waste_qua：</label>
			<div class="controls">
				<form:input path="wasteQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">trwaste_qua：</label>
			<div class="controls">
				<form:input path="trwasteQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">powerrate：</label>
			<div class="controls">
				<form:input path="powerrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">waste_rate：</label>
			<div class="controls">
				<form:input path="wasteRate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">comp_wrate：</label>
			<div class="controls">
				<form:input path="compWrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">salequa_insdh：</label>
			<div class="controls">
				<form:input path="salequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wastequa_insdh：</label>
			<div class="controls">
				<form:input path="wastequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">trwastequa_insdh：</label>
			<div class="controls">
				<form:input path="trwastequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wasterate_insdh：</label>
			<div class="controls">
				<form:input path="wasterateInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">compwrate_insdh：</label>
			<div class="controls">
				<form:input path="compwrateInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">yglj：</label>
			<div class="controls">
				<form:input path="yglj" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wglj：</label>
			<div class="controls">
				<form:input path="wglj" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sslj：</label>
			<div class="controls">
				<form:input path="sslj" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sclj：</label>
			<div class="controls">
				<form:input path="sclj" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="tjxx:xsStatisticsBdz:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>