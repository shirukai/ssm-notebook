<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>${book.bookTitle}</title>
    <style>
        .container {
            margin-top: 20px;
            padding: 20px 40px 80px 40px;
            background-color: #fff;
            margin-bottom: 40px;
        }

        .btn-xm {
            padding: .2rem 1rem;
            font-size: .4rem;
            line-height: 1.4;
            border-radius: .35rem;
        }

        #bookView > div h6 {
            margin-bottom: 2px;
        }

    </style>
</head>
<body>
<div class="container wrapper">
    <div class="book" id="bookView">
        <input type="hidden" id="b_id" value="">
        <div class="pull-right">
            <h6><span class="fa fa-clock-o"></span>&nbsp;&nbsp;创建时间： <fmt:formatDate value="${book.modifyTime}"
                                                                                     pattern="yyyy年MM月dd日 HH时mm分ss秒"/><span
                    id="b_time"></span></h6>
            <h6><span class="fa fa-th-large"></span>&nbsp;&nbsp;分类：<a id="b_type">${book.type}</a></h6>
            <h6><span class="fa fa-eye"></span>&nbsp;&nbsp;状态：<a id="b_state">${book.isPublic==1?"公开":"不公开"}</a></h6>
        </div>
        <br>
        <div style="margin-top:58px">
            <a id="b_title" style="font-size: 20px" target="_blank">${book.bookTitle}</a>
            <span class="pull-right" style="margin-top: 3px">
                            <button id="b_share" class="btn  btn-xm btn-warning">分享</button>
                        </span>
        </div>
        <hr>
        <div id="b_content">
            ${book.bookContent}
        </div>
        <hr>
        <div class="pull-right">
            <h6><span class="fa fa-pencil"></span><span id="createUser">本笔记由notebook发布</span></h6>
        </div>
    </div>
</div>
</body>
</html>
