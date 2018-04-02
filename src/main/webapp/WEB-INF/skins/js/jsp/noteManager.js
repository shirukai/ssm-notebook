//富文本编辑器
var E = window.wangEditor;
var editor = new E('#editor');
editor.create();
loadNote({'uid': userInfo['uid']})
//点击+下拉编辑
$(".add,#editorCancel").click(function () {
    if (!$(".add,#editor").is(":animated")) {
        $(".options").toggle(800, function () {
        });
        $(".add").toggleClass("addTransform");
        $("#editor").slideToggle(600);
    }
});

//新增便签事件
$("#editorSave").click(function () {
    var noteContent = editor.txt.html(),
        isPublic = $('#public')[0].checked ? 1 : 0;
    var data = {
        noteContent: noteContent,
        isPublic: isPublic,
        uid: userInfo['uid'],
    }
    var mydate = new Date();
    var createTime = mydate.toLocaleString();
    $.ajax({
        url: API['addNote'],
        type: 'POST',
        data: data,
        success: function (result) {
            console.log(result);
            if (result['state'] === 1) {
                data['nickName'] = userInfo['nickName'];
                data['sid'] = result['data'];
                data['createTime'] = createTime;
                data['likeNumber'] = 0
                addNoteToView(data)
            } else {

            }
        }
    })
});

//加载便签
function loadNote(pageInfo) {
    $.ajax({
        url: API['getNoteByPage'],
        type: 'POST',
        data: pageInfo,
        success: function (result) {
            console.log(result);
            if (result['state'] !== 0) {
                $.each(result['data'], function (index, info) {
                    info['createTime'] = getLocalTime(info['modifyTime']);
                    info['nickName'] = userInfo['nickName'];
                    addNoteToView(info)
                })
            } else {

            }
        }
    })
}

//删除便签
function deleteNote() {
    var id = event.target.id
    $('#deleteTypeModal').modal('show')

    $('#deleteType').click(function () {
        $.ajax({
            url: API['deleteNote'],
            type: 'GET',
            data: {sid: id},
            success: function (result) {
                console.log(result);
                if (result['state'] !== 0) {
                    $('#' + id).parent().parent().parent().hide()
                } else {

                }
            }
        })
        $('#deleteTypeModal').modal('hide')
    })
}

// 查看更多
function moreInfo(e) {
    var id = $(e).siblings('.myClose').attr('id')
    var html = $(e).siblings('.noteContent').html()
    var state = $('#'+id+'state').text()
    $('#modifyPublic')[0].checked = state==="状态：公开"
    var md = $('#showNoteDetail')
    md.find('.modal-body').html(html)
    var modifyEditor = new E('#modifyEdit')
    modifyEditor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        //'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        //'link',  // 插入链接
        //'list',  // 列表
        'justify',  // 对齐方式
        //'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        //'table',  // 表格
        //'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    modifyEditor.create();
    md.modal('show')
    $('#saveModify').click(function () {
        var noteContent = modifyEditor.txt.html()
        var isPublic = $('#modifyPublic')[0].checked ? 1 : 0;
        var data = {
            noteContent: noteContent,
            isPublic: isPublic,
            sid: id
        }
        $.ajax({
                url: API['updateNote'],
                type: 'POST',
                data: data,
                success: function (result) {
                    console.log(result);
                    if (result['state'] === 1) {
                        var stateText = isPublic?"状态：公开":"状态：不公开" ;
                        $(e).siblings('.noteContent').html(noteContent)
                        $('#'+id+'state').text(stateText)
                        md.modal('hide')
                    } else {

                    }
                }
            })
    })
}
//喜欢
function addLike() {
    //获取当前点击事件的id
    var id=event.target.id;
    //获取当前元素的下一个元素
    var text = document.getElementById(id).nextElementSibling;
    //获取元素的文本内容
    var oldLikeNumber = text.textContent;
    text.textContent = parseInt(oldLikeNumber)+1;
    //截取id数字部分
    var likeId= id.substring(0,36);
    $.ajax({
        type:'GET',
        url:API['addLike'],
        data:{
            sid:likeId
        },
        success:function (data) {
        },
        error:function () {
            alert("点赞失败")
        }
    })
}
//更新页面
function addNoteToView(info) {
    var sid = info['sid'], likeId = sid + "like", moreId = sid + "more", contentId = sid + "content",
        stateId = sid + "state",
        html = document.getElementById("showNoteItems").innerHTML, reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'),
        source = html.replace(reg, function (node, key) {
            return {
                'noteId': sid,
                'noteContent': info['noteContent'],
                'createName': info['nickName'],
                'isPublic': info['isPublic'] === 1 ? "公开" : "不公开",
                'stateId': stateId,
                'createTime': info['createTime'],
                'likeNumber': info['likeNumber'],
                'likeId': likeId,
                'moreId': moreId,
                'contentId': contentId
            }[key]
        });//通过json格式的数据来匹配模板数据
    //将模板加载到html
    $("#showNote .row").append(source)
}
