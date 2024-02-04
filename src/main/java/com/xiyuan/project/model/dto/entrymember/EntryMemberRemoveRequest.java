package com.xiyuan.project.model.dto.entrymember;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 作品成员
 * @TableName entry_member
 */
@TableName(value ="entry_member")
@Data
public class EntryMemberRemoveRequest implements Serializable {

    /**
     * 作品ID
     */
    private Long entryId;

    /**
     * 成员ID
     */
    private Long memberId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}