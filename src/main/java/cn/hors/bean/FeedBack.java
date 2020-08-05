package cn.hors.bean;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 反馈留言表:对医生的评论反馈  对医院的评论反馈 投诉
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack {
    /**
    * 反馈id
    */
    private Integer backId;

    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 医生ID
    */
    private Integer doctorId;

    /**
    * 反馈内容
    */
    private String content;

    /**
    * 反馈时间
    */
    private String backTime;

    /**
    * 管理回复
    */
    private String reply;

    /**
    * 回复时间
    */
    private String replyTime;
}