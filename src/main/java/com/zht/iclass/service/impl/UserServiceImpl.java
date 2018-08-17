package com.zht.iclass.service.impl;

import com.zht.iclass.dao.UserMapper;
import com.zht.iclass.model.User;
import com.zht.iclass.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Resource
    UserMapper userMapper;
    @Override
    public boolean addUser(User user) {
        if(userMapper.insertUser(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserByPhone(User user) {
        if(userMapper.updateUserByPhone(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserByUsername(User user) {
        if(userMapper.updateUserByUsername(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public User selectUserByUsername(String username) {
        User user = null;
        try {
            user = userMapper.selectUserByUsername(username);
        }catch (Exception e){
            logger.error("get user error",e);
        }
        return user;
    }

    @Override
    public User selectUserByPhone(String phone) {
        User user = null;
        try{
            user = userMapper.selectUserByPhone(phone);
        }catch (Exception e){
            logger.error("get user error",e);
        }
        return user;
    }

    @Override
    public boolean updatePasswordByPhone(String password, String phone) {
        if(userMapper.updatePasswordByPhone(password,phone)>0){
            return  true;
        }
        return false;
    }
}
