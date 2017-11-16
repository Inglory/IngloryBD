<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>历史线损计算电量管理</title>
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
		<li><a href="${ctx}/dljs/xsLinewastageHis/">历史线损计算电量列表</a></li>
		<li class="active"><a href="${ctx}/dljs/xsLinewastageHis/form?id=${xsLinewastageHis.id}">历史线损计算电量<shiro:hasPermission name="dljs:xsLinewastageHis:edit">${not empty xsLinewastageHis.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dljs:xsLinewastageHis:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsLinewastageHis" action="${ctx}/dljs/xsLinewastageHis/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">线路：</label>
			<div class="controls">
				<form:input path="lineId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路编号：</label>
			<div class="controls">
				<form:input path="lineCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路名称：</label>
			<div class="controls">
				<form:input path="linename" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起始日期：</label>
			<div class="controls">
				<input name="qsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsLinewastageHis.qsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束日期：</label>
			<div class="controls">
				<input name="jsrq" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsLinewastageHis.jsrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售电量：</label>
			<div class="controls">
				<form:input path="saleQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售电量（含四到户）：</label>
			<div class="controls">
				<form:input path="salequaInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实抄电量：</label>
			<div class="controls">
				<form:input path="actExcerpqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变损电量：</label>
			<div class="controls">
				<form:input path="trwasteExsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变损电量（含四到户）：</label>
			<div class="controls">
				<form:input path="trwasteInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">四到户变损电量：</label>
			<div class="controls">
				<form:input path="sdhTrwaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综损电量：</label>
			<div class="controls">
				<form:input path="comTrwaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综损电量（含四到户）：</label>
			<div class="controls">
				<form:input path="comtrwasteInsdh" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">四到户综损电量：</label>
			<div class="controls">
				<form:input path="sdhComtrwaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专线线损：</label>
			<div class="controls">
				<form:input path="spelineWaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有功合计：</label>
			<div class="controls">
				<form:input path="qua1" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无功合计：</label>
			<div class="controls">
				<form:input path="qua2" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失电量：</label>
			<div class="controls">
				<form:input path="wasteQua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">理论变损率：</label>
			<div class="controls">
				<form:input path="theoryTrwaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">理论损失率：</label>
			<div class="controls">
				<form:input path="theoryWrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">理论综损率：</label>
			<div class="controls">
				<form:input path="theoryCompwrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际损失率：</label>
			<div class="controls">
				<form:input path="actWrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">综合损失率：</label>
			<div class="controls">
				<form:input path="compWrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失考核得分：</label>
			<div class="controls">
				<form:input path="wratePrscore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">损失得分：</label>
			<div class="controls">
				<form:input path="wrateScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际力率：</label>
			<div class="controls">
				<form:input path="actPowerrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">力率考核得分：</label>
			<div class="controls">
				<form:input path="powerPrscore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">力率得分：</label>
			<div class="controls">
				<form:input path="powerScore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">burgle：</label>
			<div class="controls">
				<form:input path="burgle" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">抄标错误：</label>
			<div class="controls">
				<form:input path="copyerror" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最终得分：</label>
			<div class="controls">
				<form:input path="finalscore" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他得分：</label>
			<div class="controls">
				<form:input path="others" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dljs:xsLinewastageHis:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>