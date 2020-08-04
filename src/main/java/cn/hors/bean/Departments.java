package cn.hors.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

/**
    * 科室表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Departments {
    private Integer departId;

    /**
    * 科室名字
    */
    private String departName;

    /**
    * 科室介绍
    */
    private String introduce;


    /**
    * 成立日期
    */
    private String  date;

    /**
    * 父科室ID默认-1 为最高级科室
    */
    private Integer dId;

    /**
     * 科室电话
     */
    private String phone;

    /**
     *子科室列表
     */
    private List<Departments> children;

    /**
     *父科室
     */
    private Departments parent;

    /**
     * 科室下的所有医生
     */
    private List<Doctor> doctors;
}