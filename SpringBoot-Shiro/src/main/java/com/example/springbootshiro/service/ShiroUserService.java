package com.example.springbootshiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springbootshiro.entity.ShiroPermission;
import com.example.springbootshiro.entity.ShiroUser;

import java.util.List;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 17:23
 */
public interface ShiroUserService extends IService<ShiroUser> {
    public  ShiroUser findRoleByAccount(String account);

    List<ShiroPermission> findPermissionByRoleId(List<String> roleIds);
}
