package com.zht.iclass.dao;

import com.zht.iclass.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user (username,password,phone,name,role) value(#{username}" +
            ",#{password},#{name},#{phone},#{role})")
    int insertUser(User user);
}
