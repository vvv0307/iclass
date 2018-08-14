package com.zht.iclass.service.impl;

import com.zht.iclass.dao.UserMapper;
import com.zht.iclass.model.User;
import com.zht.iclass.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public boolean addUser(User user) {
        if(userMapper.insertUser(user)>0){
            return true;
        }
        return false;
    }
}
