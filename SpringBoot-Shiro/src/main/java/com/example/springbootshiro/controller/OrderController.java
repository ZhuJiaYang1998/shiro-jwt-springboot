package com.example.springbootshiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.DocFlavor;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 17 10:56
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/save")
    @RequiresRoles(value = {"user","admin"})// 同时拥有两个role才可以访问
    @RequiresPermissions(value = {"user:select:01"}) //拥有这个权限才可以访问
    public String Save (){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
        log.info("访问成功");
        }else {
            log.info("无权访问");
        }
        return "index";
    }
}
