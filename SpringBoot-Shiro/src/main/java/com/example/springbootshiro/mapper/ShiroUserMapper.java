package com.example.springbootshiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootshiro.entity.ShiroPermission;
import com.example.springbootshiro.entity.ShiroUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 17:13
 */

public interface ShiroUserMapper extends BaseMapper<ShiroUser> {
    public  ShiroUser findRoleByAccount(String account);

    public List<ShiroPermission> findPermissionByRoleId(List<String> roleIds);
}
