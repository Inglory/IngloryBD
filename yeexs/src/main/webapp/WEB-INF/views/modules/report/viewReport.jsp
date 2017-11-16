<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="decorator" content="default"/>
<title>在线预览报表</title>
<script type="text/javascript" src="static/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
			var dd=$(window).height()-120; 
		    $("#reportPreview").css("height",dd);
			
		});
	function previewReport(){
		
		var time=$("#time").val();
		var url="${ctx}/report/preview/${reportName}";
		 window.frames.reportPreview.location.href = url;
	}	
	
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a>${chreportName }</a></li>
</ul>
<form id="searchForm"   method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		<li><label>时间：</label>
				<input type="text" id="time" name="time" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmitpreview" class="btn btn-primary" type="button" value="查询" onclick='previewReport()'/></li>
			<li><label>导出类型：</label><select class="input-medium"><option value="EXCEL">EXCEL</option><option value="PDF">PDF</option></select></li>
			<li class="btns"><input id="btnSubmitexport" class="btn btn-primary" type="button" value="导出" onclick='previewReport()'/></li>
			<li class="clearfix"></li>
		</ul>	
	</form>
	<iframe id="reportPreview" name="reportPreview" width="99%"> </iframe>
   
</body>
</html>