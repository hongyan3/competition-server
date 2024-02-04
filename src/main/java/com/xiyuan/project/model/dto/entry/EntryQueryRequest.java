package com.xiyuan.project.model.dto.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiyuan.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参赛作品
 * @TableName entry
 */
@TableName(value ="entry")
@Data
public class EntryQueryRequest extends PageRequest implements Serializable {
    /**
     * 作品ID
     */
    private Long id;

    /**
     * 作品名称
     */
    private String entryName;

    /**
     * 创建者ID
     */
    private Long creatorId;

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