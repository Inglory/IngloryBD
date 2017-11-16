<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>35kV线路统计关系管理</title>
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
		<li><a href="${ctx}/tjgx/xsRelation35/">35kV线路统计关系列表</a></li>
		<li class="active"><a href="${ctx}/tjgx/xsRelation35/form?id=${xsRelation35.id}">35kV线路统计关系<shiro:hasPermission name="tjgx:xsRelation35:edit">${not empty xsRelation35.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="tjgx:xsRelation35:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsRelation35" action="${ctx}/tjgx/xsRelation35/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">统计线路：</label>
			<div class="controls">
				<form:select path="parlineId" class="input-medium">
					<form:option value="" label="请选择35kV线路"/>
					<form:options items="${Listline35}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算线路：</label>
			<div class="controls">
				<form:select path="lineId" class="input-medium">
					<form:option value="" label="请选择6-10kV线路"/>
					<form:options items="${Listline610}" itemLabel="lineName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电量类型：</label>
			<div class="controls">
				<form:select path="quaKind" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dllx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计算方式：</label>
			<div class="controls">
				<form:select path="calKind" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('jsfs')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="tjgx:xsRelation35:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>