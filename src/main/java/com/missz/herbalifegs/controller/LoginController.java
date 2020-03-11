package com.missz.herbalifegs.controller;

import com.missz.herbalifegs.entity.User;
import com.missz.herbalifegs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    //    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        User user = userService.checkUser(username, password);
//        if(!StringUtils.isEmpty(username) && "aa".equals(password)){
        if(user != null){
            //登录成功,后防止表单重复提交，可以重定向到主页
            user.setPassword(null);
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            //登录失败
            map.put("msg","用户名密码错误");
            return  "login";
        }

    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute("user");
//        return "login";
//    }

}
