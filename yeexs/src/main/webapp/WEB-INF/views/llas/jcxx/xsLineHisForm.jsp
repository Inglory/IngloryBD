<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>历史线路信息管理</title>
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
		<li><a href="${ctx}/jcxx/xsLineHis/">历史线路信息列表</a></li>
		<li class="active"><a href="${ctx}/jcxx/xsLineHis/form?id=${xsLineHis.id}">历史线路信息<shiro:hasPermission name="jcxx:xsLineHis:edit">${not empty xsLineHis.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jcxx:xsLineHis:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xsLineHis" action="${ctx}/jcxx/xsLineHis/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">线路编号：</label>
			<div class="controls">
				<form:input path="lineCode" htmlEscape="false" maxlength="12" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路名称：</label>
			<div class="controls">
				<form:input path="lineName" htmlEscape="false" maxlength="40" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变电站编号：</label>
			<div class="controls">
				<form:input path="trsubstationId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变电站名称：</label>
			<div class="controls">
				<form:input path="trsubstationName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">母线标志：</label>
			<div class="controls">
				<form:radiobuttons path="isparline" items="${fns:getDictList('ifelse')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电压等级：</label>
			<div class="controls">
				<form:select path="voltage" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dydj')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专线标志：</label>
			<div class="controls">
				<form:radiobuttons path="isspecline" items="${fns:getDictList('ifelse')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向有功起码：</label>
			<div class="controls">
				<form:input path="fpSvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向有功止码：</label>
			<div class="controls">
				<form:input path="fpEvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向有功追补：</label>
			<div class="controls">
				<form:input path="fpSubjoinqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向有功换表：</label>
			<div class="controls">
				<form:input path="fpChmeter" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向有功抄见：</label>
			<div class="controls">
				<form:input path="fpExcerpqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向无功起码：</label>
			<div class="controls">
				<form:input path="fupSvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向无功止码：</label>
			<div class="controls">
				<form:input path="fupEvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向无功追补：</label>
			<div class="controls">
				<form:input path="fupSubjoinqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向无功换表：</label>
			<div class="controls">
				<form:input path="fupChmeter" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向无功抄见：</label>
			<div class="controls">
				<form:input path="fupExcerpqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向有功合计：</label>
			<div class="controls">
				<form:input path="fpTotalqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正向无功合计：</label>
			<div class="controls">
				<form:input path="fupTotalqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向有功起码：</label>
			<div class="controls">
				<form:input path="rpSvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向有功止码：</label>
			<div class="controls">
				<form:input path="rpEvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向有功追补：</label>
			<div class="controls">
				<form:input path="rpSubjoinqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向有功换表：</label>
			<div class="controls">
				<form:input path="rpChmeter" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向有功抄见：</label>
			<div class="controls">
				<form:input path="rpExcerpqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向有功合计：</label>
			<div class="controls">
				<form:input path="rpTotalqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向无功起码：</label>
			<div class="controls">
				<form:input path="rupSvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向无功止码：</label>
			<div class="controls">
				<form:input path="rupEvalue" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向无功追补：</label>
			<div class="controls">
				<form:input path="rupSubjoinqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向无功换表：</label>
			<div class="controls">
				<form:input path="rupChmeter" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向无功抄见：</label>
			<div class="controls">
				<form:input path="rupExcerpqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">反向无功合计：</label>
			<div class="controls">
				<form:input path="rupTotalqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有功合计：</label>
			<div class="controls">
				<form:input path="pTotalqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无功合计：</label>
			<div class="controls">
				<form:input path="upTotalqua" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">线路电阻：</label>
			<div class="controls">
				<form:input path="resistance" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">空载损耗：</label>
			<div class="controls">
				<form:input path="unloadWaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短路损耗：</label>
			<div class="controls">
				<form:input path="cirWaste" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">温度：</label>
			<div class="controls">
				<form:input path="temperature" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">K1系数：</label>
			<div class="controls">
				<form:input path="k1" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">K系数：</label>
			<div class="controls">
				<form:input path="k" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用电小时数：</label>
			<div class="controls">
				<form:input path="hours" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">倍率：</label>
			<div class="controls">
				<form:input path="intefactor" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计数容量：</label>
			<div class="controls">
				<form:input path="maxcapacity" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总容量：</label>
			<div class="controls">
				<form:input path="capacity" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">执行力率标准：</label>
			<div class="controls">
				<form:input path="powerfactor" htmlEscape="false" maxlength="2" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划线损率：</label>
			<div class="controls">
				<form:input path="scheWrate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">换表标志：</label>
			<div class="controls">
				<form:radiobuttons path="changesign" items="${fns:getDictList('ifelse')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">正反向标志：</label>
			<div class="controls">
				<form:radiobuttons path="prflag" items="${fns:getDictList('ifelse')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起码日期：</label>
			<div class="controls">
				<input name="qmdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsLineHis.qmdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">止码日期：</label>
			<div class="controls">
				<input name="zmdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${xsLineHis.zmdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">报表显示标志：</label>
			<div class="controls">
				<form:select path="rptvissign" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="jcxx:xsLineHis:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>