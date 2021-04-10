package com.lagou.points.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.points.mapper.PointsMapper;
import com.lagou.points.entity.Points;
import com.lagou.points.service.PointsService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分服务
 */
@Slf4j
@Service
public class PointsServiceImpl extends ServiceImpl<PointsMapper, Points> implements PointsService {
    @Autowired
    PointsMapper pointsMapper;

    /**
     * 会员增加积分
     *
     * @param username 用户名
     * @param points   增加的积分
     * @return 积分对象
     */
    public void increase(String username, Integer points) {
        QueryWrapper<Points> wrapper = new QueryWrapper<Points>();
        wrapper.lambda().eq(Points::getUsername, username);
        Points userPoints = this.getOne(wrapper);
        if (userPoints == null) {
            userPoints = new Points();
            userPoints.setUsername(username);
            //userPoints.setPoints(points); 不直接增加积分
            userPoints.setFrozenPoints(points);//设置冻结积分
            this.save(userPoints);
        } else {
            userPoints.setFrozenPoints(points);//设置冻结积分
            this.saveOrUpdate(userPoints);
        }
    }

    @Override
    public boolean increaseCommit(BusinessActionContext context) {
        //查询⽤户积分
        QueryWrapper<Points> wrapper = new QueryWrapper<Points>();

        //从上下文信息中获取用户信息
        wrapper.lambda().eq(Points::getUsername,
                context.getActionContext("username"));

        Points userPoints = this.getOne(wrapper);
        if (userPoints != null) {
            //增加⽤户积分
            userPoints.setPoints(userPoints.getPoints() +
                    userPoints.getFrozenPoints());

            //冻结积分清零，接口幂等性，这里必须设置为0,否则会引起错误
            userPoints.setFrozenPoints(0);
            this.saveOrUpdate(userPoints);
        }
        log.info("积分模块：--------->xid=" + context.getXid() + " 提交成功!");
        return true;
    }

    @Override
    public boolean increaseRollback(BusinessActionContext context) {
        //查询⽤户积分
        QueryWrapper<Points> wrapper = new QueryWrapper<Points>();
        wrapper.lambda().eq(Points::getUsername,
                context.getActionContext("username"));
        Points userPoints = this.getOne(wrapper);
        if (userPoints != null) {
            //冻结积分清零,回滚操作只需把冻结积分清零即可
            userPoints.setFrozenPoints(0);
            this.saveOrUpdate(userPoints);
        }
        log.info("积分模块：--------->xid=" + context.getXid() + " 回滚成功!");
        return true;
    }
}
