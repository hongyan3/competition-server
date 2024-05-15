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
 *
 * @TableName entry
 */
@TableName(value = "entry")
@Data
public class EntryVO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
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
     * 参赛学院ID
     */
    private Long collegeId;
    /**
     * 参赛学院名称
     */
    private String collegeName;
    /**
     * 作品状态 0-待审核 1-已审核
     */
    private Integer status;
    /**
     * 状态备注
     */
    private String statusRemarks;
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
}