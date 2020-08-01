package cn.hors.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
    * 账号表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    /**
    * 账号id
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