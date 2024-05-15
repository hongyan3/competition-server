package com.xiyuan.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学院表
 *
 * @TableName college
 */
@TableName(value = "college")
@Data
public class College implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 学院ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 学院名称
     */
    private String collegeName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 逻辑删除 0-删除 1-正常
     */
    private Integer isDelete;
}