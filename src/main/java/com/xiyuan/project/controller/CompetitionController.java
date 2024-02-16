package com.xiyuan.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.competition.CompetitionQueryRequest;
import com.xiyuan.project.model.dto.competitionentry.CompetitionEntryAddRequest;
import com.xiyuan.project.model.dto.competitionentry.CompetitionEntryQueryRequest;
import com.xiyuan.project.model.entity.Competition;
import com.xiyuan.project.model.entity.CompetitionEntry;
import com.xiyuan.project.model.entity.User;
import com.xiyuan.project.model.vo.CompetitionEntryVO;
import com.xiyuan.project.model.vo.CompetitionVO;
import com.xiyuan.project.service.CompetitionEntryService;
import com.xiyuan.project.service.CompetitionService;
import com.xiyuan.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    UserService userService;
    @Resource
    CompetitionService competitionService;
    @Resource
    CompetitionEntryService competitionEntryService;

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

    @PostMapping("/entry")
    public BaseResponse<Long> addCompetitionEntry(@RequestBody CompetitionEntryAddRequest addRequest, HttpServletRequest request) {
        User currentUser = userService.getLoginUser(request);
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long competitionId = addRequest.getCompetitionId();
        Long entryId = addRequest.getEntryId();
        QueryWrapper<CompetitionEntry> competitionEntryQueryWrapper = new QueryWrapper<>();
        competitionEntryQueryWrapper.eq("competition_id",competitionId);
        competitionEntryQueryWrapper.eq("entry_id",entryId);
        // 检查是否存在
        CompetitionEntry queryResult = competitionEntryService.getOne(competitionEntryQueryWrapper);
        if (queryResult != null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该项目已参加此比赛");
        }
        // 检查参赛时间是否截止
        Competition competition = competitionService.getById(competitionId);
        Date endTime = competition.getEndTime();
        Date now = new Date();
        if (now.after(endTime)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该竞赛已截止");
        }
        CompetitionEntry competitionEntry = new CompetitionEntry();
        BeanUtils.copyProperties(addRequest,competitionEntry);
        competitionEntry.setCaptainId(currentUser.getId());
        competitionEntryService.validCompetitionEntry(competitionEntry,true);
        boolean result = competitionEntryService.save(competitionEntry);
        ThrowUtils.throwIf(!result,ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(competitionEntry.getId());
    }
    
    @GetMapping("/entry")
    public BaseResponse<Page<CompetitionEntryVO>> getCompetitionEntryList(@ModelAttribute CompetitionEntryQueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<CompetitionEntry> competitionPage = competitionEntryService.page(new Page<>(current,size),
                competitionEntryService.getQueryWrapper(queryRequest));
        List<CompetitionEntryVO> competitionVOList = competitionEntryService.getCompetitionEntryVO(competitionPage.getRecords());
        Page<CompetitionEntryVO> voPage = new Page<>(current,size,competitionPage.getTotal());
        voPage.setRecords(competitionVOList);
        return ResultUtils.success(voPage);
    }
}
