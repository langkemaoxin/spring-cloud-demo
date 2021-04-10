package com.lagou.points.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.points.entity.Points;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface PointsService extends IService<Points> {

    @TwoPhaseBusinessAction(name = "increaseTcc", commitMethod =
            "increaseCommit"
            , rollbackMethod = "increaseRollback")
    void increase(@BusinessActionContextParameter(paramName = "username") String username,
                  @BusinessActionContextParameter(paramName = "points") Integer points);

    boolean increaseCommit(BusinessActionContext context);
    boolean increaseRollback(BusinessActionContext context);
}
