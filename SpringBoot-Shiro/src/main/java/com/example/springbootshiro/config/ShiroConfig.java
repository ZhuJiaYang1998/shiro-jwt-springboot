package com.example.springbootshiro.config;

import com.example.springbootshiro.realm.FirstRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 15:03
 */
@Configuration
public class ShiroConfig {
    //1.shirofilter 拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        HashMap<String, String> map = new HashMap<>();
        /**
         * authc 需要人认证才能访问的资源
         * anon 公共支援
         */
        map.put("/regist.jsp","anon");
        map.put("/user/regist","anon");
        map.put("/user/login","anon");
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //登录资源，公共的
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        return shiroFilterFactoryBean;
    }


    //2.安全管理器
    @Bean
    public DefaultWebSecurityManager securityManager(FirstRealm realm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(realm);
        return defaultSecurityManager;
    }

    //3.realm
    @Bean
    public FirstRealm realm(){
        /**
         * hash比较器
         */
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        /**
         * 迭代次数
         */
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        FirstRealm firstRealm = new FirstRealm();
        firstRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return firstRealm;
    }
}
