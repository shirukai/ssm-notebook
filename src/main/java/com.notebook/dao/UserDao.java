package com.notebook.dao;

import com.notebook.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 新增用户信息
     *
     * @param user 用户实体类
     * @return int
     */
    int insertUser(User user);

    User verification(String userName);

    User queryByUid(String uid);

    int updateUserNoPwd(User user);

    int updatePwd(@Param("uid") String uid, @Param("newPwd") String newPwd);

}
