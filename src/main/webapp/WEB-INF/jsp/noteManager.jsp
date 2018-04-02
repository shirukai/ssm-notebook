<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>便签管理</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/wangEditor.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/noteManager.css">
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp" %>
<div class="container">
    <%--开启编辑器按钮--%>
    <div class="add"></div>

    <%--富文本编辑器--%>
    <div id="editorController" style="background: white">
        <div id="editor">
            <h3>标题：</h3>
            <p>正文：</p>
            <p><br></p>
            <p><br></p>
        </div>
        <div class="options" style="padding: 20px">
            <button class="btn btn-primary" id="editorSave">新增</button>
            <button class="btn btn-light" id="editorCancel">取消</button>
            <div class="custom-control custom-toggle pull-right public">
                <input type="checkbox" id="public" name="customToggle1" class="custom-control-input" checked>
                <label class="custom-control-label" for="public">是否公开</label>
            </div>
        </div>
    </div>
    <div id="showNote" style="padding-top: 40px">
        <div class="row">
        </div>
        <%--<div class="alertImg" style="width:30%;margin: 0 auto;display: none">--%>
        <%--<img src="<%=request.getContextPath()%>/skins/img/note.png" style=" width:100%;height:auto;margin: 0 auto">--%>
        <%--</div>--%>
        <%--<div class="alertImg" style="width:60%;margin: 0 auto;display: none;text-align: center;color: #5e5e5e">--%>
        <%--<h4>亲，您现在还未编写任何便签，点击右上角橙色按钮，即可添加新便签！</h4>--%>
        <%--</div>--%>
    </div>
</div>

<!-- delete Type Modal -->
<div class="modal fade" id="deleteTypeModal" tabindex="-1" role="dialog" aria-labelledby="deleteTypeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="z-index: 1000000000">
        <div class="modal-content" id="model">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteTypeModalLabel">删除便签</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>便签删除后将不可恢复，确定要删除吗？</h6>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" id="deleteType">删除</button>
            </div>
        </div>
    </div>
</div>

<!-- show note detail -->
<div class="modal fade" id="showNoteDetail" tabindex="-1" role="dialog" aria-labelledby="showNoteDetail"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="z-index: 1000000000">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">详情</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modifyEdit">
            </div>
            <div style="margin-right: 40px;margin-bottom: 10px">
                <div class="custom-control custom-toggle pull-right public">
                    <input type="checkbox" id="modifyPublic" name="customToggle1" class="custom-control-input" checked>
                    <label class="custom-control-label" for="modifyPublic">是否公开</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveModify">保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="showNoteItems">
    <div class="col-lg-4 showNoteItems animation-active animation-hover">
        <div class="content">
            <div class="" style="padding:20px">
                <span class="pull-right myClose" title="删除此便签" onclick="deleteNote()" id="[noteId]"></span>
                <div class="noteContent" id="[contentId]" style="height:180px;margin-top: 5px">
                    [noteContent]
                </div>
                <a class="more pull-right" id="[moreId]" onclick="moreInfo(this)">more</a>
                <hr>
                <div class="createInfo">
                    作者：[createName]
                    <span class="pull-right" id="[stateId]">状态：[isPublic]</span>
                    </br>时间：[createTime]
                    <span class="pull-right">
                    <span class="addLike"><img class="heart" src="<%=request.getContextPath()%>/skins/img/heart.png"
                                               title="喜欢就点个赞吧" onclick="addLike()"
                                               id="[likeId]"><span>[likeNumber]</span></span>
                    人喜欢
               </span>
                </div>
            </div>
        </div>
    </div>
</script>
<script>

</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/noteManager.js"></script>
</body>
</html>
