package com.example.springbootshiro.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.springbootshiro.entity.ShiroUser;
import com.example.springbootshiro.service.ShiroUserService;
import com.example.springbootshiro.util.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public  String login(@RequestParam String username, @RequestParam String password,
                         @RequestParam String code, HttpSession session){
        System.out.println("=================");
        try {
            String DATECODE = (String) session.getAttribute("VALIDATECODE");
            if (StringUtils.equalsIgnoreCase(code,DATECODE)){
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(username,password));
                return "redirect:/index.jsp";
            }else throw new RuntimeException("CODE ERROR");
        } catch (UnknownAccountException e) {
            log.info("unknownAccount");
            e.printStackTrace();
        }catch (IncorrectCredentialsException e){
            log.info("CredentialsError");
            e.printStackTrace();
        }catch (RuntimeException E){

        }
        return "redirect:/index.jsp";
    }
    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getImge")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil.getRandcode(request, response);//输出验证码图片方法
            System.out.println(request.getSession().getServletContext().getRealPath("com/example/springbootshiro")+"===============");
        } catch (Exception e) {
            log.error("获取验证码失败>>>>   ", e);
        }
    }

}
