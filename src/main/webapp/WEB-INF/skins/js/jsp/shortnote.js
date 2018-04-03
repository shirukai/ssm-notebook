var pageNum=1;
var pageSize=10;

loadNote(pageNum,pageSize);

//加载便签
function loadNote(start,length) {
    $.ajax({
        url: API['getPublicNote'],
        type: 'GET',
        data:{"pageNum":pageNum,"pageSize":pageSize},
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

//滚动条加载底部自动加载
$(function(){
    //鼠标滚动事件
    $(window).scroll(function(){
//下面这句主要是获取网页的总高度，主要是考虑兼容性所以把Ie支持的documentElement也写了，这个方法至少支持IE8
        var htmlHeight=document.body.scrollHeight||document.documentElement.scrollHeight;
        //clientHeight是网页在浏览器中的可视高度，
        var clientHeight=document.body.clientHeight||document.documentElement.clientHeight;
        //scrollTop是浏览器滚动条的top位置，
        var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
        //通过判断滚动条的top位置与可视网页之和与整个网页的高度是否相等来决定是否加载内容；
        if(scrollTop+clientHeight+100>=htmlHeight){
            pageNum++;
            // console.log(loadNote(pageNum,pageSize));
            loadNote(pageNum,pageSize);
        }
    })
})
