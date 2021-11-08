package com.example.springbootshiro.config;

import com.example.springbootshiro.cache.RedisCacheManager;
import com.example.springbootshiro.filter.MyFilter;
import com.example.springbootshiro.realm.FirstRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 15:03
 */
@Configuration
public class ShiroConfig {

    /*
    自定义Filter ,交给spring管理出现UnavailableSecurityManagerException，不能出现@Bean @compont
     */
    public Filter getfilter(){
        return  new MyFilter();
    }
    //1.shirofilter 拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
         * 设置自定义过滤器
         */
        Map<String, Filter> myFilter = shiroFilterFactoryBean.getFilters();
        myFilter.put("MyFilter",getfilter());
        shiroFilterFactoryBean.setFilters(myFilter);

        HashMap<String, String> map = new LinkedHashMap<>();
        /**
         * authc 需要人认证才能访问的资源
         * anon 公共支援
         */
        map.put("/regist.jsp","anon");
        map.put("/user/regist","anon");
        map.put("/user/login","anon");
        map.put("/user/getImge","anon");
        map.put("/test","MyFilter[admin,user]");
        map.put("/**","authc");
        /*
         * 设置拦截链
         */
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
        /**
         * 开启缓存管理
         */
        firstRealm.setCacheManager(new EhCacheManager());//添加缓存管理器
        firstRealm.setCachingEnabled(true);//开启全局缓存
        firstRealm.setAuthenticationCachingEnabled(true);//开启认证缓存
        firstRealm.setAuthenticationCacheName("authenticationCache");//给认证缓存设定name
        firstRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
        firstRealm.setAuthorizationCacheName("authorizationCache");//给授权缓存设定name
        return firstRealm;
    }

}
