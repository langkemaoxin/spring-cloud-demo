package com.lagou.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.order.entity.Order;
import com.lagou.order.mapper.OrderMapper;
import com.lagou.order.service.OrderService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public void add(Order order) {
        order.setCreateTime(new Date());//设置订单创建时间
        order.setStatus(0);//try阶段-预检查
        this.save(order);//保存订单
    }

    @Override
    public boolean addCommit(BusinessActionContext context) {
        Order order =
                JSON.parseObject(context.getActionContext("order").toString(),
                        Order.class);
        order = this.getById(order.getId());
        if (order != null) {
            order.setStatus(1);//commit阶段-提交事务
            this.saveOrUpdate(order);//修改订单
        }
        log.info("订单模块：--------->xid=" + context.getXid() + " 提交成功!");
        return true;
    }

    @Override
    public boolean addRollBack(BusinessActionContext context) {
        Order order =
                JSON.parseObject(context.getActionContext("order").toString(),
                        Order.class);
        order = this.getById(order.getId());
        if (order != null) {
            this.removeById(order.getId());//删除订单
        }
        log.info("订单模块：--------->xid=" + context.getXid() + " 回滚成功!");
        return true;
    }
}
