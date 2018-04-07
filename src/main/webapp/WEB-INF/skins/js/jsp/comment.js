var b_id = $('#b_id').val(), isLogin = false;
//获取登录信息
if (userInfo) {
    $('.comment-publish>.col-1>img').attr('src', userInfo['avatar']);
    isLogin = true;
}
getAllComment()
$('#send').click(function () {
    var te = $('.comment-publish>.comment-input>textarea');
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
            bid: b_id,
            content: content
        }
        $.ajax({
            url: API['insertComment'],
            type: 'POST',
            data: data,
            success: function (re) {
                var args = {
                    body: '😭 评论失败',
                    type: 'alert-danger'
                }
                if (re['state'] === 1) {
                    args = {
                        body: '😄 评论成功',
                        type: 'alert-success'
                    }
                    $('.list-all').html('')
                    getAllComment()
                }
                Alert(args)

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
        data: {'bid': b_id},
        success: function (re) {
            console.log(re)
            if (re['state'] > 0) {
                $('#commentNumber').text(re['data'].length)
                $.each(re['data'], function (ind, val) {
                    var reply = '';
                    $.each(val['interactives'], function (ind, inter) {
                        reply += replys(inter, val['cid'])
                    })
                    addCommentToView(val, reply);
                })
            }
        }
    })
}

function addCommentToView(val, reply) {
    try {
        var id = val['senderUid'] === userInfo['uid'] || userInfo['uid'] === b_id ? val['cid'] : '';
    } catch (e) {
        id = ''
    }
    var html = document.getElementById("comment-template").innerHTML,
        reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'),
        source = html.replace(reg, function (node, key) {
            return {
                'uid': val['senderUid'],
                'cid': val['cid'],
                'avatar': val['senderAvatar'],
                'name': val['senderName'],
                'time': getLocalTime(val['createTime']),
                'content': val['content'],
                'like': val['likeNumber'],
                'options': options(id),
                'reply-items': reply,
                'input-id': 'input-' + val['cid']
            }[key]
        });//通过json格式的数据来匹配模板数据
    $('.list-all').append(source)
}

function options(id) {
    var d = '<button id="' + id + '" type="button" class="btn btn-xm btn-outline-danger btn-pill option-btn" onclick="delComment(this)">删除</button>'

    return id === '' ? '' : d

}

function replys(val, cid) {
    var html = document.getElementById("reply-item").innerHTML,
        reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'),
        source = html.replace(reg, function (node, key) {
            return {
                'id': val['iid'],
                'data-cid': cid,
                'sender-uid': val['senderUid'],
                'answer-uid': val['answerUid'],
                'time': getLocalTime(val['createTime']),
                'sender': val['senderName'],
                'answer': val['answerName'],
                'content': val['content']
            }[key]
        });
    return source;
}

function showReply(e) {
    if (!isLogin) {
        Alert({type: 'alert-info', body: '请登录，才可以参与互动'})
    } else {
        var answerUid = $(e).attr('data-uid'), cid = $(e).attr('data-cid'), name = $(e).text();
        $('#' + 'input-' + cid).removeClass('hide').children('input').attr({
            'placeholder': '回复' + $.trim(name) + ':',
            'data-sender-uid': userInfo['uid'],
            'data-answer-uid': answerUid,
            'data-cid': cid
        })
    }

}

function delComment(e) {
    $.ajax({
        url: API['deleteByCid'],
        type: 'GET',
        data: {'cid': $(e).attr('id')},
        success: function (re) {
            if (re['state'] === 1) {
                $('.list-all').html('')
                getAllComment()
            }
        }

    })
}

function sendReply(e) {
    var el = $(e).siblings('input')
    var data = {
        senderUid: el.attr('data-sender-uid'),
        answerUid: el.attr('data-answer-uid'),
        cid: el.attr('data-cid'),
        content: el.val()
    }
    $.ajax({
        url: API['insertInteractive'],
        type: 'POST',
        data: data,
        success: function (re) {
            if (re['state'] === 1) {
                $('.list-all').html('')
                getAllComment()
            }
        }
    })
}

function addLike(e) {
    var el = $(e), cid = el.attr('data-cid'), num = parseInt(el.siblings('span').text());
    $.ajax({
        url: API['commentLike'],
        type: 'GET',
        data: {cid: cid},
        success: function (re) {
            if (re['state'] === 1) {
                el.siblings('span').text(num + 1)
            }
        }
    })
}
