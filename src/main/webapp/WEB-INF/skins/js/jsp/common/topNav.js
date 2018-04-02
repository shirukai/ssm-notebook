var url = window.location.href;
var nav_links = $('.nav-item a');
$.each(nav_links,function (index,value) {
    if(value.href ===url){
        $(value).addClass('active').siblings('li').removeClass('active')
    }
})
//读取cookie登录信息
var userInfo = $.cookie('userInfo')
if (!userInfo || userInfo === null) {
    $('#noLogin').show().siblings('li').hide()
} else {
    userInfo = JSON.parse(userInfo)
    $('#alLogin').show().siblings('li').hide()
    $('#userName').text(userInfo['nickName'])
}

//退出
$('#out').bind('click', outLogin);

function outLogin() {
    $.removeCookie('accessToken', {path: '/'});
    $.removeCookie('userInfo', {path: '/'});
    window.location.reload()
}