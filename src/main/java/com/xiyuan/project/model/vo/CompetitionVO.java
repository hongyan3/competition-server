package com.xiyuan.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 竞赛
 * @TableName competition
 */
@Data
public class CompetitionVO implements Serializable {
    /**
     * 竞赛ID
     */
    private Long id;

    /**
     * 竞赛名称
     */
    private String competitionName;

    /**
     * 竞赛描述
     */
    private String description;

    /**
     * 创建人ID
     */
    private String creatorId;
    /**
     * 主办单位
     */
    private String organizer;

    /**
     * 报名开始时间
     */
    private Date startTime;

    /**
     * 报名结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}