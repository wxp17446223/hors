package cn.hors.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {


    /**
     * 反馈ID
     */
    private Integer backId;

    /**
     * 患者ID
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
     * 回复内容
     */
    private String reply;

    /**
     * 回复时间
     */
    private String replyTime;

}
