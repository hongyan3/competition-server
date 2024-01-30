package com.xiyuan.project.model.dto.school;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiyuan.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName 学校标签
 */
@TableName(value ="school")
@Data
public class SchoolQueryRequest extends PageRequest implements Serializable {
    /**
     * 学校ID
     */
    private Long id;

    /**
     * 学校名称
     */
    private String schoolName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}