package cn.hors.service;

import cn.hors.bean.Timeline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimelineService{

    /**
     * 添加一个排班信息
     * @param timeline 排班信息
     * @return
     */
    boolean insert(Timeline timeline);

    /**
     * 删除多个排班信息
     * @param ids 排班IDs
     * @return
     */
    boolean delByIds(@Param("ids") Integer... ids);

    /**
     * 修改排班信息
     * @param timeline 排班信息
     * @return
     */
    boolean update(Timeline timeline);

    /**
     * 动态查询所有排班信息
     * @param timeline 排班信息
     * @return
     */
    List<Timeline> findAll(Timeline timeline);

    /**
     * 通过ID查询排班信息
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

    /**
     * 通过医生Id 和日期查到该医生的该日期的排班信息
     * @param doctorId
     * @return
     */
    List<Timeline> findByDoctorIdAndDate(@Param("doctorId") Integer doctorId,@Param("date") String date);

    /**
     * 预约成功后 调用此方法 使预约数量-1
     * @param tId
     * @return
     */
    boolean updateQuota(Integer tId);
}
