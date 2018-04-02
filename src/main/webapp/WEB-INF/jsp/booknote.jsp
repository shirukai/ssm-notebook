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
        <div class="col-lg-9" style="background: #aaaaaa;height: 100%;">
            <div  class="list_style" style="background: #ffffff" id="managerlist" >
                <div class="col-lg-1 pull-left" style="width: 100%;border-radius: 50% 50%;padding: 0;">
                    <img src="http://ov1a6etyz.bkt.clouddn.com//1521854290525avatar.jpg" style="width: 100%;border-radius: 50% 50%;"/>
                </div>
                <div class="col-lg-11 pull-right">
                <h6>java从入门到精通</h6>
                <p class="managerlead">
                    本文写于一次内部的技术研讨会,文中口语化的内容较多.除了安利两个工具外,也写出了我在前台上想过的一些东西,希望能对大家有所启发吧.
                </p>
                    <hr style="height: 1px;margin: 8px 1px 8px 1px ">
                    <div class="createInfo">
                        <span class="fa fa-user-o col-lg-2"> shirukai</span>
                        <span class="fa fa-calendar col-lg-4"> 2018/3/31 下午2:30:08</span>

                        <span class="fa fa-refresh col-lg-2">
                           12
                        </span>
                        <span  class="fa fa-comment-o col-lg-2"> 5</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-3">
            <div style="background: #bbbbbb;height: 300px; "  id="managertype">
                <p>111111</p>
            </div>
        </div>
    </div>
</div>
<div>


</div>

<script type="text/html" id="showManagerItems">
    <div  class="list_style" style="background: #ffffff" id="managerlist" >
        <div class="col-lg-1 pull-left" style="width: 100%;border-radius: 50% 50%;padding: 0;">
            <img src="http://ov1a6etyz.bkt.clouddn.com//1521854290525avatar.jpg" style="width: 100%;border-radius: 50% 50%;"/>
        </div>
        <div class="col-lg-11 pull-right">
            <h6>[bookTitle]</h6>//文章标题
            <p class="managerlead">//文章内容
                [bookContent]
            </p>
            <hr style="height: 1px;margin: 8px 1px 8px 1px ">
            <div class="createInfo">
                <span class="fa fa-user-o col-lg-2">[niceName]</span>//作者
                <span class="fa fa-calendar col-lg-4">[createTime]</span>//修改时间
                <span class="fa fa-refresh col-lg-2">[viewNumber]</span>//转发量[浏览量]
                <span  class="fa fa-comment-o col-lg-2">[likeNumber]</span>//评论量[喜欢量]
            </div>
        </div>
    </div>
</script>

<script type="text/javascript" src="<%=request.  getContextPath()%>/skins/js/jsp/booknote.js"></script>
</body>
</html>
