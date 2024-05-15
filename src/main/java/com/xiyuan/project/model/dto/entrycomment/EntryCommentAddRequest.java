package com.xiyuan.project.model.dto.entrycomment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 作品评论
 *
 * @TableName entry_comment
 */
@TableName(value = "entry_comment")
@Data
public class EntryCommentAddRequest implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 作品ID
     */
    private Long entryId;
    /**
     * 评论类型 1-评论 2-回复
     */
    private Integer commentType;
    /**
     * 回复的评论ID
     */
    private Long replyId;
}