var start=0;
var length=5;//
//动态添加文章列表
findBookList(start,length);
//动态添加用户排名
findUserTOP();
function  findUserTOP(){
    $.ajax({
        url: API['findUserTop'],
        type: 'GET',
        data: "",
        success: function (result) {
            console.log(result);
            if (result['state'] !== 0) {
                $.each(result['aaData'], function (index, info) {
                    console.log(info);
                    addUserTopToView(info)
                });
            } else {

            }
        }
    });
}
function addUserTopToView(info) {
    html = document.getElementById("user_top").innerHTML, reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'),
        source = html.replace(reg, function (node, key) {
            return {
                'uid': info['uid'],
                'numberBook': info['numberBook'],
                'nickName': info['nickName'],
                'rowNumber': info['rowNumber']
            }[key]
        });
    $("#userTop").append(source)
}
//获取文章列表信息
function  findBookList(
    start,length
){
    $.ajax({
        url: API['getBookList'],
        type: 'GET',
        data: {"start":start,"length":length},
        success: function (result) {
            console.log(result);
            if (result['state'] !== 0) {
                $.each(result['aaData'], function (index, info) {
                    info['createTime'] = getLocalTime(info['createTime']);
                    info['modifyTime'] = getLocalTime(info['modifyTime']);
                    info['nickName'] = info['nickName'];
                    addNoteToView(info)
                });
            } else {

            }
        }
    });
}
//更新页面
function addNoteToView(info) {
    var bid = info['bid'], likeId = bid + "like", viewId = bid + "view",
        stateId = bid + "state",bookContent_new=delHtmlTag(info['bookContent']),
        html = document.getElementById("showManagerItems").innerHTML, reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'),
        source = html.replace(reg, function (node, key) {
            return {
                'booknId': bid,
                'bookTitle':info['bookTitle'],
                'bookContent': bookContent_new,
                'createName': info['nickName'],
                'isPublic': info['isPublic'] === 1 ? "公开" : "不公开",
                'nickName': info['nickName'],
                'createTime': info['createTime'],
                'modifyTime':info['modifyTime'],
                'viewNumber': info['viewNumber'],
                'likeNumber': info['likeNumber'],
                'likeId': likeId,
                'viewId': viewId,
                'stateId':stateId,
                'type': info['type']
            }[key]
        });//通过json格式的数据来匹配模板数据
//将模板加载到html
    $("#managerlist").append(source)
}
function delHtmlTag(str){
    //console.log(str.replace(/<[^>]+>/g,""));
    str_new=str.replace(/<[^>]+>/g,"")//去掉所有的html标记
    return str_new.substring(0,100) ;//截取
}
//滚动条加载底部自动加载
$(function(){
    //鼠标滚动事件
    $(window).scroll(function(){
        console.log("滚动条到顶部的垂直高度：" +  $(window).scrollTop() );
        console.log("页面的文档高度：" +  $(document).height() );
        console.log("浏览器的高度：" +  $(window).height() );
//下面这句主要是获取网页的总高度，主要是考虑兼容性所以把Ie支持的documentElement也写了，这个方法至少支持IE8
        var htmlHeight=document.body.scrollHeight||document.documentElement.scrollHeight;
        //clientHeight是网页在浏览器中的可视高度，
        var clientHeight=document.body.clientHeight||document.documentElement.clientHeight;
        //scrollTop是浏览器滚动条的top位置，
        var scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
        //通过判断滚动条的top位置与可视网页之和与整个网页的高度是否相等来决定是否加载内容；
        if(scrollTop+clientHeight==htmlHeight){
             start+=length;
            findBookList(start,length);
            // 延时
             // setTimeout(function () {
             //
             // },1000)

        }
    })
})
