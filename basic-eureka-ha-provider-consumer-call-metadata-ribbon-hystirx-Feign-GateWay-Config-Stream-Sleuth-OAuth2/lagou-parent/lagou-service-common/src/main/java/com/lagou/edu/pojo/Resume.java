package com.lagou.edu.pojo;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name="r_resume")
public class Resume {
    @Id
    private Long id; // 主键
    private String sex; // 性别
    private String birthday; // ⽣⽇
    private String workYear; // ⼯作年限
    private String phone; // ⼿机号
    private String email; // 邮箱
    private String status; // ⽬前状态
    private String resumeName; // 简历名称
    private String name; // 姓名
    private String createTime; // 创建时间
    private String headPic; // 头像
    private Integer isDel; //是否删除 默认值0-未删除 1-已删除
    private String updateTime; // 简历更新时间
    private Long userId; // ⽤户ID
    private Integer isDefault; // 是否为默认简历 0-默认 1-⾮默认
    private String highestEducation; // 最⾼学历
    private Integer deliverNearByConfirm; // 投递附件简历确认 0-需要确认 1-不需要确认
    private Integer refuseCount; // 简历被拒绝次数
    private Integer markCanInterviewCount; //被标记为可⾯试次数
    private Integer haveNoticeInterCount; //已通知⾯试次数
    private String oneWord; // ⼀句话介绍⾃⼰
    private String liveCity; // 居住城市
    private Integer resumeScore; // 简历得分
    private Integer userIdentity; // ⽤户身份1-学⽣ 2-⼯⼈
    private Integer isOpenResume; // ⼈才搜索-开放简历 0-关闭，1-打开，2-简历未达到投放标准被动关闭 3-从未设置过开放简历
}