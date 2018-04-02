$('.input-group input').bind('blur', verification).bind('focus', initInput);
var result = true;
$('#login').click(function () {
    verificationAll()
    if (result) {
        $.ajax({
            url: API['verification'],
            type: 'POST',
            data: $('.form-login').serialize() + "&autoLogin=" + $('#autoLogin').is('checked'),
            success: function (data) {
                if (data['state'] === 1) {
                    window.location.href = context + "/notebook/index"
                } else {
                    $('.alert').addClass('show');
                    setTimeout(function (args) { $('.alert').removeClass('show') }, 2000)
                }
            }
        })
    }
})

// 输入校验
function verification() {
    result = true
    var val = $(this).val(), msg = "";
    //非空检验
    if (val === "" || val === null || val === undefined) {
        var pla = $(this).attr("placeholder");
        result = false
        msg = pla + "不能为空"
    } else {
        //长度校验
        if (val.length < 3 || val.length > 16) {
            result = false;
            msg = "长度为3-16个字符"
        } else {
            if ($(this).attr("id") === "userName") {
                var phone = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/.test(val)
                var email = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(val)
                if (!phone && !email) {
                    result = false
                    msg = "用户名为手机号/邮箱"
                }
            }

        }
    }
    if (result) {
        $(this).addClass("is-valid")
    } else {
        $(this).addClass("is-invalid").parent().removeClass("login-input")
        $(this).siblings()[1].innerHTML = msg
    }
}

//初始化input
function initInput() {
    $(this).removeClass('is-invalid is-valid').parent().addClass('login-input')
}

//verificationAll
function verificationAll() {
    var inputs = $('.form-login input')
    for (var i = 0; i < inputs.length; i++) {
        $(inputs[i]).blur()
    }
}