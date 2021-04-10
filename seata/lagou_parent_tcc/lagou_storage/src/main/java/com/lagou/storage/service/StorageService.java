package com.lagou.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.storage.entity.Storage;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * 仓库服务
 */
@LocalTCC
public interface StorageService extends IService<Storage> {

    @TwoPhaseBusinessAction(
            name = "decreaseTcc",
            commitMethod = "decreaseCommit",
            rollbackMethod = "decreaseRollback")
    public void decrease(@BusinessActionContextParameter(paramName = "goodsId") Integer goodsId,
                         @BusinessActionContextParameter(paramName = "quantity") Integer quantity);

    public boolean decreaseCommit(BusinessActionContext context);

    public boolean decreaseRollback(BusinessActionContext context);
}
