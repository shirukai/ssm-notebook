//获取登录信息
if (userInfo) {
    $('.comment-publish>.col-1>img').attr('src', userInfo['avatar']);
}
getAllComment()
$('#send').click(function () {
    var te = $('.comment-publish>.comment-input>textarea')
    var content = te.val();
    if (!userInfo) {
        var dialog = new Dialog({
            title: {
                class: '',
                text: "未登录"
            },
            body: '<h6>发表评论之前，请先登录。</h6>',
            headerClass: '',
            okBtn: {
                class: '',
                text: '登录'
            },
            noBtn: {
                class: 'btn-light',
                text: '注册'
            },
            no: function () {
                window.location.href = "/notebook/register"
            },
            ok: function () {
                window.location.href = "/notebook/login"
            }
        })
        dialog.open()
    } else if (content) {
        var data = {
            answerUid: $('#u_id').val(),
            senderUid: userInfo['uid'],
            bid: $('#b_id').val(),
            content: content
        }
        $.ajax({
            url: API['insertComment'],
            type: 'POST',
            data: data,
            success: function (re) {
                console.log(re)
            }
        })
        te.removeClass('is-invalid')
    } else {
        te.addClass('is-invalid')
    }
});

function getAllComment() {
    $.ajax({
        url: API['getCommentByBid'],
        type: 'GET',
        data: {'bid': $('#b_id').val()},
        success: function (re) {
            console.log(re)
            if (re['state'] > 0) {
                $('#commentNumber').text(re['data'].length)
            }
        }
    })

}
