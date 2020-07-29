package cn.hors.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
    * 管理员表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    /**
    * 管理员id
    */
    private Integer accountId;

    /**
    * 登录账号
    */
    private String account;

    /**
    * 登录密码
    */
    private String password;

    private Userinfo userinfo;
}