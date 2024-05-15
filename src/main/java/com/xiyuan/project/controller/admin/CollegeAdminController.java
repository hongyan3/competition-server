package com.xiyuan.project.controller.admin;

import com.xiyuan.project.annotation.AuthCheck;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.college.CollegeAddRequest;
import com.xiyuan.project.model.dto.college.CollegeUpdateRequest;
import com.xiyuan.project.model.entity.College;
import com.xiyuan.project.model.enums.UserRoleEnum;
import com.xiyuan.project.service.CollegeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/5/15 下午2:46
 */
@RestController
@RequestMapping("/admin/college")
public class CollegeAdminController {
    @Resource
    CollegeService collegeService;
    /**
     * 创建学院（仅管理员）
     *
     * @param collegeAddRequest
     * @return
     */
    @PostMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Long> addCollege(@RequestBody CollegeAddRequest collegeAddRequest) {
        if (collegeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        College college = new College();
        BeanUtils.copyProperties(collegeAddRequest, college);
        boolean result = collegeService.save(college);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(college.getId());
    }

    /**
     * 删除学院(仅管理员)
     *
     * @param collegeId
     * @return
     */
    @DeleteMapping("/{collegeId}")
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> deleteCollege(@PathVariable Long collegeId) {
        if (collegeId == null || collegeId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean isSuccess = collegeService.removeById(collegeId);
        return ResultUtils.success(isSuccess);
    }

    /**
     * 更新学院(仅管理员)
     *
     * @param request
     * @return
     */
    @PutMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> updateCollege(@RequestBody CollegeUpdateRequest request) {
        if (request == null || request.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        College college = new College();
        BeanUtils.copyProperties(request, college);
        boolean result = collegeService.updateById(college);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
