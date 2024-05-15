package com.xiyuan.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 参赛作品
 *
 * @TableName entry
 */
@TableName(value = "entry")
@Data
public class Entry implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 作品ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 作品名称
     */
    private String entryName;
    /**
     * 作品描述
     */
    private String description;
    /**
     * 参赛学院ID
     */
    private Long collegeId;
    /**
     * 创建者ID
     */
    private Long creatorId;
    /**
     * 作品状态 0-待审核 1-已审核
     */
    private Integer status;
    /**
     * 状态备注
     */
    private String statusRemarks;
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