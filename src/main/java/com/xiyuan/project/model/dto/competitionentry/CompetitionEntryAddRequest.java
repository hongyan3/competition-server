package com.xiyuan.project.model.dto.competitionentry;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 参赛-作品关系表
 * @TableName competition_entry
 */
@Data
public class CompetitionEntryAddRequest implements Serializable {
    /**
     * 竞赛ID
     */
    private Long competitionId;

    /**
     * 作品ID
     */
    private Long entryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}