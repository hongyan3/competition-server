package com.xiyuan.project.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.entry.EntryAddRequest;
import com.xiyuan.project.model.dto.entry.EntryEditRequest;
import com.xiyuan.project.model.dto.entry.EntryQueryRequest;
import com.xiyuan.project.model.dto.entrymember.EntryMemberAddRequest;
import com.xiyuan.project.model.dto.entrymember.EntryMemberRemoveRequest;
import com.xiyuan.project.model.dto.entrysource.EntrySourceAddRequest;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.entity.EntryMember;
import com.xiyuan.project.model.entity.EntrySource;
import com.xiyuan.project.model.entity.User;
import com.xiyuan.project.model.vo.EntryVO;
import com.xiyuan.project.service.EntryMemberService;
import com.xiyuan.project.service.EntryService;
import com.xiyuan.project.service.EntrySourceService;
import com.xiyuan.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiyuan
 * @description TODO
 * @date 2024/2/4 22:20
 */
@RestController
@RequestMapping("/entry")
@Slf4j
public class EntryController {
    @Resource
    EntryService entryService;
    @Resource
    EntryMemberService entryMemberService;
    @Resource
    EntrySourceService entrySourceService;
    @Resource
    UserService userService;

    @GetMapping
    public BaseResponse<Page<EntryVO>> getEntryList(@ModelAttribute EntryQueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = queryRequest.getCurrent();
        long size = queryRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<Entry> entryPage = entryService.page(new Page<>(current, size),
                entryService.getQueryWrapper(queryRequest));
        List<EntryVO> entryVOList = entryService.getEntryVO(entryPage.getRecords());
        Page<EntryVO> voPage = new Page<>(current, size, entryPage.getTotal());
        voPage.setRecords(entryVOList);
        return ResultUtils.success(voPage);
    }

    @GetMapping("/{entryId}")
    public BaseResponse<EntryVO> getEntryById(@PathVariable Long entryId) {
        if (entryId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        EntryVO entryVO = entryService.getEntryVO(entryService.getById(entryId));
        return ResultUtils.success(entryVO);
    }

    @PostMapping
    public BaseResponse<Long> addEntry(@RequestBody EntryAddRequest addRequest, HttpServletRequest request) {
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Entry entry = new Entry();
        User user = userService.getLoginUser(request);
        BeanUtils.copyProperties(addRequest, entry);
        entryService.validEntry(entry, true);
        entry.setCreatorId(user.getId());
        boolean result = entryService.save(entry);
        // 添加自己为成员
        EntryMember entryMember = new EntryMember();
        entryMember.setEntryId(entry.getId());
        entryMember.setMemberId(entry.getCreatorId());
        entryMemberService.addEntryMember(entryMember);

        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(entry.getId());
    }

    @PutMapping
    public BaseResponse<Boolean> editEntry(@RequestBody EntryEditRequest editRequest, HttpServletRequest request) {
        if (editRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = editRequest.getId();
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User currentUser = userService.getLoginUser(request);
        Entry currentEntry = entryService.getById(id);
        if (currentEntry == null || !currentEntry.getId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        Entry entry = new Entry();
        BeanUtils.copyProperties(editRequest, entry);
        boolean result = entryService.updateById(entry);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/{entryId}")
    public BaseResponse<Boolean> removeEntry(@PathVariable Long entryId, HttpServletRequest request) {
        User currentUser = userService.getLoginUser(request);
        Entry currentEntry = entryService.getById(entryId);
        if (currentEntry == null || !currentEntry.getId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = entryService.removeById(entryId);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @PostMapping("/member")
    public BaseResponse<Boolean> addEntryMember(@RequestBody EntryMemberAddRequest addRequest, HttpServletRequest request) {
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long entryId = addRequest.getEntryId();
        Long memberId = addRequest.getMemberId();
        if (entryId == null || entryId <= 0 || memberId == null || memberId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User currentUser = userService.getLoginUser(request);
        User user = userService.getById(memberId);
        Entry entry = entryService.getById(entryId);
        if (user == null || entry == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 鉴权
        if (!entry.getCreatorId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        EntryMember entryMember = new EntryMember();
        BeanUtils.copyProperties(addRequest, entryMember);
        // 判断成员是否已存在
        List<EntryMember> entryMemberList = entryMemberService.getEntryMemberList(entryMember);
        if (CollectionUtils.isNotEmpty(entryMemberList)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该成员已存在");
        }
        entryMemberService.addEntryMember(entryMember);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/member")
    public BaseResponse<Boolean> removeEntryMember(@RequestBody EntryMemberRemoveRequest removeRequest, HttpServletRequest request) {
        if (removeRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long entryId = removeRequest.getEntryId();
        Long memberId = removeRequest.getMemberId();
        if (entryId == null || entryId <= 0 || memberId == null || memberId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Entry entry = entryService.getById(entryId);
        if (entry == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "作品不存在");
        }
        // 鉴权
        User currentUser = userService.getLoginUser(request);
        if (!entry.getCreatorId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        if (entry.getCreatorId().equals(memberId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能删除自己");
        }
        EntryMember entryMember = new EntryMember();
        BeanUtils.copyProperties(removeRequest, entryMember);
        entryMemberService.removeEntryMember(entryMember);
        return ResultUtils.success(true);
    }

    @PostMapping("/source")
    public BaseResponse<Boolean> addEntrySource(@RequestBody EntrySourceAddRequest addRequest, HttpServletRequest request) {
        if (addRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User currentUser = userService.getLoginUser(request);
        Long entryId = addRequest.getEntryId();
        Entry entry = entryService.getById(entryId);
        // 检查作品是否存在
        if (entry == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 鉴权
        if (!entry.getCreatorId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        EntrySource entrySource = new EntrySource();
        BeanUtils.copyProperties(addRequest,entrySource);
        entrySourceService.addEntrySource(entrySource);
        return ResultUtils.success(true);
    }

    @DeleteMapping("/source/{sourceId}")
    public BaseResponse<Boolean> removeEntrySource(@PathVariable Long sourceId, HttpServletRequest request) {
        if (sourceId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User currentUser = userService.getLoginUser(request);
        EntrySource source = entrySourceService.getById(sourceId);
        Long entryId = source.getEntryId();
        Entry entry = entryService.getById(entryId);
        // 鉴权
        if (!entry.getCreatorId().equals(currentUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        entrySourceService.removeEntrySource(sourceId);
        return ResultUtils.success(true);
    }
}
