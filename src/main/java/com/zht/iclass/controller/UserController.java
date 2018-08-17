package com.zht.iclass.controller;

import com.zht.iclass.model.User;
import com.zht.iclass.service.UserService;
import com.zht.iclass.util.Result;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/iclass")
public class UserController {
    @Resource
    private UserService userService;

    //register by username
    @RequestMapping(value = "/registerbyusername",method = RequestMethod.POST)
    public Result registerByUsername(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password){
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
    //check username
    @RequestMapping(value = "/checkusername/{username}",method = RequestMethod.GET)
    public Result checkUsername(@PathVariable String username){
        Result result = new Result();
        User user = userService.selectUserByUsername(username);
        if(user==null){
            result.setMsg("username not exist");
        }else{
            result.setMsg("username exist");
            result.setRes(user);
        }
        return result;
    }
    //check phone
    @RequestMapping(value = "/checkphone/{phone}",method = RequestMethod.GET)
    public Result checkPhone(@PathVariable String phone){
        Result result = new Result();
        User user = userService.selectUserByPhone(phone);
        if(user==null){
            result.setMsg("user not exist");
        }else{
            result.setMsg("user exist");
        }
        return result;
    }
    //register by phone
    @RequestMapping(value = "/registerbyphone",method = RequestMethod.POST)
    public Result registerByPhone(@RequestParam(value = "phone") String phone,
                                  @RequestParam(value = "password") String password){
        Result result = new Result();
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        if(userService.addUser(user)){
            result.setMsg("add user ok");
        }else{
            result.setMsg("add user error");
        }
        return result;
    }
    //complete information
    @RequestMapping(value = "/complete",method = RequestMethod.POST)
    public Result completeInformation(@RequestParam(value = "username") String username,
                                      @RequestParam(value = "name") String name,
                                      @RequestParam(value = "phone") String phone,
                                      @RequestParam(value = "role") Integer role,
                                      @RequestParam(value = "by") String by){
        Result result = new Result();
        User user = new User();
        if(by.trim().equals("phone")) {
            user.setUsername(username);
            user.setName(name);
            user.setPhone(phone);
            user.setRole(role);
            if(userService.updateUserByPhone(user)){
                result.setMsg("update user by phone ok");
            }else{
                result.setMsg("update user by phone error");
            }
        }else if(by.trim().equals("username")){
            user.setUsername(username);
            user.setName(name);
            user.setPhone(phone);
            user.setRole(role);
            if(userService.updateUserByUsername(user)){
                result.setMsg("update by username ok");
            }else{
                result.setMsg("update by username error");
            }
        }else{
            result.setMsg("request parameter error");
        }
        return result;
    }
    //update information
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Result updateUser(@RequestParam(value = "username") String username,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "phone") String phone,
                             @RequestParam(value = "role") Integer role){
        Result result = new Result();
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setRole(role);
        user.setName(name);
        if(userService.updateUserByPhone(user)){
            result.setMsg("update user ok");
        }else{
            result.setMsg("update user error");
        }
        return result;
    }
    //update password by phone
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public Result updatePassword(@RequestParam(value = "phone") String phone,
                                 @RequestParam(value = "password") String password){
        Result result = new Result();
        if(userService.updatePasswordByPhone(password,phone)){
            result.setMsg("update password ok");
        }else {
            result.setMsg("update password error");
        }
        return result;
    }
    //login
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password){
        Result result = new Result();
        User user = userService.selectUserByUsername(username);
        if(user!=null){
            String ps = user.getPassword();
            if(ps.equals(password)){
                result.setMsg("login ok");
                //save cookie

                Cookie cookie = new Cookie("iclassuser",username);
                cookie.setMaxAge(12*3600);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                response.addCookie(cookie);
                /*Cookie[] cookies = request.getCookies();
                for(Cookie c:cookies){
                    System.out.println(c.getName() + c.getValue());
                }*/
            }else{
                result.setMsg("password error");
            }
        }else{
            result.setMsg("username error");
        }
        return result;
    }
}
