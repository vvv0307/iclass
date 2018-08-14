package com.zht.iclass.controller;

import com.zht.iclass.model.User;
import com.zht.iclass.service.UserService;
import com.zht.iclass.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/iclass")
public class UserController {
    @Resource
    private UserService userService;

    //register by username
    @RequestMapping(value = "/registerbyusername",method = RequestMethod.POST)
    public Result registerByUsername(@RequestParam String username,
                                     @RequestParam String password){
        Result result = new Result();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userService.addUser(user)){
            result.setMsg("add user ok");
        }else{
            result.setMsg("add user error");
        }
        return result;
    }
}
