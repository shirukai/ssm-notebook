<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>${book.bookTitle}</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/bookDetail.css">
</head>
<body>
<div class="container wrapper book-detail">
    <div class="book" id="bookView">
        <input type="hidden" id="u_id" value="${book.uid}">
        <input type="hidden" id="b_id" value="${book.bid}">
        <div class="pull-right">
            <h6><i class="fa fa-clock-o"></i>&nbsp;&nbsp;创建时间： <fmt:formatDate value="${book.modifyTime}"
                                                                               pattern="yyyy年MM月dd日 HH时mm分ss秒"/><span
                    id="b_time"></span></h6>
            <h6><i class="fa fa-th-large"></i>&nbsp;&nbsp;分类：<a id="b_type">${book.type}</a></h6>
            <h6><i class="fa fa-eye"></i>&nbsp;&nbsp;状态：<a id="b_state">${book.isPublic==1?"公开":"不公开"}</a></h6>
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
            <h6><i class="fa fa-pencil"></i><span id="createUser">本笔记由notebook发布</span></h6>
        </div>
    </div>
</div>
<%@include file="comment.jsp" %>
</body>
</html>
