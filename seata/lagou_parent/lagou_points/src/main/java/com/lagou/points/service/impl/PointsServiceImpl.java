package com.lagou.points.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.points.mapper.PointsMapper;
import com.lagou.points.entity.Points;
import com.lagou.points.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分服务
 */
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
            userPoints.setPoints(points);
        } else {
            userPoints.setPoints(userPoints.getPoints() + points);
        }
        this.saveOrUpdate(userPoints);
    }
}
