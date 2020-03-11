package com.missz.herbalifegs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/webapp")
public class WebappController {

//    @GetMapping("/types")
//    public String type(){
//        return "web/types";
//    }
//
//    @GetMapping("/tags")
//    public String tags(){
//        return "web/tags";
//    }
//
//    @GetMapping("/archives")
//    public String archives(){
//        return "web/archives";
//    }
//
//    @GetMapping("/about")
//    public String about(){
//        return "web/about";
//    }
//
//    @GetMapping("/blog")
//    public String blog(){
//        return "web/blog";
//    }

    @GetMapping("/missz")
    public String missz(){
        return "missz";
    }


}
