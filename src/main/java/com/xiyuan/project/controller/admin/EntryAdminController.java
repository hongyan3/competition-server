package com.xiyuan.project.controller.admin;

import com.xiyuan.project.annotation.AuthCheck;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.entry.EntryAddRequest;
import com.xiyuan.project.model.dto.entry.EntryUpdateRequest;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.enums.UserRoleEnum;
import com.xiyuan.project.service.EntryService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/2/4 22:38
 */
@RestController
@RequestMapping("/admin/entry")
public class EntryAdminController {
    @Resource
    EntryService entryService;

    @PostMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Long> addEntry(@RequestBody EntryAddRequest addRequest) {
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Entry entry = new Entry();
        BeanUtils.copyProperties(addRequest,entry);
        entryService.validEntry(entry,true);
        boolean result = entryService.save(entry);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(entry.getId());
    }

    @PutMapping
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> UpdateEntry(@RequestBody EntryUpdateRequest updateRequest) {
        if (updateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Entry entry = new Entry();
        BeanUtils.copyProperties(updateRequest,entry);
        boolean result = entryService.updateById(entry);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/{entryId}")
    public BaseResponse<Boolean> DeleteEntry(@PathVariable Long entryId) {
        if (entryId == null || entryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = entryService.removeById(entryId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
