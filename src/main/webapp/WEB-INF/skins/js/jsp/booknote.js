findBookList();
//获取列表信息
function  findBookList() {
    $.ajax({
        url: API['getBookList'],
        type: 'GET',
        data: "",
        success: function (result) {
            console.log(result);
            if (result['state'] !== 0) {
                $.each(result['aaData'], function (index, info) {
                    info['createTime'] = getLocalTime(info['createTime']);
                    info['modifyTime'] = getLocalTime(info['modifyTime']);
                    info['nickName'] = info['nickName'];
                    console.log(info);
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
    return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}