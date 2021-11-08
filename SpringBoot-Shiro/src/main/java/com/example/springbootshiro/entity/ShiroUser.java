package com.example.springbootshiro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.*;
/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 17:16
 */
@TableName("shiro_user")
@Data
@Accessors(chain = true)//链式调用
@AllArgsConstructor
@NoArgsConstructor
public class ShiroUser implements Serializable {
    @TableId(type = IdType.ASSIGN_ID )
    private String id;
    private String account;
    private String password;
    @TableField(exist = false)
    private List<ShiroRole> roles;
    public ShiroUser(String account, String password, String salt) {
        this.account = account;
        this.password = password;
        this.salt = salt;
    }
    private String salt;
}
