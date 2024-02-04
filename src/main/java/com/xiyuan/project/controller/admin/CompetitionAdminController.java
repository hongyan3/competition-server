package com.xiyuan.project.controller.admin;

import com.xiyuan.project.annotation.AuthCheck;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.competition.CompetitionAddRequest;
import com.xiyuan.project.model.dto.competition.CompetitionUpdateRequest;
import com.xiyuan.project.model.entity.Competition;
import com.xiyuan.project.model.enums.UserRoleEnum;
import com.xiyuan.project.service.CompetitionService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/2/4 21:39
 */
@RestController
@RequestMapping("/admin/competition")
public class CompetitionAdminController {
    @Resource
    CompetitionService competitionService;

    @PostMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Long> addCompetition(@RequestBody CompetitionAddRequest addRequest) {
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Competition competition = new Competition();
        BeanUtils.copyProperties(addRequest,competition);
        competitionService.validCompetition(competition,true);
        boolean result = competitionService.save(competition);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(competition.getId());
    }

    @PutMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> UpdateCompetition(@RequestBody CompetitionUpdateRequest updateRequest) {
        if (updateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Competition competition = new Competition();
        BeanUtils.copyProperties(updateRequest,competition);
        boolean result = competitionService.updateById(competition);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/{competitionId}")
    public BaseResponse<Boolean> DeleteCompetition(@PathVariable Long competitionId) {
        if (competitionId == null || competitionId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = competitionService.removeById(competitionId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
