package com.xiyuan.project.model.dto.competition;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 竞赛
 * @TableName competition
 */
@Data
public class CompetitionAddRequest implements Serializable {

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}