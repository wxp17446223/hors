package cn.hors.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private boolean locked;

    private boolean expired;

    private boolean enabled;


    private Integer roleId;
    private UserInfo userInfo;
}