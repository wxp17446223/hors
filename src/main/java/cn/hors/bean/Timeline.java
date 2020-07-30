package cn.hors.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 医生排班表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timeline {
    /**
    * 排班ID
    */
    private Integer tId;

    /**
    * 医生ID
    */
    private Integer doctorId;

    /**
    * 日期
    */
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    private Date date;

    /**
    * 允许预约的人数
    */
    private Integer quota;

    /**
    * 时段开始
    */
    private String startTime;

    /**
    * 时段结束
    */
    private String endTime;

    /**
    * 该时段是否允许预约
        0 否 1允许
    */
    private Integer status;
}