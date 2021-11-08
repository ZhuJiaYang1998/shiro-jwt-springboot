package com.example.springbootshiro.realm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springbootshiro.entity.ShiroRole;
import com.example.springbootshiro.entity.ShiroUser;
import com.example.springbootshiro.service.ShiroUserService;
import com.example.springbootshiro.util.MyByteSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

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
//        if (StringUtils.equals(primaryPrincipal,"admin")){
//            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            simpleAuthorizationInfo.addRole("admin");
//            simpleAuthorizationInfo.addRole("user");
//            simpleAuthorizationInfo.addStringPermission("user:add:*");
//            simpleAuthorizationInfo.addStringPermission("user:select:*");
//            return simpleAuthorizationInfo;
//        }
        //数据库中查用户的role
        ShiroUser User = shiroUserService.findRoleByAccount(primaryPrincipal);
        List<ShiroRole> roles = User.getRoles();
        if (roles!=null){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            List<String> rolesNames = roles.stream().map(shiroRole -> shiroRole.getName()).collect(Collectors.toList());
            rolesNames.stream().forEach(rolesName->simpleAuthorizationInfo.addRole(rolesName));
//            List<String> roleIds = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
//            List<ShiroPermission> permissions = shiroUserService.findPermissionByRoleId(roleIds);
//            simpleAuthorizationInfo.addStringPermissions(permissions.stream().map(permission->permission.getName()).collect(Collectors.toList()));
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
           return new SimpleAuthenticationInfo(one.getAccount(),one.getPassword(),
                   MyByteSource.Util.bytes(one.getSalt()),
                   this.getName());
        }
        return null;
    }
}
