package com.zht.iclass.dao;

import com.zht.iclass.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user (username,password,phone,name,role) value(#{username}" +
            ",#{password},#{phone},#{name},#{role})")
    int insertUser(User user);

    @Update("update user set username=#{username},name=#{name},role=#{role} where phone=#{phone}")
    int updateUserByPhone(User user);

    @Update("update user set phone=#{phone},name=#{name},role=#{role} where username=#{username}")
    int updateUserByUsername(User user);

    @Select("select * from user where username=#{username}")
    User selectUserByUsername(String username);

    @Select("select * from user where phone=#{phone}")
    User selectUserByPhone(String phone);

    @Update("update user set password=#{password} where phone=#{phone}")
    int updatePasswordByPhone(@Param("password") String password,@Param("phone") String phone);


}
