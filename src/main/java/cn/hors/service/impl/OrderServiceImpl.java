package cn.hors.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.hors.mapper.OrderMapper;
import cn.hors.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

}
