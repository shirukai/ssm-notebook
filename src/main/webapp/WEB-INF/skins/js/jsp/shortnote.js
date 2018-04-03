loadNote();

//加载便签
function loadNote() {
    $.ajax({
        url: API['getPublicNote'],
        type: 'GET',
        data: "",
        success: function (result) {
            console.log(result);
            if (result['state'] !== 0) {
                $.each(result['data'], function (index, info) {
                    info['createTime'] = getLocalTime(info['modifyTime']);
                    info['nickName'] = info['nickName'];
                    addNoteToView(info)
                });
            } else {

            }
        }
    })
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
    $("#gallery-wrapper").append(source)
}
