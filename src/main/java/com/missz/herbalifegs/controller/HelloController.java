package com.missz.herbalifegs.controller;

import com.missz.herbalifegs.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/c")
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("helloaa")
    public String helloss(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExistException();
        }
        return "Helloaaaaa";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好<h1>");
        map.put("users", Arrays.asList("zhangshang","wangwu","lisi"));
        return "success";
    }

    @RequestMapping("/test")
    public String test(Map<String,Object> map){
        map.put("hello","<h1>你好<h1>");
        map.put("users", Arrays.asList("zhangshang","wangwu","lisi"));
        return "test";
    }

    @ResponseBody
    @GetMapping("/map")
    public Map<String,Object> map(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from department");
        System.out.println(list+"----------");
        return list.get(0);
    }

}





