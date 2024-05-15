package com.xiyuan.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.college.CollegeQueryRequest;
import com.xiyuan.project.model.entity.College;
import com.xiyuan.project.model.vo.CollegeVO;
import com.xiyuan.project.service.CollegeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/5/15 下午3:36
 */
@RestController
@RequestMapping("/college")
@Slf4j
public class CollegeController {
    @Resource
    CollegeService collegeService;

    @GetMapping
    public BaseResponse<Page<CollegeVO>> getCollegeList(@ModelAttribute CollegeQueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<College> collegePage = collegeService.page(new Page<>(current, size),
                collegeService.getQueryWrapper(queryRequest));
        List<CollegeVO> collegeVOList = collegeService.getCollegeVO(collegePage.getRecords());
        Page<CollegeVO> voPage = new Page<>(current, size, collegePage.getTotal());
        voPage.setRecords(collegeVOList);
        return ResultUtils.success(voPage);
    }
}
