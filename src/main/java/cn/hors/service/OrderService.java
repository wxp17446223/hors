package cn.hors.service;

import cn.hors.bean.Order;
import cn.hors.bean.Timeline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService{

    /**
     * 添加一个预约表信息
     * @param order 预约信息
     * @return
     */
    boolean insert(Order order);

    /**
     * 删除多个预约信息
     * @param ids 预约IDs
     * @return
     */
    boolean delByIds(@Param("ids") Integer... ids);

    /**
     * 预约排班信息
     * @param order 预约信息
     * @return
     */
    boolean update(Order order);

    /**
     * 动态查询所有预约信息
     * @param order 预约信息
     * @return
     */
    List<Order> findAll(Order order);

    /**
     * 通过排班ID查询预约信息
     * @param id 预约ID
     * @return
     */
    Order findById(Integer id);

    /**
     * 通过用户ID查询单一用户的预约信息
     * @param userId 用户ID
     * @return
     */
    Order findByUseId(Integer userId);
}
