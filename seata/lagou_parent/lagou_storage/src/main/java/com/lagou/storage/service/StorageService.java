package com.lagou.storage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lagou.storage.entity.Storage;

/**
 * 仓库服务
 */
public interface StorageService extends IService<Storage> {

    public void decrease(Integer goodsCode, Integer quantity);
}
