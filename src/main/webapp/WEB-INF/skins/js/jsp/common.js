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
    "getPublicNote":context+"/note/findPublicNote",
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
    "getBookList":context+"/book/findAllList",
    "findUserTop":context+"/book/findUserRank"
}

//时间转换
function getLocalTime(S) {
    return new Date(parseInt(S)).toLocaleString();
}

function Dialog(args) {
    var time = new Date().getTime(), modal_id = 'modal-' + time, modal_label = 'label' + time,
        okId = 'okBtn-' + time, noId = 'noBtn-' + time;
    //btn class
    var titleText = isEmpty(args['title']['text']) ? '提示' : args['title']['text'];
    var titleClass = isEmpty(args['title']['text']) ? '' : args['title']['class'];
    var modalBody = args['body'];
    var okBtnClass = isEmpty(args['okBtn']['class']) ? 'btn-primary' : args['okBtn']['class'];
    var noBtnClass = isEmpty(args['noBtn']['class']) ? 'btn-light' : args['noBtn']['class'];
    var okBtnText = isEmpty(args['okBtn']['text']) ? '确定' : args['okBtn']['text'];
    var noBtnText = isEmpty(args['noBtn']['text']) ? '取消' : args['noBtn']['text'];
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



