<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/comment.css">
<div class="container wrapper comments ">
    <div class="comment">
        <div class="comment-head">
            <i class="fa fa-comments-o"></i>
            评论(<span id="commentNumber">0</span>)
        </div>
        <hr>
        <div class="comment-body">
            <div class="comment-publish row">
                <div class="col-lg-1">
                    <img class="avatar" id="userAvatar" src="http://ov1a6etyz.bkt.clouddn.com/201804032209_631.jpg">
                </div>
                <div class="col-lg-9 comment-input">
                    <textarea class="form-control center-vertical"></textarea>
                </div>
                <div class="col-lg-1">
                    <button class="btn btn-primary center-vertical" id="send">评论</button>
                </div>
            </div>
            <div class="comment-list">
                <div class="list-all">
                    <div><span>最新评论</span></div>
                </div>
            </div>
        </div>
        <div class="comment-foot"></div>
    </div>
</div>
<script type="text/html" id="comment-template">
    <div class="list-content row">
        <div class="col-lg-1 ">
            <img class="avatar" src="[avatar]">
        </div>
        <div class="col-lg-9 commenter">
            <div class="name" data-uid="[uid]" data-cid="[cid]" onclick="showReply(this)">
                [name]
            </div>
            <div class="time">
                [time]
            </div>
            <div class="content">
                [content]
            </div>
            <div class="reply-list">
                [reply-items]
            </div>
            <div class="option pull-right">
                [options]
                <i class="fa fa-ellipsis-h ah aa" title="更多操作"></i>
            </div>
            <div class="input-group input-group-seamless reply-write hide" id="[input-id]">
                <input type="text" class="form-control" placeholder="请输入回复内容">
                <span class="input-group-append" onclick="sendReply(this)">
                    <span class="input-group-text">
                        <i class="fa fa-paper-plane-o ah aa" title="发送"></i>
                    </span>
                </span>
            </div>

        </div>
        <div class="col-lg-1">
            <i class="fa fa-thumbs-o-up ah aa" title="点赞" data-cid=[cid] onclick="addLike(this)"></i>&nbsp;(<span
                class="like">[like]</span>)
        </div>
    </div>
</script>
<script type="text/html" id="reply-item">
    <div class="reply-item" id="[id]">
        <span class="reply-time">[time]</span>&nbsp;
        <span class="reply-sender" data-uid="[sender-uid]" data-cid="[data-cid]"
              onclick="showReply(this)">[sender]</span>&nbsp;回复&nbsp;
        <span class="reply-answer" data-uid="[answer-uid]" data-cid="[data-cid]"
              onclick="showReply(this)">[answer]</span>：
        <span class="reply-content">[content]</span>
    </div>
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/comment.js"></script>