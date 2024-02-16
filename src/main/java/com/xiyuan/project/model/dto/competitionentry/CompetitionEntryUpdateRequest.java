package com.xiyuan.project.model.dto.competitionentry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 参赛-作品关系表
 * @TableName competition_entry
 */
@Data
public class CompetitionEntryUpdateRequest implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 0-未获奖 1-三等奖 2-二等奖 3-一等奖
     */
    private Integer competitionRank;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}