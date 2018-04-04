<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .comment {
        background-color: #fff;
        padding: 20px;
    }

    .avatar {
        height: 60px;
        width: 60px;
        border-radius: 50%;
    }

    .center-vertical {
        position: relative;
        top: 50%;
        transform: translateY(-50%);
    }

    .comment-list {
        margin-top: 20px;
    }

    .list-content {
        margin-top: 10px;
    }

    .commenter > .time {
        font-size: 0.8rem;
    }
</style>
<div class="container wrapper">
    <div class="comment">
        <div class="comment-head">
            <span class="fa fa-comments-o"></span>
            评论(<span id="commentNumber">0</span>)
        </div>
        <hr>
        <div class="comment-body">
            <div class="comment-publish row">
                <div class="col-1">
                    <img class="avatar" src="http://ov1a6etyz.bkt.clouddn.com/201804032209_631.jpg">
                </div>
                <div class="col-9 comment-input">
                    <textarea class="form-control center-vertical"></textarea>
                </div>
                <div class="col-1">
                    <button class="btn btn-primary center-vertical" id="send">评论</button>
                </div>
            </div>
            <div class="comment-list">
                <div class="list-all">
                    <div><span>最新评论</span></div>


                    <div class="list-content row">
                        <div class="col-1">
                            <img class="avatar" src="[avatar]">
                        </div>
                        <div class="col-9 commenter">
                            <div class="name">
                                [name]
                            </div>
                            <div class="time">
                                [time]
                            </div>
                            <div class="content">
                                [content]
                            </div>
                            <hr>
                        </div>
                        <div class="col-1">
                            <span class="fa fa-thumbs-o-up"></span>(5)
                        </div>
                    </div>


                </div>
            </div>
        </div>
        <div class="comment-foot"></div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/comment.js"></script>