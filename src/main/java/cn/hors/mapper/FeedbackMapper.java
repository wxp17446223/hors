package cn.hors.mapper;

import cn.hors.bean.FeedBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackMapper {

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
    int insert(FeedBack feedBack);

    /**
     * 修改反馈
     * @param feedBack
     * @return
     */
    int update(FeedBack feedBack);

    /**
     * 删除反馈
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") Integer... ids);
}
