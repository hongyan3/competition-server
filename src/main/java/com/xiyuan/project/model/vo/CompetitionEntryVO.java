package com.xiyuan.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参赛-作品关系表
 * @TableName competition_entry
 */
@TableName(value ="competition_entry")
@Data
public class CompetitionEntryVO implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 竞赛信息
     */
    private CompetitionVO competition;

    /**
     * 作品信息
     */
    private EntryVO entry;

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