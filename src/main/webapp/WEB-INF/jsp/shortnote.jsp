<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>便签</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/normalize.css">
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/default.css">--%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/shortnote.css">
    <style type="text/css">
        #gallery-wrapper {
            position: relative;
            max-width: 100%;
            width: 100%;
            margin: 50px auto;
        }

        img.thumb {
            width: 100%;
            max-width: 100%;
            height: auto;
        }

        .white-panel {
            position: absolute;
            background: white;
            border-radius: 5px;
            box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
            padding: 10px;
        }

        .white-panel h1 {
            font-size: 1em;
        }

        .white-panel h1 a {
            color: #A92733;
        }

        .white-panel:hover {
            box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.5);
            margin-top: -5px;
            -webkit-transition: all 0.3s ease-in-out;
            -moz-transition: all 0.3s ease-in-out;
            -o-transition: all 0.3s ease-in-out;
            transition: all 0.3s ease-in-out;
        }
        .createInfo {
            color: #626262;
            font-size: 12px;
        }
        .heart {
            width: 20px;
            height: 16px;
        }
        .noteContent {
            overflow: hidden;
        }
    </style>
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp" %>
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
