$('.avatar').bind('click', function () {
    $('#avatarFile').click()
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
// 输入框检验
$('.input-register input').bind('blur', verification).bind('focus', initInput);
//注册
var result = true;
$('#register').click(function () {
    verificationAll()
    if(result){
        $.ajax({
            url: API['register'],
            type: 'POST',
            data: $('.form-register').serialize(),
            success: function (data) {
                if(data['state']===1){
                    window.location.href = context+"/notebook/index"
                }
            }
        })
    }
})
// 上传头像
function uploadAvatar(file) {
    const formdata = new FormData();
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
            $('.avatar').attr('src', data['qiniuUrl'])
            $('#avatar').val(data['qiniuUrl'])
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


// 输入校验
function verification() {
    var val = $(this).val(), msg = "";
    result = true;
    //非空检验
    if (val === "" || val === null || val === undefined) {
        var pla = $(this).attr("placeholder")
        result = false;
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
                    result = false;
                    msg = "用户名为手机号/邮箱"
                } else {
                    //校验用户名是否存在
                    $.ajax({
                        url: API['checkUserName'],
                        type: 'POST',
                        data: {"userName": val},
                        async:false,
                        success: function (data) {
                            result = (data['state'] === 1);
                            msg = data['data']
                        }
                    })
                }
            } else if ($(this).attr("id") === "password") {
                var reg = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/.test(val)
                if (!reg) {
                    result = false;
                    msg = "必须包含大小写字母、数字、特殊字符"
                }

            } else if ($(this).attr("id") === "rePassword") {
                if ($('#password').val() !== val) {
                    result = false;
                    msg = "两次密码不一致"
                }
            }

        }
    }
    if (result) {
        $(this).addClass("is-valid")
    } else {
        $(this).addClass("is-invalid").parent().removeClass("input-register")
        $(this).siblings()[1].innerHTML = msg
    }
}


//verificationAll
function verificationAll() {
    var inputs = $('.form-register input')
    for (var i = 0; i < inputs.length; i++) {
        $(inputs[i]).blur()
    }
}

//初始化input
function initInput() {
    $(this).removeClass('is-invalid is-valid').parent().addClass('input-register')
}

