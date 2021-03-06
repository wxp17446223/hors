package cn.hors.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
    * 医生表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    /**
    * 医生ID
    */
    private Integer doctorId;

    /**
    * 医生工号
    */
    private Integer jobNumber;

    /**
    * 所在门诊ID
    */
    private Integer departId;

    /**
    * 医生姓名
    */
    private String name;

    /**
    * 性别
    */
    private Integer sex;
    /**
     * 枚举性别下标 存进数据库的值
     */
    private Integer sexIndex;

    /**
    * 医生照片
    */
    private String picture;

    /**
    * 出生日期
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String birth;

    /**
    * 医生职位
    */
    private String position;

    /**
    * 工作经验
    */
    private String workExperience;

    /**
    * 联系方式
    */
    private String phone;

    /**
    * 擅长
    */
    private String adept;

    /**
    * 医生介绍
    */
    private String introduce;
    /**
     * 医生的排班信息
     */
    private List<Timeline> timelineList;
    /**
     * 医生所在的科室信息
     */
    private Departments department;

}