package cn.hors.service;

import cn.hors.bean.FeedBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackService {


    /**
     * 查询相应用户的反馈信息
     * @param userId
     * @return
     */
    List<FeedBack> findByUsId(Integer userId);


    /**
     * 查询所有反馈信息
     * @param feedBack 反馈bean
     * @return
     */
    List<FeedBack> findAll(FeedBack feedBack);

    /**
     * 新增反馈
     * @param feedBack
     * @return
     */
    boolean insert(FeedBack feedBack);

    /**
     * 修改反馈
     * @param feedBack
     * @return
     */
    boolean update(FeedBack feedBack);

    /**
     * 删除反馈
     * @param ids
     * @return
     */
    boolean deleteByIds(@Param("ids") Integer... ids);

}
