package cn.hors.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 新闻信息表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    /**
    * 新闻id
    */
    private Integer newId;

    /**
    * 新闻标题
    */
    private String title;

    /**
    * 新闻内容
    */
    private String content;

    /**
    * 新闻日期
    */
    private Date newsDate;

    /**
    * 新闻来源
    */
    private String source;


    /**
     * 浏览量
     */
    private Integer count;
}