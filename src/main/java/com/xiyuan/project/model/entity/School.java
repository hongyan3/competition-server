package com.xiyuan.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName 学校标签
 */
@TableName(value ="school")
@Data
public class School implements Serializable {
    /**
     * 学校ID
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}