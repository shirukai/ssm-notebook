<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/login.css">
</head>
<body>
<form class="form-login text-center" onsubmit="return false">
    <div class="alert alert-danger alert-dismissible fade" role="alert">
        <strong>登录失败</strong> 用户名或密码错误
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <h2 class="logo-text">NOTEBOOK</h2>
    <h6>用心记录点滴生活</h6>
    <br>
    <div class="input-group input-group-seamless login-input">
        <input type="text" class="form-control" id="userName" placeholder="用户名" name="userName">
        <span class="input-group-append">
            <span class="input-group-text">
                <i class="fa fa-user"></i>
            </span>
        </span>
        <div class="invalid-feedback text-left" id="user-feedback"></div>
    </div>
    <div class="input-group input-group-seamless login-input">
        <input type="password" class="form-control" id="password" placeholder="密码" name="userPwd">
        <span class="input-group-append">
            <span class="input-group-text">
                <i class="fa fa-lock"></i>
            </span>
        </span>
        <div class="invalid-feedback text-left" id="password-feedback"></div>
    </div>
    <div class="checkbox mb-6">
        <label>
            <input type="checkbox" value="" id="autoLogin"> 七天免登录
        </label>
        <label>
            <a href="<%=request.getContextPath()%>/notebook/register"> 没有账号？</a>
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" id="login">登录</button>
    <p class="mt-5 mb-3 text-muted">© 2017-2018</p>
</form>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/login.js"></script>
</body>
</html>
