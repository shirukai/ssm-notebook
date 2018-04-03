var bookTypeTable, bookListTable, typeInfo = {}, bookEdit, E = window.wangEditor, isEditBook;
var b_state = ['不公开', '公开'];
//获取当前时间
var mydate = new Date();
var createTime = mydate.toLocaleString();
$(".nowTime").text(createTime);
var editor = new E('#bookEditor');
initTypeTable();
initBookTable('bookByUid', "");
editor.create();
var bookEditor = $('#bookEditor');
bookEditor.children().eq(0).css('background-color', '#FFFFFF');
bookEditor.children().eq(1).height(610)
//写笔记
$('#writeBooks').click(function () {
    isEditBook = true;
    $('#booksTitle').val('')
    bookEditor.children().eq(1).children().html('<h4>正文：</h4>')
    changController("bookEdit")
});
//添加分类
$('#addType').click(function () {
    var newType = $('#newType');
    if (newType.val() === null || newType.val() === "" || newType.val() === undefined) {
        newType.addClass("is-invalid")
    } else {
        newType.removeClass("is-invalid").addClass("is-valid")
        addType(newType.val())
    }
});
changController('bookList');
//删除分类按钮切换
$("#deleteType").click(function () {
    $(".deleteType").toggle(10)
});

$('#editorSave').click(function () {
    saveOrUpdateBook();
});
$('#editorCancel').click(function () {
    changController('bookList')
});
//笔记操作
$('#b_return').click(function () {
    changController('bookList')
});
$('#b_modify').click(function () {
    isEditBook = false;
    //分类
    $("#checkType").val($('#b_type').attr('title'));
    //题目
    $('#booksTitle').val($('#b_title').text())
    //内容
    bookEditor.children().eq(1).children().html($('#b_content').html())
    //是否公开
    $('#isPublic')[0].checked = $('#b_state').text() === "公开";
    changController("bookEdit")
});
$('#b_delete').click(function () {
    var deleteBookDialog = new Dialog({
        title: {
            class: 'danger',
            text: "删除笔记"
        },
        body: '<h6>笔记删除后将无法恢复，确定要删除吗？</h6>',
        headerClass: '',
        okBtn: {
            class: '',
            text: ''
        },
        noBtn: {
            class: 'btn-danger',
            text: '取消'
        },
        no: function () {

        },
        ok: function () {
            $.ajax({
                type: 'GET',
                url: API['deleteBook'],
                data: {'bid': $('#b_id').val(), 'uid': userInfo['uid']},
                success: function (data) {
                    bookTypeTable.destroy();
                    initTypeTable();
                }
            });
        }
    });
    deleteBookDialog.open()
});

//视图切换控制器
function changController(target) {
    var v_ids = ['bookList', 'bookEdit', 'bookView'];
    v_ids.forEach(function (value) {
        if (target !== value) {
            $('#' + value).hide();
        }
        $('#' + target).show();
    })
}

//添加分类
function addType(type) {
    $.ajax({
        type: "POST",
        url: API['addType'],
        data: {'type': type, 'uid': userInfo['uid']},
        success: function (data) {
            if (data['state'] === 1) {
                bookTypeTable.destroy();
                initTypeTable();
                $('#addTypeModal').modal('hide')
            }
        }
    })
}

//删除分类
function deleteType(tid) {
    event.stopPropagation();
    var deleteTypeDialog = new Dialog({
        title: {
            class: 'danger',
            text: "删除分类"
        },
        body: '<h6>分类被删除后，该分类下的所有笔记将归类到为分类状态，确定要删除吗？</h6>',
        headerClass: '',
        okBtn: {
            class: '',
            text: ''
        },
        noBtn: {
            class: 'btn-danger',
            text: '取消'
        },
        no: function () {

        },
        ok: function () {
            $.ajax({
                type: 'GET',
                url: API['deleteType'],
                data: {'tid': tid, 'uid': userInfo['uid']},
                success: function (data) {
                    bookTypeTable.destroy();
                    initTypeTable();
                }
            });
        }
    });
    deleteTypeDialog.open()
}

//查看分类下的笔记
function viewTypeDetail(tid, type) {
    bookListTable.destroy();
    initBookTable('bookByType', tid)
    $('#l_type').text(type)
    changController("bookList")
}

//查看笔记详情
function viewBookDetail(bid) {
    $.ajax({
        type: 'GET',
        url: API['bookDetail'],
        data: {'bid': bid},
        success: function (data) {
            if (data['state'] === 1) {
                $('#b_id').val(data['data']['bid']);
                $('#b_time').text(getLocalTime(data['data']['createTime']));
                $('#b_type').text(data['data']['type']).attr('title', data['data']['tid']);
                $('#b_title').text(data['data']['bookTitle']);
                $('#b_state').text(b_state[data['data']['isPublic']])
                $('#b_content').html(data['data']['bookContent']);
                changController("bookView")
            }
        }
    });
}

//初始化分类列表
function initTypeTable() {
    typeInfo = {}
    bookTypeTable = $("#bookTypeList").dtable({
        //显示“正在加载”的图标
        "processing": true,
        "serverSide": false,//开启服务器服务，开启之后将不能进前端分页
        "paging": false,//是否分页
        "iDisplayLength": 10, //显示五行数据
        "bInfo": false,//是否显示底部信息，默认开启
        "ordering": false,
        "pagingType": "simple_numbers",//翻页栏样式
        "ajax": API['getTypes'] + "?uid=" + userInfo['uid'],
        "columns": [
            {
                "data": "type"
            }
        ],
        "columnDefs": [
            {
                "targets": 0,
                "data": "type",
                "render": function (data, type, full) {
                    typeInfo[full.tid] = full.type
                    var html = '<div class="typeNode" onclick="viewTypeDetail(' + "'" + full.tid + "'" + ',' + "'" + full.type + "'" + ')">' +
                        '<span class="typeName">' + data + '</span>(' + full.bookNumber + ')' +
                        '<button class="pull-right btn  btn-xm btn-danger deleteType" style="margin-right: 20px" onclick="deleteType(' + "'" + full.tid + "'" + ')">删除</button></div>';
                    return html;
                }
            }
        ]
    });
    setTimeout(function () {
        renderType()
    }, 300);
    return bookTypeTable;
}

function initBookTable(type, param) {
    var ajaxURL = {
        'bookByType': API['bookByType'] + '?uid=' + userInfo['uid'] + '&tid=' + param,
        'bookByReg': API['bookByReg'] + '?uid=' + userInfo['uid'] + '&reg=' + param,
        'bookByUid': API['bookByUid'] + '?uid=' + userInfo['uid']
    }
    bookListTable = $("#bookTableList").DataTable({
        //显示“正在加载”的图标
        "processing": true,
        "serverSide": true,//开启服务器服务，开启之后将不能进前端分页
        "paging": true,//是否分页
        "iDisplayLength": 10, //显示五行数据
        "bInfo": true,//是否显示底部信息，默认开启
        "ordering": false,
        "pagingType": "simple_numbers",//翻页栏样式
        "ajax": ajaxURL[type],
        "columns": [
            {
                "data": "bookTitle"
            }
        ],
        "columnDefs": [
            {
                "targets": 0,
                "data": "bookTitle",
                "render": function (data, type, full) {
                    var createTime = getLocalTime(full.modifyTime);
                    var bookTitle = data;
                    var status = "不公开";
                    if (full.isPublic === 1) {
                        status = "公开"
                    }
                    var Options = '<div class="animation-hover animation-active book-item" onclick="viewBookDetail(' + "'" + full.bid + "'" + ')"><span class="list_title pull-left"><span class="fa fa-file-text-o"></span>&nbsp;&nbsp;' + bookTitle + '</span>' +
                        '<span class="list_p pull-right"><span class="fa fa-clock-o"></span>' + createTime + '&nbsp;&nbsp;&nbsp;&nbsp;状态：' + status + '</span></div>';
                    return Options;
                }
            }
        ],
        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoEmpty": "没有数据",
            "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
            "sZeroRecords": "没有检索到数据",
            "sSearch": "模糊查询:  ",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            }
        }
    });
    return bookListTable
}

//渲染分类到富文本
function renderType() {
    var typeHTML = "<option>选择笔记分类</option>"
    $.each(typeInfo, function (key, value) {
        typeHTML += "<option value='" + key + "'>" + value + "</option>"
    });
    $('#checkType').html(typeHTML)
}


//上传markdowm、html文件
$("#displayFile").change(function () {
    //获取文件名
    var fileName = $(this).val();
    //截取文件名和扩展名
    var begin = fileName.lastIndexOf('\\') + 1, end = fileName.lastIndexOf('.');
    var name = fileName.substring(begin, end), type = fileName.substr(end);
    //获取文件
    var files = $(this).prop('files'), reader = new FileReader();
    reader.readAsText(files[0], "UTF-8");//读取文件
    reader.onload = function (evt) {
        var fileString = evt.target.result;
        if (type === ".md") {
            //进行转换
            $("#booksTitle").val(name);
            var converter = new showdown.Converter();
            editor.txt.html(converter.makeHtml(fileString))
        } else if (type === ".html") {
            $("#booksTitle").val(name);
            editor.txt.html(fileString)
        } else {
            $('#typeErrorModal').modal('show')
        }
    }
});


//保存笔记
function saveOrUpdateBook() {
    var data = {
        uid: userInfo['uid'],
        tid: $("#checkType").val(),
        bookContent: editor.txt.html(),
        bookTitle: $("#booksTitle").val(),
        isPublic: $('#isPublic')[0].checked ? 1 : 0
    };
    var saveOrUpdateURL;
    if (isEditBook) {
        saveOrUpdateURL = API['saveBook']
    } else {
        saveOrUpdateURL = API['updateBook'];
        data['bid'] = $('#b_id').val()
    }
    $.ajax({
        type: "POST",
        url: saveOrUpdateURL,
        data: data,
        success: function (data) {
            bookTypeTable.destroy();
            bookListTable.destroy();
            initTypeTable();
            $('#l_type').text('')
            initBookTable("bookByUid", "");
            changController("bookList")

        },
        error: function () {
        }
    })
}

//添加监听滚动事件
window.addEventListener('scroll', handleScroll);

function handleScroll() {
    //鼠标滚动高度
    var scrollTop = $(window).scrollTop();
    $('#bookType')[0].style.marginTop = scrollTop + 2 + 'px';
}
