package com.xiyuan.project.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiyuan.project.model.entity.EntryComment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作品评论
 *
 * @TableName entry_comment
 */
@TableName(value = "entry_comment")
@Data
public class EntryCommentVO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 评论ID
     */
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
    private UserVO userInfo;
    /**
     * 评论类型 1-评论 2-回复
     */
    private Integer commentType;
    /**
     * 回复的评论ID
     */
    private EntryComment replyComment;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}