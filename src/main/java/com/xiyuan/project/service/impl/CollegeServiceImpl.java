package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.constant.CommonConstant;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.mapper.CollegeMapper;
import com.xiyuan.project.model.dto.college.CollegeQueryRequest;
import com.xiyuan.project.model.entity.College;
import com.xiyuan.project.model.vo.CollegeVO;
import com.xiyuan.project.service.CollegeService;
import com.xiyuan.project.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyuan
 * @description 针对表【college(学院表)】的数据库操作Service实现
 * @createDate 2024-05-15 14:40:49
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
        implements CollegeService {

    @Override
    public QueryWrapper<College> getQueryWrapper(CollegeQueryRequest collegeQueryRequest) {
        if (collegeQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        String collegeName = collegeQueryRequest.getCollegeName();
        String sortField = collegeQueryRequest.getSortField();
        String sortOrder = collegeQueryRequest.getSortOrder();
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(collegeName),"college_name",collegeName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public CollegeVO getCollegeVO(College college) {
        if (college == null) {
            return null;
        }
        CollegeVO collegeVO = new CollegeVO();
        BeanUtils.copyProperties(college,collegeVO);
        return collegeVO;
    }

    @Override
    public List<CollegeVO> getCollegeVO(List<College> collegeList) {
        if (CollectionUtils.isEmpty(collegeList)) {
            return new ArrayList<>();
        }
        return collegeList.stream().map(this::getCollegeVO).collect(Collectors.toList());
    }
}




