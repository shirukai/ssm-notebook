package com.notebook.web;

import com.notebook.entity.User;
import com.notebook.service.UserService;
import com.notebook.utils.MD5;
import com.notebook.utils.Qiniu;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Map;

import static com.notebook.dto.ResData.resData;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 注册用户
     *
     * @param user user
     * @return msg
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map insertUser(User user, HttpServletResponse response) {
        Object msg = null;
        int state = 0;
        try {
            state = userService.insertUser(user);
            if (state == 1) {
                //存入cookie
                Cookie cookie = new Cookie("accessToken", MD5.get(user.getUserName()));
                cookie.setPath("/");
                cookie.setMaxAge(-1);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", user.getUserName())
                        .put("uid", user.getUid())
                        .put("nickName", user.getNickName()).put("avatar", user.getAvatar());
                String userInfo = jsonObject.toString();
                Cookie cookieUserInfo = new Cookie("userInfo", URLEncoder.encode(userInfo, "UTF-8"));
                cookieUserInfo.setPath("/");
                cookieUserInfo.setMaxAge(-1);
                response.addCookie(cookie);
                response.addCookie(cookieUserInfo);

            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        return resData(state, msg);
    }

    @RequestMapping(value = "/getUserInfo")
    public Map getUserInfo(
            @RequestParam(value = "uid", required = false) String uid
    ) {
        User user = userService.queryByUid(uid);
        int state = user == null ? 0 : 1;
        return resData(state, user);
    }

    @RequestMapping(value = "/upload/avatar")
    public Map uploadAvatar(MultipartFile file) {
        return Qiniu.upload(file);
    }

    @RequestMapping(value = "/check/username", method = RequestMethod.POST)
    public Map verificationName(@RequestParam String userName) {
        return userService.verificationName(userName);
    }

    @RequestMapping(value = "/verification", method = RequestMethod.POST)
    public Map verification(@RequestParam("userName") String userName,
                            @RequestParam("userPwd") String password,
                            @RequestParam("autoLogin") Boolean autoLogin,
                            HttpServletResponse response) throws Exception {
        Map result = userService.verification(userName, password, autoLogin);
        if (result.get("state") == (Object) 1) {
            //accessToken存入cookie
            Cookie cookie = new Cookie("accessToken", MD5.get(userName));
            //userInfo 存入cookie
            Cookie cookieUserInfo = new Cookie("userInfo", URLEncoder.encode(String.valueOf(result.get("data")), "UTF-8"));
            cookie.setPath("/");
            cookieUserInfo.setPath("/");
            if (autoLogin) {
                //七天免登陆
                cookie.setMaxAge(60 * 60 * 24 * 7);
                cookieUserInfo.setMaxAge(60 * 60 * 24 * 7);
            } else {
                //关闭浏览器失效
                cookie.setMaxAge(-1);
                cookieUserInfo.setMaxAge(-1);
            }
            response.addCookie(cookie);
            response.addCookie(cookieUserInfo);
        }
        return result;
    }

    @RequestMapping(value = "updateUserInfo")
    public Map updateUserInfo(
            User user,
            @RequestParam("oldUserPwd") String oldUserPwd
    ) {
        Map map = userService.updateUser(user, oldUserPwd);
        return resData((Integer) map.get("state"), map.get("msg"));
    }
}
