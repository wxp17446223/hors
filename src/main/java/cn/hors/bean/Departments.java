package cn.hors.bean;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date date;

    /**
    * 父科室ID默认-1 为最高级科室
    */
    private Integer dId;

    /**
     *子科室列表
     */
    private List<Departments> children;

    /**
     *父科室
     */
    private Departments parent;

    private List<Doctor> doctors;
}