package com.example.springbootshiro.controller;

import com.example.springbootshiro.entity.ShiroUser;
import com.example.springbootshiro.util.ContextUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ShiroUserControllerTest {
    @Autowired
    ShiroUserController shiroUserController;
    @Test
    public void test(){
        ApplicationContext context = ContextUtil.getContext();
        System.out.println(context.getBean("shiroUserMapper"));
    }
}