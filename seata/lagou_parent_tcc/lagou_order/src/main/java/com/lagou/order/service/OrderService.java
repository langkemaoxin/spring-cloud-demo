package com.lagou.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.order.entity.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 接口被Seata管理，根据事务状态完成事务的提交或者回滚
 */
@LocalTCC
public interface OrderService extends IService<Order> {

    @TwoPhaseBusinessAction(name = "addTcc",
    commitMethod = "addCommit",
    rollbackMethod = "addRollBack")
    void add(@BusinessActionContextParameter(paramName = "order") Order order);

    boolean addCommit(BusinessActionContext context);

    boolean addRollBack(BusinessActionContext context);
}
