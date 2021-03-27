package com.lagou.edu.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SentinelHandlersClass {

    // 整体要求和当时Hystrix一样，这里还需要在形参中添加BlockException参数，用于接收异常
    // 注意：方法是静态的
    public static Integer handleException(Long userId, BlockException blockException) {
        return -100;
    }

    public static Integer handleError(Long userId) {
        return -500;
    }

}
