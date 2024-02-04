package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.constant.CommonConstant;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.mapper.SchoolMapper;
import com.xiyuan.project.model.dto.school.SchoolQueryRequest;
import com.xiyuan.project.model.entity.School;
import com.xiyuan.project.model.vo.SchoolVO;
import com.xiyuan.project.service.SchoolService;
import com.xiyuan.project.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author xiyuan
* @description 针对表【school】的数据库操作Service实现
* @createDate 2024-01-30 21:32:29
*/
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolService{

    @Override
    public QueryWrapper<School> getQueryWrapper(SchoolQueryRequest queryRequest) {

        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        Long id = queryRequest.getId();
        String schoolName = queryRequest.getSchoolName();
        String sortField = queryRequest.getSortField();
        String sortOrder = queryRequest.getSortOrder();
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null,"id",id);
        queryWrapper.like(StringUtils.isNotBlank(schoolName), "school_name", schoolName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public SchoolVO getSchoolVO(School school) {
        if (school == null) {
            return null;
        }
        SchoolVO schoolVO = new SchoolVO();
        BeanUtils.copyProperties(school,schoolVO);
        return schoolVO;
    }

    @Override
    public List<SchoolVO> getSchoolVO(List<School> schoolList) {
        if (CollectionUtils.isEmpty(schoolList)) {
            return new ArrayList<>();
        }
        return schoolList.stream().map(this::getSchoolVO).collect(Collectors.toList());
    }
}




