//获取用户信息
if (userInfo !== undefined) {
    $.ajax({
        url: API['getUserInfo'],
        type: "GET",
        data: {'uid': userInfo['uid']},
        success: function (d) {
            $('#userHeadImg').attr('src', d['data']['avatar']);
            $('.nickName').text(d['data']['nickName']);
            $('.useName').text(d['data']['userName']);
            $('#noteNumber').text(d['data']['noteNumber']);
            $('#bookNumber').text(d['data']['bookNumber'])
        }
    })
}
loadNote();
loadBooks();
if (myInfo) {
    showUserInfo();
}
$('.info-close').click(function () {
    window.history.go(-1)
})
//更改头像
$('.head .avatar').bind('click', function () {
    $('.head #avatarFile').click()
});
$('#avatarFile').change(function () {
    //获取文件
    var files = $(this)[0].files,
        fileName = files[0].name,
        fileType = getExpandedName(fileName),
        ofr = new FileReader();
    // ofr.readAsDataURL(files[0])
    // ofr.onloadend = function (ev) {
    // }
    if (checkType(fileType)) {
        uploadAvatar(files[0])
    }
})

//更新用户信息
$('#updateUser').click(function () {
    $.ajax({
        url: API['updateUserInfo'],
        type: 'POST',
        data: $('#updateFrom').serialize() + "&userName=" + userInfo['userName'] + "&uid=" + userInfo['uid'],
        success: function (data) {
            if (data['state'] === 1) {
                Alert({type: 'alert-success', body: data['data']})
            } else {
                Alert({type: 'alert-danger', body: data['data']})
            }
        }
    });
})

function target(e) {
    window.open($(e).attr('href'))
}

//加载便签
function loadNote() {
    $.ajax({
        url: API['getPublicNote'],
        type: 'GET',
        data: {page: 1, pageSize: 6},
        success: function (result) {
            if (result['state'] !== 0) {
                $.each(result['data'], function (index, info) {
                    info['createTime'] = getLocalTime(info['modifyTime']);
                    info['nickName'] = info['nickName'];
                    addNoteToView(info)
                })
            } else {

            }
        }
    })
}

//加载笔记
function loadBooks() {
    $.ajax({
        url: API['getBookList'],
        type: 'GET',
        data: {start: 0, length: 25},
        success: function (result) {
            console.log(result);
            if (result['state'] !== 0) {
                $.each(result['aaData'], function (index, info) {
                    $('.showBookItems').append(getHTML(info['bid'], info['bookTitle'], info['createTime']))
                });
            } else {

            }
        }
    });
}

function getHTML(b_id, b_title, b_time) {
    var time = formatDate(b_time), href = '/notebook/view/' + b_id;
    return '<div class="book-item"><div onclick="target(this)" href="' + href + '"  class="title pull-left">' + b_title + '</div><span class="pull-right time">' + time + '</span></div>'
}

//更新页面
function addNoteToView(info) {
    var sid = info['sid'], likeId = sid + "like", contentId = sid + "content",
        html = document.getElementById("showNoteItem").innerHTML, reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'),
        source = html.replace(reg, function (node, key) {
            return {
                'noteId': sid,
                'noteContent': info['noteContent'],
                'createName': info['nickName'],
                'createTime': info['createTime'],
                'likeNumber': info['likeNumber'],
                'likeId': likeId,
                'contentId': contentId
            }[key]
        });//通过json格式的数据来匹配模板数据
    //将模板加载到html
    $(".showNoteItems").append(source)
}

function moreInfo(e) {
    var el = $(e).parent(), t = $(e);
    if (t.text() === 'more') {
        el.height('auto').find('.noteContent').height('auto');
        t.text('close')
    } else {
        el.height('160px').find('.noteContent').height('90px');
        t.text('more')
    }

}

//喜欢
function addLike() {
    //获取当前点击事件的id
    var id = event.target.id;
    //获取当前元素的下一个元素
    var text = document.getElementById(id).nextElementSibling;
    //获取元素的文本内容
    var oldLikeNumber = text.textContent;
    text.textContent = parseInt(oldLikeNumber) + 1;
    //截取id数字部分
    var likeId = id.substring(0, 36);
    $.ajax({
        type: 'GET',
        url: API['addLike'],
        data: {
            sid: likeId
        },
        success: function (data) {
        },
        error: function () {
            alert("点赞失败")
        }
    })
}

//格式化日期
function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('/');
}

function showUserInfo() {
    $('.head .avatar').attr('src', userInfo['avatar']);
    $('.head #avatar').val(userInfo['avatar']);
    $('.info-body #userName').val(userInfo['userName']);
    $('.info-body #nickName').val(userInfo['nickName'])
}

// 上传头像
function uploadAvatar(file) {
    var formdata = new FormData();
    formdata.append('file', file);
    $.ajax({
        url: API['uploadAvatar'],
        type: 'POST',
        data: formdata,
        // 告诉jQuery不要去处理发送的数据
        processData: false,
        // 告诉jQuery不要去设置Content-Type请求头
        contentType: false,
        success: function (data) {
            $('.head .avatar').attr('src', data['qiniuUrl'])
            $('.head #avatar').val(data['qiniuUrl'])
        }
    })
}

//封装获取扩展名的方法
function getExpandedName(fileName) {
    var index1 = fileName.lastIndexOf("."), index2 = fileName.length;
    return fileName.substring(index1 + 1, index2);
}

//校验扩展名
function checkType(type) {
    var regType = "png,gif,jpg";
    return regType.indexOf(type) > -1
}
