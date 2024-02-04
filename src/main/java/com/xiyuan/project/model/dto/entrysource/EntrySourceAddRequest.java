package com.xiyuan.project.model.dto.entrysource;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 作品资源
 * @TableName entry_source
 */
@TableName(value ="entry_source")
@Data
public class EntrySourceAddRequest implements Serializable {
    /**
     * 作品ID
     */
    private Long entryId;

    /**
     * 资源类型 image|video|audio|file
     */
    private String sourceType;

    /**
     * 资源名称
     */
    private String sourceName;

    /**
     * 资源链接
     */
    private String sourceUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}