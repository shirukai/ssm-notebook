<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>笔记</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/booknote.css">
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp" %>

<div class="container ">
    <div class="row">
        <div class="col-sm-8" id="managerlist">
            <%--文章列表--%>
        </div>
        <div class="col-sm-4">
            <div class="wrapper animation-hover animation-active"
                 style="background: #ffffff;height: 300px;padding: 20px;" id="userTop">
                <div class="row user_top" style="padding-top: 5px;">
                    <div class="col-1 pull-left"></div>
                    <div class="col-6 pull-left"> 用户名</div>
                    <div class="col-4 pull-right"> 文章数</div>
                </div>
                <hr style="padding: 0px;margin:0px;"/>
                <%--用户排名--%>
            </div>
        </div>
    </div>
</div>

<div style="height: 20px;">
    <%--页面底部留白--%>
</div>
<script type="text/html" id="showManagerItems">
    <div class=" list_style book_info wrapper animation-hover animation-active" id="book_info" >
        <div class="col-sm-1">
            <img src="[avatar]" style="width: 50px;height: 50px;border-radius: 50% 50%;"/>
        </div>
        <div class="col-11">
            <div  id="[booknId]" onclick="click_top(this)">
                <%--文章标题--%>
                <h6> [bookTitle]</h6>
                <%--文章内容--%>
                <p class="managerlead">
                    [bookContent]
                </p>
            </div>
            <hr style="height: 1px;margin: 8px 1px 8px 1px ">
            <div class="createInfo" >
                <div class="row" style="padding-bottom: 3px;">
                    <%--作者--%>
                    <span class="fa fa-user-o col-8"> [nickName]</span>
                    <%--转发量[浏览量]--%>
                    <span class="fa fa-refresh col-4" id="[viewId]"> [viewNumber]</span>
                </div>
                <div class="row" style="padding-bottom: 3px;">
                    <%--修改时间--%>
                    <span class="fa fa-calendar col-8"> [modifyTime]</span>
                    <%--评论量[喜欢量]--%>
                    <span class="fa fa-comment-o col-4" id="[likeId]"> [likeNumber]</span>
                </div>
            </div>
        </div>
    </div>
</script>
<script type="text/html" id="user_top">
    <a href="#" style="text-decoration:none;">
        <div class="row user_top animation-hover animation-active" style="padding-top: 5px;" id="[uid]">
            <div class="col-1  rownumber">[rowNumber]</div>
            <div class="col-6  nickname">[nickName]</div>
            <div class="col-4  numberbook">[numberBook]</div>
        </div>
    </a>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/booknote.js"></script>
</body>
</html>
