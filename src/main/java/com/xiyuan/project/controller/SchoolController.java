package com.xiyuan.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.school.SchoolQueryRequest;
import com.xiyuan.project.model.entity.School;
import com.xiyuan.project.model.vo.SchoolVO;
import com.xiyuan.project.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/1/30 22:07
 */
@RestController
@RequestMapping("/school")
@Slf4j
public class SchoolController {
    @Resource
    SchoolService schoolService;

    @GetMapping
    public BaseResponse<Page<SchoolVO>> getSchoolList(@ModelAttribute SchoolQueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<School> schoolPage = schoolService.page(new Page<>(current,size),
                schoolService.getQueryWrapper(queryRequest));
        List<SchoolVO> schoolVOList = schoolService.getSchoolVO(schoolPage.getRecords());
        Page<SchoolVO> voPage = new Page<>(current,size,schoolPage.getTotal());
        voPage.setRecords(schoolVOList);
        return ResultUtils.success(voPage);
    }
    @GetMapping("/{schoolId}")
    public BaseResponse<SchoolVO> getSchoolById(@PathVariable Long schoolId) {
        if (schoolId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SchoolVO schoolVO = schoolService.getSchoolVO(schoolService.getById(schoolId));
        return ResultUtils.success(schoolVO);
    }
}
