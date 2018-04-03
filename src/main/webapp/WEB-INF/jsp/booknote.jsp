<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>笔记</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/booknote.css">
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp"%>

<div class="container ">
    <div class="row">
        <div class="col-lg-9"  id="managerlist">
        <%--文章列表--%>
        </div>
        <div class="col-lg-3">
            <div style="background: #ffffff;height: 300px; "  id="managertype">
                <div class="user_top" style="padding-top: 5px;">
                    <div class="col-1 pull-left"> </div>
                    <div class="col-6 pull-left"> 用户名</div>
                    <div class="col-4 pull-right" > 文章数</div>
                </div>
                <hr/>
            </div>
        </div>
    </div>
</div>

<div style="height: 20px;">
<%--页面底部留白--%>
</div>
<script type="text/html" id="showManagerItems">
    <div  class="list_style" style="" id="[booknId]" >
        <div class="col-1 pull-left" style="width: 100%;border-radius: 50% 50%;padding: 0;">
            <img src="http://ov1a6etyz.bkt.clouddn.com//1521854290525avatar.jpg" style="width: 50px;height: 50px;border-radius: 50% 50%;"/>
        </div>
        <div class="col-11 pull-right">
            <%--文章标题--%>
            <h6> [bookTitle]</h6>
                <%--文章内容--%>
            <p class="managerlead">
                [bookContent]
            </p>
            <hr style="height: 1px;margin: 8px 1px 8px 1px ">
            <div class="createInfo">
                <%--作者--%>
                <span class="fa fa-user-o col-3"> [nickName]</span>
                    <%--修改时间--%>
                <span class="fa fa-calendar col-4"> [modifyTime]</span>
                    <%--转发量[浏览量]--%>
                <span class="fa fa-refresh col-2" id="[viewId]"> [viewNumber]</span>
                    <%--评论量[喜欢量]--%>
                <span  class="fa fa-comment-o col-2" id="[likeId]"> [likeNumber]</span>
            </div>
        </div>
    </div>
</script>

<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/booknote.js"></script>
</body>
</html>
