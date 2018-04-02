<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>notebook</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/index.css">
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp" %>
<div class="container recommend">
    <div class="row">
        <div class="col-lg-3" id="row1">
            <div class="animation-hover animation-active">
                <div class="info-img">
                    <div class="headImg">
                        <img src="#" id="userHeadImg">
                    </div>
                </div>
                <div class="info-content" style="">
                    <div class="nameInfo">
                        <span class="neckName">未登录</span><br>
                        <span class="useName">用户名</span>
                    </div>
                    <div class="userInfo row">
                        <div class="notepads col-xl col-4">
                            <span class="infoTitle">便签</span><br>
                            <span class="infoNumber" id="noteNumber">无</span>
                        </div>
                        <div class="notebook col-xl col-4">
                            <span class="infoTitle">笔记</span><br>
                            <span class="infoNumber" id="booksNumber">无</span>
                        </div>
                        <div class="notebook col-xl col-4">
                            <span class="infoTitle">收藏</span><br>
                            <span class="infoNumber" id="favorite">无</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-5" id="row2">
            <div class="notes">
                <h5 class="name">便签</h5>
                <div class="line"></div>
                <div class="showNoteItems">
                </div>
            </div>
        </div>
        <div class="col-lg-4" id="row3">
            <div class="book">
                <h5 class="name">笔记</h5>
                <div class="line"></div>
                <div class="showBookItems">
                </div>
            </div>
        </div>
    </div>
</div>

<!--便签模板-->
<script type="text/html" id="showNoteItem">
    <div class="node-body animation-hover animation-active">
        <div class="noteContent" id="【contentId】">
            [noteContent]
        </div>
        <a class="more float-right" id="[moreId]" onclick="moreInfo()">more</a>
        <hr>
        <div class="createInfo">
            作者：[createName]
            <br>
            时间：[createTime]
            <span class="float-right">
                <span class="addLike">
                    <img class="heart" src="../skins/img/heart.png" title="喜欢就点个赞吧"
                         onclick="addLike()"
                         id="[noteId2]">
                    <span>[likeNumber]</span>
                </span>人喜欢
            </span>
        </div>
    </div>
</script>
<!--笔记模板-->
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/index.js"></script>
</body>
</html>
