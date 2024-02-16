package com.xiyuan.project.model.dto.competitionentry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xiyuan.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参赛-作品关系表
 * @TableName competition_entry
 */
@Data
public class CompetitionEntryQueryRequest extends PageRequest implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 竞赛ID
     */
    private Long competitionId;

    /**
     * 作品ID
     */
    private Long entryId;

    /**
     * 项目队长ID
     */
    private Long captainId;

    /**
     * 0-未获奖 1-三等奖 2-二等奖 3-一等奖
     */
    private Integer competitionRank;

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