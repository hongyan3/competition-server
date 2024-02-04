package com.xiyuan.project.model.dto.entry;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 参赛作品
 * @TableName entry
 */
@TableName(value ="entry")
@Data
public class EntryAddRequest implements Serializable {

    /**
     * 作品名称
     */
    private String entryName;

    /**
     * 作品描述
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}