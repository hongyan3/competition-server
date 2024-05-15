package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.college.CollegeQueryRequest;
import com.xiyuan.project.model.entity.College;
import com.xiyuan.project.model.vo.CollegeVO;

import java.util.List;

/**
 * @author xiyuan
 * @description 针对表【college(学院表)】的数据库操作Service
 * @createDate 2024-05-15 14:40:49
 */
public interface CollegeService extends IService<College> {
    /**
     * 获取查询条件
     *
     * @param collegeQueryRequest
     * @return
     */
    QueryWrapper<College> getQueryWrapper(CollegeQueryRequest collegeQueryRequest);
    /**
     * 获取脱敏的比赛信息
     *
     * @return
     */
    CollegeVO getCollegeVO(College college);

    /**
     * 获取脱敏的比赛信息
     *
     * @param collegeList
     * @return
     */
    List<CollegeVO> getCollegeVO(List<College> collegeList);

}
