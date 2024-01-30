package com.xiyuan.project.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName 学校标签
 */
@TableName(value ="school")
@Data
public class SchoolVO implements Serializable {
    /**
     * 学校ID
     */
    private Long id;

    /**
     * 学校名称
     */
    private String schoolName;

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