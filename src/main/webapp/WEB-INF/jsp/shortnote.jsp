<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>便签</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/normalize.css">
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/default.css">--%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/shortnote.css">
    <style type="text/css">

    </style>
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp" %>
//列表
<div class="container">
    <div class="row">
        <section id="gallery-wrapper">

        </section>
    </div>
</div>
<script type="text/html" id="showNoteItems">
    <article class="white-panel">
        <div class="content">
            <div class="">
                <div class="noteContent" id="[contentId]" >
                    [noteContent]
                </div>
                <hr>
                <div class="createInfo">
                    作者：[createName]
                    <span class="pull-right" id="[stateId]">状态：[isPublic]</span>
                    </br>时间：[createTime]
                    <span class="pull-right">
                        <span class="addLike"><img class="heart" src="<%=request.getContextPath()%>/skins/img/heart.png"
                                                   title="喜欢就点个赞吧" onclick="addLike()"
                                                   id="[likeId]"><span>[likeNumber]</span>
                        </span>
                        人喜欢
                    </span>
                </div>
            </div>
        </div>
    </article>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/pinterest_grid.js"></script>
<script type="text/javascript">
    $(function () {
        $("#gallery-wrapper").pinterest_grid({
            no_columns: 4,
            padding_x: 10,
            padding_y: 10,
            margin_bottom: 50,
            single_column_breakpoint: 700
        });

    });
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/shortnote.js"></script>

</body>
</html>
