package com.xiyuan.project.model.dto.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/5/15 下午4:27
 */
@Data
public class EntryUpdateStatusRequest implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 作品ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 作品状态 0-待审核 1-已审核
     */
    private Integer status;
    /**
     * 状态备注
     */
    private String statusRemarks;
}
