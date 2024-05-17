package com.xiyuan.project.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.annotation.AuthCheck;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.entry.EntryAddRequest;
import com.xiyuan.project.model.dto.entry.EntryQueryRequest;
import com.xiyuan.project.model.dto.entry.EntryUpdateRequest;
import com.xiyuan.project.model.dto.entry.EntryUpdateStatusRequest;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.enums.UserRoleEnum;
import com.xiyuan.project.model.vo.EntryVO;
import com.xiyuan.project.service.EntryService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

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
        BeanUtils.copyProperties(addRequest, entry);
        entryService.validEntry(entry, true);
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
        BeanUtils.copyProperties(updateRequest, entry);
        boolean result = entryService.updateById(entry);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/{entryId}")
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> DeleteEntry(@PathVariable Long entryId) {
        if (entryId == null || entryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = entryService.removeById(entryId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/review")
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Page<EntryVO>> getReviewEntryList(@ModelAttribute EntryQueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        QueryWrapper<Entry> queryWrapper = entryService.getQueryWrapper(queryRequest);
        queryWrapper.eq("status",0);
        Page<Entry> entryPage = entryService.page(new Page<>(current, size),
                queryWrapper);
        List<EntryVO> entryVOList = entryService.getEntryVO(entryPage.getRecords());
        Page<EntryVO> voPage = new Page<>(current, size, entryPage.getTotal());
        voPage.setRecords(entryVOList);
        return ResultUtils.success(voPage);
    }

    @PutMapping("/review")
    @AuthCheck(AccessRole = UserRoleEnum.ADMIN)
    public BaseResponse<Boolean> updateEntryStatus(@RequestBody EntryUpdateStatusRequest request) {
        if (request == null || request.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Entry entry = new Entry();
        BeanUtils.copyProperties(request,entry);
        boolean result = entryService.updateById(entry);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
}
