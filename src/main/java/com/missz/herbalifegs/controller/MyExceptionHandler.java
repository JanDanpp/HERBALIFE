package com.missz.herbalifegs.controller;

import com.missz.herbalifegs.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {


    //1。缺点 浏览器和客户端放回的都是固定的json数据，不会自适应
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String,Object> handleException(Exception e){
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexist");
//        map.put("message",e.getMessage());
//        return  map;
//    }


//    2.缺点未携带错误的原始数据
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
        /**
         6 * Integer statusCode = (Integer) request
         7 .getAttribute("javax.servlet.error.status_code");
         8 */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());

        request.setAttribute("ext",map);
        //转发到/error
        return  "forward:/error";
    }

}
