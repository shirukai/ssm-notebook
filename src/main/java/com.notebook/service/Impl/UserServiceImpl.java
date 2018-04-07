package com.notebook.service.Impl;

import com.notebook.dao.UserDao;
import com.notebook.entity.User;
import com.notebook.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.notebook.dto.ResData.resData;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Map updateUser(User user, String oldPwd) {
        Map<String, Object> map = new HashMap<>();
        String msg = "更新成功！";
        int state = 1;
        //判断是否跟新密码
        if (oldPwd == null || oldPwd.equals("undefined") || oldPwd.equals("")) {
            //更新其他信息的操作
            int res = userDao.updateUserNoPwd(user);
            if (res == 0) {
                msg = "更新失败";
                state = 0;
            }
        } else {
            msg = "更新密码失败，请认真核对新旧密码";
            state = 0;
            User oldUser = userDao.verification(user.getUserName());
            if (oldUser.getUserPwd().equals(oldPwd) && user.getUserPwd() != null) {
                int res = userDao.updateUserNoPwd(user);
                if (res == 1) {
                    //执行更新密码的操作
                    res = userDao.updatePwd(user.getUid(), user.getUserPwd());
                }
                if (res == 1) {
                    state = 1;
                    msg = "更新成功！";
                }
            }
        }
        map.put("state", state);
        map.put("msg", msg);
        return map;
    }

    public int insertUser(User user) {
        return userDao.insertUser(user);
    }


    public Map verificationName(String userName) {
        User user = userDao.verification(userName);
        int state = 0;
        Object msg;
        if (user == null) {
            state = 1;
            msg = "用户名可用";
        } else {
            msg = "用户名已存在";
        }
        return resData(state, msg);
    }

    public Map verification(String userName, String password, Boolean autoLogin) {
        User user = userDao.verification(userName);
        int state = 0;
        Object msg = "用户名或密码错误";
        if (user != null) {
            if (user.getUserPwd().equals(password)) {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("userName", user.getUserName()).put("nickName", user.getNickName()).put("uid", user.getUid()).put("avatar", user.getAvatar());
                    msg = jo.toString();
                } catch (Exception e) {
                    e.getMessage();
                }

                state = 1;
            }
        }
        return resData(state, msg);
    }

    public User queryByUid(String uid) {
        return userDao.queryByUid(uid);
    }
}
