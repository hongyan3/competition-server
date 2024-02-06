package com.xiyuan.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作品评论
 * @TableName entry_comment
 */
@TableName(value ="entry_comment")
@Data
public class EntryComment implements Serializable {
    /**
     * 评论ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 作品ID
     */
    private Long entryId;

    /**
     * 评论人ID
     */
    private Long userId;

    /**
     * 评论类型 1-评论 2-回复
     */
    private Integer commentType;

    /**
     * 回复的评论ID
     */
    private Long replyId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}