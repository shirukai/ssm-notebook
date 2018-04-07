<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/common/topNav.css">
<div class="nav-head">
    <nav class="navbar navbar-expand-lg navbar-light mb-4">
        <a class="navbar-brand" href="#">NoteBook</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown-7"
                aria-controls="navbarNavDropdown-7" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown-7">
            <ul class="navbar-nav mr-auto head-left">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/notebook/index">推荐
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/notebook/shortnote">便签</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/notebook/booknote">笔记</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink-7"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        创作
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink-7">
                        <a class="dropdown-item"
                           href="<%=request.getContextPath()%>/admin/notebook/bookManager">笔记管理</a>
                        <a class="dropdown-item"
                           href="<%=request.getContextPath()%>/admin/notebook/noteManager">便签管理</a>
                        <%--<a class="dropdown-item"--%>
                           <%--href="<%=request.getContextPath()%>/admin/notebook/favorite">收藏夹</a>--%>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav head-right login-info">
                <li id="noLogin"><a href="<%=request.getContextPath()%>/notebook/login">登录</a>&nbsp&nbsp&nbsp&nbsp<a
                        href="/notebook/register">注册</a>
                </li>
                <li id="alLogin" class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" id="userName">
                    </a>
                    <div class="dropdown-menu my-info">
                        <a class="dropdown-item" href="<%=request.getContextPath()%>/notebook/myInfo">我的资料</a>
                        <a class="dropdown-item" href="#" id="out">退出登录</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/common/topNav.js"></script>