package com.xiyuan.project.controller.admin;

import com.xiyuan.project.annotation.AuthCheck;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.school.SchoolAddRequest;
import com.xiyuan.project.model.dto.school.SchoolUpdateRequest;
import com.xiyuan.project.model.entity.School;
import com.xiyuan.project.model.enums.UserRoleEnum;
import com.xiyuan.project.service.SchoolService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/1/30 22:43
 */
@RestController
@RequestMapping("/admin/school")
public class SchoolAdminController {
    @Resource
    SchoolService schoolService;

    @PostMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Long> addSchool(@RequestBody SchoolAddRequest addRequest) {
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        School school = new School();
        BeanUtils.copyProperties(addRequest,school);
        boolean result = schoolService.save(school);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(school.getId());
    }

    @PutMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> UpdateSchool(@RequestBody SchoolUpdateRequest updateRequest) {
        if (updateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        School school = new School();
        BeanUtils.copyProperties(updateRequest,school);
        boolean result = schoolService.updateById(school);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/{schoolId}")
    public BaseResponse<Boolean> DeleteSchool(@PathVariable Long schoolId) {
        if (schoolId == null || schoolId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = schoolService.removeById(schoolId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
