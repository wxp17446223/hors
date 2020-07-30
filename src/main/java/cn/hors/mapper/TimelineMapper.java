package cn.hors.mapper;

import cn.hors.bean.Timeline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimelineMapper {
    /**
     * 添加一个排班信息
     * @param timeline 排班信息
     * @return
     */
    int insert(Timeline timeline);

    /**
     * 删除多个排班信息
     * @param ids 排班IDs
     * @return
     */
    int delByIds(@Param("ids") Integer... ids);

    /**
     * 修改排班信息
     * @param timeline 排班信息
     * @return
     */
    int update(Timeline timeline);

    /**
     * 动态查询所有排班信息
     * @param timeline 排班信息
     * @return
     */
    List<Timeline> findAll(Timeline timeline);

    /**
     * 通过排班ID查询排班信息
     * @param id 排班ID
     * @return
     */
    Timeline findById(Integer id);

    /**
     * 通过医生Id 查到该医生的所有排班信息
     * @param doctorId
     * @return
     */
    List<Timeline> findByDoctorId(Integer doctorId);
}