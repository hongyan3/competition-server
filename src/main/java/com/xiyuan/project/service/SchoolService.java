package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.school.SchoolQueryRequest;
import com.xiyuan.project.model.entity.School;
import com.xiyuan.project.model.vo.SchoolVO;

import java.util.List;

/**
* @author xiyuan
* @description 针对表【school】的数据库操作Service
* @createDate 2024-01-30 21:32:29
*/
public interface SchoolService extends IService<School> {
    QueryWrapper<School> getQueryWrapper(SchoolQueryRequest schoolQueryRequest);
    SchoolVO getSchoolVO(School school);
    List<SchoolVO> getSchoolVO(List<School> schoolList);
}
