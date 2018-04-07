// 获取userInfo
var userInfo = $.cookie("userInfo");
if (userInfo && userInfo !== null) {
    userInfo = JSON.parse(userInfo);
}
var API = {
    "insertUser": context + "/user/insert",
    "uploadAvatar": context + "/user/upload/avatar",
    "checkUserName": context + "/user/check/username",
    "register": context + "/user/insert",
    "verification": context + "/user/verification",
    "addNote": context + "/admin/note/insert",
    "getNoteByPage": context + "/admin/note/page",
    "deleteNote": context + "/admin/note/delete",
    "updateNote": context + "/admin/note/update",
    "addLike": context + "/note/addLike",
    "getPublicNote": context + "/note/findPublicNote",
    "getTypes": context + "/admin/book/type/all",
    "addType": context + "/admin/book/type/insert",
    "deleteType": context + "/admin/book/type/delete",
    "bookByType": context + "/admin/book/bookByTid",
    "bookByReg": context + "/admin/book/bookByReg",
    "bookByUid": context + "/admin/book/bookByUid",
    "bookDetail": context + "/book/bookDetail",
    "saveBook": context + "/admin/book/insert",
    "updateBook": context + "/admin/book/update",
    "deleteBook": context + "/admin/book/delete",
    "getBookList": context + "/book/findAllList",
    "getUserInfo": context + "/user/getUserInfo",
    "findUserTop": context + "/book/findUserRank",
    "insertComment": context + "/comment/insert",
    "getCommentByBid": context + "/comment/getComment",
    "deleteCommentBySender": context + "/comment/deleteCommentBySender",
    "deleteCommentByAnswer": context + "/comment/deleteCommentByAnswer",
    "insertInteractive": context + "/comment/insertInteractive",
    "deleteByCid": context + "/comment/deleteByCid",
    'commentLike': context + "/comment/addLikeNumber",
    "updateUserInfo": context + "/user/updateUserInfo"

}

//时间转换
function getLocalTime(S) {
    return new Date(parseInt(S)).toLocaleString();
}

function Dialog(args) {
    var time = new Date().getTime(), modal_id = 'modal-' + time, modal_label = 'label' + time,
        okId = 'okBtn-' + time, noId = 'noBtn-' + time;
    //btn class
    var titleText = '提示';
    var titleClass = '';
    var okBtnClass = 'btn-primary';
    var noBtnClass = 'btn-light';
    var okBtnText = '确定';
    var noBtnText = '取消';
    try {
        titleText = args['title']['text'];
    } catch (e) {
    }
    try {
        titleClass = args['title']['class'];
    } catch (e) {
    }


    try {
        okBtnClass = args['okBtn']['class'];
    } catch (e) {
    }
    try {
        noBtnClass = args['noBtn']['class'];
    } catch (e) {
    }
    try {
        okBtnText = args['okBtn']['text'];
    } catch (e) {
    }
    try {
        noBtnText = args['noBtn']['text'];
    } catch (e) {
    }
    var modalBody = args['body'];
    var template = '<div class="modal fade" id="' + modal_id + '" tabindex="-1" role="dialog" aria-labelledby="' + modal_label + '" aria-hidden="true"><div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-header"><h5 class="modal-title ' + titleClass + '" id="' + modal_label + '">' + titleText + '</h5><button type="button" class="close ' + titleClass + '" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button></div><div class="modal-body">' + modalBody + '</div><div class="modal-footer"><button type="button" class="btn ' + noBtnClass + '" id="' + noId + '">' + noBtnText + '</button><button type="button" class="btn ' + okBtnClass + '" id="' + okId + '">' + okBtnText + '</button></div></div></div></div>'
    $('body').append(template);
    var m = $('#' + modal_id);
    $('#' + noId).click(function () {
        if (!isEmpty(args['no'])) {
            args['no']();
        }
        m.modal('hide')
    });
    $('#' + okId).click(function () {
        if (!isEmpty(args['ok'])) {
            args['ok']();
        }
        m.modal('hide')
    });
    this.open = function () {
        m.modal('show')
    };
    this.close = function () {
        m.modal('hide')
    }
}

function isEmpty(object) {
    return object === null || object === undefined || object === ''
}


function Alert(args) {
    var id = "alert-" + new Date().getTime()
    var type = args['type'] ? args['type'] : 'alert-success', content = args['body'] ? args['body'] : 'success',
        time = args['time'] ? args['time'] : 2000
    var html = '<div id="' + id + '" class="alert ' + type + ' alert-dismissible fade show" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><div class="content">' + content + '</div></div>'
    $('body').append(html);
    var a = $('#' + id)
    a.fadeIn(200, function () {
        setTimeout(function () {
            a.fadeOut(600, function () {
                a.remove()
            })
        }, time)
    })
}


