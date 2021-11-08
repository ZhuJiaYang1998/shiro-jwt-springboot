package com.example.springbootshiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 15:47
 */
@Controller
public class MyController {
@RequestMapping("/")
    public String index(){
    System.out.println();
    return "index";
}
@RequestMapping("/test")
@ResponseBody
    public String test(){
    System.out.println();
    return "succees";
    }
}
