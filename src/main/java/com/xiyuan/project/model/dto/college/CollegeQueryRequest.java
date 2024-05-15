package com.xiyuan.project.model.dto.college;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xiyuan.project.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/5/15 下午3:19
 */
@Data
public class CollegeQueryRequest extends PageRequest implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 学院名称
     */
    private String collegeName;
}
