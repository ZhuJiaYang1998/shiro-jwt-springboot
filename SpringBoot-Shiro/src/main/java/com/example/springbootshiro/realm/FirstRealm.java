package com.example.springbootshiro.realm;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springbootshiro.entity.ShiroUser;
import com.example.springbootshiro.service.ShiroUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 15:11
 */
@Slf4j
public class FirstRealm extends AuthorizingRealm {
    @Autowired
    ShiroUserService shiroUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /**
         * subject.hasrole等方法触发授权
         */
        log.info("=================授权");
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        if (StringUtils.equals(primaryPrincipal,"admin")){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("admin");
            simpleAuthorizationInfo.addRole("user");
            simpleAuthorizationInfo.addStringPermission("user:add:*");
            simpleAuthorizationInfo.addStringPermission("user:select:*");
            return simpleAuthorizationInfo;
        }
        /**
         * 如果没有权限
         */
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("=======================进入认证");
//        Object principal = authenticationToken.getPrincipal();
        String principal = (String) authenticationToken.getPrincipal();
        ShiroUser one = shiroUserService.getOne(new LambdaQueryWrapper<ShiroUser>().eq(ShiroUser::getAccount, principal));
        if (!ObjectUtils.isEmpty(one)){
            /**
             * 可以加一些判断lock操作
             */
           return new SimpleAuthenticationInfo(one.getAccount(),one.getPassword(), ByteSource.Util.bytes(one.getSalt()),this.getName());
        }
        return null;
    }
}
