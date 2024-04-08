package com.xiyuan.project.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiyuan.project.model.entity.EntrySource;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 参赛作品
 * @TableName entry
 */
@TableName(value ="entry")
@Data
public class EntryVO implements Serializable {
    /**
     * 作品ID
     */
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
     * 参赛学院
     */
    private String college;

    /**
     * 创建者信息
     */
    private UserVO creatorInfo;

    /**
     * 成员列表
     */
    private List<UserVO> members;

    /**
     * 资料列表
     */
    private List<EntrySource> sources;
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