package com.xiyuan.project.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/5/15 下午3:40
 */
@Data
public class CollegeVO implements Serializable {
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
}
