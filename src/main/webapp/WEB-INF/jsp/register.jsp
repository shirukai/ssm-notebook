<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/register.css">
</head>
<body>
<form class="form-register" onsubmit="return false">
    <div class="text-center">
        <h2 class="logo-text">NOTEBOOK</h2>
        <h6>用心记录点滴生活</h6>
        <img class="avatar" src="">
        <input type="file" id="avatarFile">
        <input type="hidden" name="avatar" id="avatar">
        <div class="modify-title">点击头像修改</div>
    </div>
    <br>
    <div class="input-register">
        <label for="userName">用户名</label>
        <input type="text" class="form-control" id="userName" name="userName" placeholder="邮箱/手机号" value="" required="">
        <div class="invalid-feedback"></div>
    </div>
    <div class="input-register">
        <label for="nickName">昵称</label>
        <input type="text" class="form-control" id="nickName" name="nickName" placeholder="3-16位昵称" value="" required="">
        <div class="invalid-feedback"></div>
    </div>
    <div class="input-register">
        <label for="password">密码</label>
        <input type="password" class="form-control" id="password" name="userPwd" placeholder="必须包含大小写字母、数字、特殊字符" value="" required="">
        <div class="invalid-feedback"></div>
    </div>
    <div class="input-register">
        <label for="rePassword">重复密码</label>
        <input type="password" class="form-control" id="rePassword" placeholder="重复输入密码" value="" required="">
        <div class="invalid-feedback"></div>
    </div>
    <button class="btn btn-lg btn-primary btn-block" id="register">注册</button>
</form>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/register.js"></script>
</body>
</html>
