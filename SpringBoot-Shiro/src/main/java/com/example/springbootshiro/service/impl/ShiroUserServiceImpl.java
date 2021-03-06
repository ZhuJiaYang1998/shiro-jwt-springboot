package com.example.springbootshiro.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootshiro.entity.ShiroPermission;
import com.example.springbootshiro.entity.ShiroUser;
import com.example.springbootshiro.mapper.ShiroUserMapper;
import com.example.springbootshiro.service.ShiroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 17:24
 */
@Service
public class ShiroUserServiceImpl extends ServiceImpl<ShiroUserMapper, ShiroUser>implements ShiroUserService {
    public  ShiroUser findRoleByAccount(String account){
        ShiroUser User = baseMapper.findRoleByAccount(account);
        return User;
    }

    @Override
    public List<ShiroPermission> findPermissionByRoleId(List<String> roleIds) {
        return baseMapper.findPermissionByRoleId(roleIds);
    }
}
