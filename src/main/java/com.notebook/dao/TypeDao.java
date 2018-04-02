package com.notebook.dao;

import com.notebook.entity.BookNote;
import com.notebook.entity.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeDao {
    int insertType(Type type);

    int updateType(Type type);

    int deleteType(String tid);

    List<Type> getTypeByUid(String uid);

    Type getNoType(@Param("type") String type, @Param("uid") String uid);
}
