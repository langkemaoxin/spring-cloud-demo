package com.lagou.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.order.entity.Order;

public interface OrderService extends IService<Order> {
    void add(Order order);
}
