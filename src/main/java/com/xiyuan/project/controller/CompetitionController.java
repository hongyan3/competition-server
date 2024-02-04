package com.xiyuan.project.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.competition.CompetitionQueryRequest;
import com.xiyuan.project.model.entity.Competition;
import com.xiyuan.project.model.entity.School;
import com.xiyuan.project.model.vo.CompetitionVO;
import com.xiyuan.project.model.vo.SchoolVO;
import com.xiyuan.project.service.CompetitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/2/4 21:24
 */
@RestController
@RequestMapping("/competition")
@Slf4j
public class CompetitionController {
    @Resource
    CompetitionService competitionService;

    @GetMapping
    public BaseResponse<Page<CompetitionVO>> getCompetitionList(@ModelAttribute CompetitionQueryRequest queryRequest){
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<Competition> competitionPage = competitionService.page(new Page<>(current,size),
                competitionService.getQueryWrapper(queryRequest));
        List<CompetitionVO> competitionVOList = competitionService.getCompetitionVO(competitionPage.getRecords());
        Page<CompetitionVO> voPage = new Page<>(current,size,competitionPage.getTotal());
        voPage.setRecords(competitionVOList);
        return ResultUtils.success(voPage);
    }

    @GetMapping("/{competitionId}")
    public BaseResponse<CompetitionVO> getCompetitionById(@PathVariable Long competitionId) {
        if (competitionId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        CompetitionVO competitionVO = competitionService.getCompetitionVO(competitionService.getById(competitionId));
        return ResultUtils.success(competitionVO);
    }
}
