package cn.hors.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用户表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userinfo {
    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 关联账号ID
    */
    private Integer accountId;

    /**
    * 性别
    */
    private Integer sex;

    /**
    * 出生日期
    */
    private Date birth;

    /**
    * 用户照片
    */
    private String picture;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 家庭住址
    */
    private String address;

    /**
    * 注册时间
    */
    private Date registerTime;
}