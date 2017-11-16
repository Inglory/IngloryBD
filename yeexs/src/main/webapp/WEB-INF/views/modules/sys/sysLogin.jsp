<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${fns:getConfig('productName')} 登录</title>
<link href="${ctxStatic }/common/login.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</head>

<body>
<div class="common-login">
	<div class="reg_bg"></div>
	<div class="login-resign-wrap">
    	<div class="login-resign-content">
        	<div class="jx-logo">
            	<img src="${ctxStatic }/images/logo.png">
            	<h1>线损管理系统</h1>
            </div>
            <form class="loginer-form-1" id="loginForm" action="${ctx}/login" method="post">
                <div class="input-inset-wrap">
                    <input type="text" id="username" name="username" class="input-inset input-login-resign" placeholder="用户名" >
                    <div class="loginer-bg-wrap loginer-zhanghu"></div>
                </div>

                <div class="input-inset-wrap">
                    <input type="password" id="password" name="password" class="input-inset input-login-resign" placeholder="密码" >
                    <div class=" loginer-bg-wrap loginer-mima"></div>
                </div>
                <div class="checkbox-wrap checkbox-login">
                     <!--<input type="checkbox" class="input-checkbox" >
                    <label class="checkbox-label" >记住我</label> -->
                    <label for="rememberMe" title="下次不需要再登录" checkbox-label><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我</label>
                    <a href="javascript:void(0);" onclick="return alert('请联系管理员');"  class="a-1">忘记密码？</a>
                    <div class="clear_both"></div>
                </div>
                <div>
                    <input class="btn btn-rect btn-primary btn-long login-resign-btn" type="submit"  value="登&nbsp;&nbsp;&nbsp;录">
                </div>
            </form>
        </div>
    </div>
    <div class="footer-bar">
    	Copyright © 2017 山东亿云信息技术有限公司 . All rights reserved.<img src="${ctxStatic }/images/yy.png">
    </div>
</div>
</body>
</html>
