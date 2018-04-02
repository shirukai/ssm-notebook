function Dialog(args) {
    var time = new Date().getTime(), modal_id = 'modal-' + time, modal_label = 'label' + time,
        okId = 'okBtn-' + time, noId = 'noBtn-' + time;
    //btn class
    var modalTitle = isEmpty(args['title']) ? '提示' : args['title'];
    var modalBody = args['body'];
    var okBtnClass = isEmpty(args['okBtn']['class']) ? 'btn-primary' : args['okBtn']['class'];
    var noBtnClass = isEmpty(args['noBtn']['class']) ? 'btn-light' : args['noBtn']['class'];
    var okBtnText = isEmpty(args['okBtn']['text']) ? '确定' : args['okBtn']['text'];
    var noBtnText = isEmpty(args['noBtn']['text']) ? '取消' : args['noBtn']['text'];
    var template = '<div class="modal fade" id="' + modal_id + '" tabindex="-1" role="dialog" aria-labelledby="' + modal_label + '" aria-hidden="true"><div class="modal-dialog" role="document"><div class="modal-content"><div class="modal-header"><h5 class="modal-title" id="' + modal_label + '">' + modalTitle + '</h5><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button></div><div class="modal-body">' + modalBody + '</div><div class="modal-footer"><button type="button" class="btn ' + noBtnClass + '" data-dismiss="modal" id="' + noId + '">' + noBtnText + '</button><button type="button" class="btn ' + okBtnClass + '" id="' + okId + '">' + okBtnText + '</button></div></div></div></div>'
    $('body').append(template);
    var m = $('#' + modal_id);
    $('#' + noId).click(function () {
        this.ok()
    });
    $('#' + okId).click(function () {
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
