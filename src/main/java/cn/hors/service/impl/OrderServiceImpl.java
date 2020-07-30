package cn.hors.service.impl;

import cn.hors.bean.Order;
import cn.hors.bean.Timeline;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.OrderMapper;
import cn.hors.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Override
    public boolean insert(Order order) {
        return orderMapper.insert(order)>0;
    }

    @Override
    public boolean delByIds(Integer... ids) {
        return orderMapper.delByIds(ids)>0;
    }

    @Override
    public boolean update(Order order) {
        return orderMapper.update(order)>0;
    }

    @Override
    public List<Order> findAll(Order order) {
        return orderMapper.findAll(order);
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.findById(id);
    }
}
