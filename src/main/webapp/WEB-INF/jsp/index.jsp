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
        <div class="col-lg-3" id="col1">
            <div class="animation-hover animation-active wrapper">
                <div class="info-img top-radius">
                    <div class="headImg">
                        <img src="http://ov1a6etyz.bkt.clouddn.com/201804032209_631.jpg" id="userHeadImg">
                    </div>
                </div>
                <div class="info-content">
                    <div class="nameInfo">
                        <span class="nickName">未登录</span><br>
                        <span class="useName">用户名</span>
                    </div>
                    <div class="userInfo row">
                        <div class="notepads col-xl col-4">
                            <span class="infoTitle">便签</span><br>
                            <a class="infoNumber" id="noteNumber" href="/admin/notebook/noteManager">无</a>
                        </div>
                        <div class="notebook col-xl col-4">
                            <span class="infoTitle">笔记</span><br>
                            <a class="infoNumber" id="bookNumber" href="/admin/notebook/bookManager">无</a>
                        </div>
                        <div class="notebook col-xl col-4">
                            <span class="infoTitle">收藏</span><br>
                            <a class="infoNumber" id="favorite">无</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:choose>
            <c:when test="${myInfo}">
                <div class="col-lg-9" id="row4">
                    <div class="user-info wrapper">
                        <form onsubmit="return false" id="updateFrom">
                            <i class="info-close pull-right fa fa-close aa ah"></i>
                            <span class="info-title">个人资料</span>
                            <hr>
                            <div class="info-body">
                                <div class="head text-center">
                                    <img class="avatar" src="http://ov1a6etyz.bkt.clouddn.com/201804032209_631.jpg">
                                    <input type="file" id="avatarFile">
                                    <input type="hidden" name="avatar" id="avatar">
                                    <div class="modify-title">点击头像修改</div>
                                </div>
                                <div class="row" style="margin-top: 10px">
                                    <div class="col-md-6 mb-3">
                                        <label for="userName">用户名</label>
                                        <input type="text" class="form-control" name="userName" id="userName" value=""
                                               disabled>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="nickName">昵称</label>
                                        <input type="text" class="form-control" name="nickName" id="nickName" value=""
                                               required="">
                                    </div>
                                </div>
                                <div class="row" style="margin-top: 10px">
                                    <div class="col-md-6 mb-3"><label for="oldPassword">旧密码</label>
                                        <input type="text" class="form-control" name="oldUserPwd" id="oldPassword"
                                               value=""
                                               placeholder="若要修改密码请填写此项">
                                    </div>
                                    <div class="col-md-6 mb-3"><label for="newPassword">新密码</label>
                                        <input type="text" class="form-control" name="userPwd" id="newPassword" value=""
                                               placeholder="若要修改密码请填写此项">
                                    </div>
                                </div>
                            </div>
                            <div class="info-foot text-center">
                                <button class="btn btn-light info-close">关闭</button>
                                <button class="btn btn-primary" id="updateUser">保存</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-lg-5" id="col2">
                    <div class="notes">
                        <div class="item-name animation-hover animation-active wrapper">
                            <span class="name">New便签</span>
                            <a class="pull-right more" href="/notebook/shortnote">更多</a>
                        </div>
                        <div class="showNoteItems">
                        </div>
                    </div>
                </div>
                <div class="col-lg-4" id="col3">
                    <div class="book">
                        <div class="item-name animation-hover animation-active wrapper">
                            <span class="name">New笔记</span>
                            <a class="pull-right more" href="/notebook/booknote">更多</a>
                        </div>
                        <div class="showBookItems animation-hover wrapper">

                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<!--便签模板-->
<script type="text/html" id="showNoteItem">
    <div class="node-body animation-hover animation-active wrapper">
        <div class="noteContent" id="[contentId]">
            [noteContent]
        </div>
        <a class="more float-right" onclick="moreInfo(this)">more</a>
        <hr>
        <div class="createInfo">
            作者：[createName]
            <br>
            时间：[createTime]
            <span class="float-right">
                <span class="addLike">
                    <img class="heart" src="<%=request.getContextPath()%>/skins/img/heart.png" title="喜欢就点个赞吧"
                         onclick="addLike()"
                         id="[likeId]">
                    <span>[likeNumber]</span>
                </span>人喜欢
            </span>
        </div>
    </div>
</script>
<script>
    var myInfo = '${myInfo}'
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/index.js"></script>
</body>
</html>
