package com.example.springbootshiro.controller;

import com.example.springbootshiro.entity.ShiroUser;
import com.example.springbootshiro.service.ShiroUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 16:01
 */
@Controller
@RequestMapping("user")
@Slf4j
public class ShiroUserController {
    @Autowired
    ShiroUserService shiroUserService;
    @RequestMapping("/regist")
    @ResponseBody
    public Boolean regist(@RequestParam ("username")String account,@RequestParam("password") String pass){
        String salt = "xP*IODx";
        /**
         * param1：密码 param2:盐 param3:迭代次数
         */
        Md5Hash md5Hash = new Md5Hash(pass,salt,1024);
        String CompletePass = md5Hash.toHex();
        ShiroUser shiroUser = new ShiroUser(account, CompletePass, salt);

        return shiroUserService.save(shiroUser);
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @PostMapping("/login")
    public  String login(@RequestParam String username,@RequestParam String password){
        System.out.println("=================");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
        } catch (UnknownAccountException e) {
            log.info("unknownAccount");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            log.info("CredentialsError");
            e.printStackTrace();
        }
     return "redirect:/index.jsp";
    }
}
