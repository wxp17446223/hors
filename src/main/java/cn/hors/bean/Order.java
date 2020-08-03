package cn.hors.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 预约表:记录用户预约的情况
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
    * 预约id
    */
    private Integer orderId;

    /**
    * 预约用户id
    */
    private Integer userId;

    /**
    * 预约了的时段id
    */
    private Integer tId;

    /**
    * 下单时间
    */
    private Date addtime;

    /**
    * 联系电话
    */
    private String userPhone;

    /**
    * 处理状态：0处理中 1预约成功
    */
    private Integer status;

    /**
    * 医生回复
    */
    private String reply;

    /**
    * 预约费用
    */
    private String cost;

    /**
    * 预约的医生ID
    */
    private Integer doctorId;

    private Userinfo orderUser;

    private Timeline timeline;

    private Doctor doctor;

}