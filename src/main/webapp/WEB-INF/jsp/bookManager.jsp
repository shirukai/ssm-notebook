<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <title>笔记管理</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/wangEditor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/showdown.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/datatables.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/jsp/bookManager.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/skins/css/datatables.css">
</head>
<body>
<!-- 导航栏 -->
<%@include file="common/topNav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-3">
            <div id="bookType">
                <table id="bookTypeList" class="table table-striped">
                    <thead>
                    <tr>
                        <th id="writeBooks" class="animation-active animation-hover text-center">
                            <span class="fa fa-pencil-square-o">&nbsp;&nbsp;</span>写笔记
                        </th>
                    </tr>
                    </thead>
                </table>
                <h4 class="text-center">
                    <i class="fa fa-plus animation-active animation-hover insert-type" title="添加分类"
                       data-toggle="modal" data-target="#addTypeModal"></i>
                    <i id="deleteType" class="fa fa-minus animation-active animation-hove delete-type" title="删除分类"></i>
                </h4>
            </div>
        </div>
        <div class="col-lg-9 views">
            <div class="input-group input-group-seamless">
                <input type="text" class="form-control" id="searchInput" placeholder="请输入搜索条件">
                <span class="input-group-append">
                    <span class="input-group-text" id="searchIcon">
                        <i class="fa fa-search"></i>
                    </span>
                </span>
            </div>
            <!-- 笔记列表 -->
            <div class="book wrapper" id="bookList">
                <table id="bookTableList" class="table table-bordered table-hover table-striped">
                    <thead>
                    <tr>
                        <th class="book-table-head">
                            <span class="pull-left"><a id="allBooks">所有笔记/</a>
                                <i id="l_type"></i>
                            </span>
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>
            <!--编写笔记-->
            <div class="book wrapper" id="bookEdit">
                <div class="pull-right">
                    <h6><span class="fa fa-clock-o"></span><span class="nowTime"></span></h6>
                </div>
                <br>
                <div class="form-group">
                    <label for="checkType"></label>
                    <select class="custom-select" id="checkType"></select>
                </div>
                <div class="input-group input-group-seamless">
                    <input type="file" id="displayFile" style="display: none">
                    <input type="text" class="form-control" id="booksTitle" placeholder="笔记标题（支持上传markdown或者html文件）">
                    <span class="input-group-append" onclick="$('input[id=displayFile]').click();">
                        <span class="input-group-text">
                            <i class="fa fa-upload"></i>
                        </span>
                    </span>
                </div>
                <div id="bookEditor">
                    <h4>正文：</h4>
                </div>
                <div class="options">
                    <button class="btn btn-primary" id="editorSave">保存</button>
                    <button class="btn btn-light" id="editorCancel">关闭</button>
                    <div class="custom-control custom-toggle pull-right public">
                        <input type="checkbox" id="isPublic" name="customToggle1" class="custom-control-input" checked>
                        <label class="custom-control-label" for="isPublic">是否公开</label>
                    </div>

                </div>
            </div>
            <!--查看笔记-->
            <div class="book wrapper" id="bookView">
                <input type="hidden" id="b_id" value="">
                <input type="hidden" id="t_id" value="">
                <div class="pull-right">
                    <h6><span class="fa fa-clock-o"></span>&nbsp;&nbsp;创建时间：<span
                            id="b_time"></span></h6>
                    <h6><span class="fa fa-th-large"></span>&nbsp;&nbsp;分类：<a id="b_type"></a></h6>
                    <h6><span class="fa fa-eye"></span>&nbsp;&nbsp;状态：<a id="b_state"></a></h6>
                </div>
                <br>
                <div style="margin-top:58px">
                    <a id="b_title" style="font-size: 20px" target="_blank"></a>
                    <span class="pull-right" style="margin-top: 3px">
                            <button id="b_return" class="btn btn-xm btn-primary" style="margin-right:5px">返回</button>
                            <button id="b_modify" class="btn btn-xm btn-info" style="margin-right:5px">修改</button>
                            <button id="b_delete" class="btn  btn-xm btn-danger" style="margin-right:5px">删除</button>
                            <button id="b_share" class="btn  btn-xm btn-warning ">分享</button>
                        </span>
                </div>
                <hr>
                <div id="b_content">

                </div>
                <hr>
                <div class="pull-right">
                    <h6><span class="fa fa-pencil"></span><span id="createUser">本笔记由notebook发布</span></h6>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- add Type Modal -->
<div class="modal fade" id="addTypeModal" tabindex="-1" role="dialog" aria-labelledby="addTypeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addTypeModalLabel">添加分类</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="input-group input-group-seamless">
                    <input type="text" class="form-control" id="newType" placeholder="请输入要添加的分类名">
                    <span class="input-group-append">
                    <span class="input-group-text">
                        <i class="fa fa fa-th-large"></i>
                    </span>
                </span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="addType">添加</button>
            </div>
        </div>
    </div>
</div>
<!-- Upload File Type Error Modal -->
<div class="modal fade" id="typeErrorModal" tabindex="-1" role="dialog" aria-labelledby="typeErrorModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title danger" id="typeErrorModalLabel">文件读取错误</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true" class="danger">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>仅支持支持上传MarkDown格式，以及HTML格式文件。</h6>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="$('#typeErrorModal').modal('hide')">确定</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/skins/js/jsp/bookManager.js"></script>
</body>
</html>
