<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据导入规则管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/jcxx/dataimpRule/">数据导入规则列表</a></li>
		<li class="active"><a href="${ctx}/jcxx/dataimpRule/form?id=${dataimpRule.id}">数据导入规则<shiro:hasPermission name="jcxx:dataimpRule:edit">${not empty dataimpRule.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="jcxx:dataimpRule:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dataimpRule" action="${ctx}/jcxx/dataimpRule/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">导入规则名称：</label>
			<div class="controls">
				<form:input path="ruleName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">源类型：</label>
			<div class="controls">
				<form:select path="sourceType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('data_storage_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">导出源：</label>
			<div class="controls">
				<form:input path="source" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目的表类型：</label>
			<div class="controls">
				<form:select path="destinationType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('data_storage_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">导入目的表：</label>
			<div class="controls">
				<form:input path="destination" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">数据导入关系映射：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>源字段</th>
								<th>excel字段所在列号</th>
								<th>excel表头所在行号</th>
								<th>目标字段</th>
								<shiro:hasPermission name="jcxx:dataimpRule:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dataimpMappingList">
						</tbody>
						<shiro:hasPermission name="jcxx:dataimpRule:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#dataimpMappingList', dataimpMappingRowIdx, dataimpMappingTpl);dataimpMappingRowIdx = dataimpMappingRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dataimpMappingTpl">//<!--
						<tr id="dataimpMappingList{{idx}}">
							<td class="hide">
								<input id="dataimpMappingList{{idx}}_id" name="dataimpMappingList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dataimpMappingList{{idx}}_delFlag" name="dataimpMappingList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dataimpMappingList{{idx}}_sourceField" name="dataimpMappingList[{{idx}}].sourceField" type="text" value="{{row.sourceField}}" maxlength="100" class="input-small required"/>
							</td>
							<td>
								<input id="dataimpMappingList{{idx}}_excelColumn" name="dataimpMappingList[{{idx}}].excelColumn" type="text" value="{{row.excelColumn}}" maxlength="3" class="input-small  digits"/>
							</td>
							<td>
								<input id="dataimpMappingList{{idx}}_excelRow" name="dataimpMappingList[{{idx}}].excelRow" type="text" value="{{row.excelRow}}" maxlength="3" class="input-small  digits"/>
							</td>
							<td>
								<input id="dataimpMappingList{{idx}}_destField" name="dataimpMappingList[{{idx}}].destField" type="text" value="{{row.destField}}" maxlength="100" class="input-small required"/>
							</td>
							<shiro:hasPermission name="jcxx:dataimpRule:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dataimpMappingList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dataimpMappingRowIdx = 0, dataimpMappingTpl = $("#dataimpMappingTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dataimpRule.dataimpMappingList)};
							for (var i=0; i<data.length; i++){
								addRow('#dataimpMappingList', dataimpMappingRowIdx, dataimpMappingTpl, data[i]);
								dataimpMappingRowIdx = dataimpMappingRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="jcxx:dataimpRule:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>