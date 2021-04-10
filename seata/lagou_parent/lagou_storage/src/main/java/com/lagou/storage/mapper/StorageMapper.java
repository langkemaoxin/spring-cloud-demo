package com.lagou.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.storage.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageMapper extends BaseMapper<Storage> {
}
