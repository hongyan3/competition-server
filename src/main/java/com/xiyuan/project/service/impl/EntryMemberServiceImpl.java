package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.mapper.EntryMemberMapper;
import com.xiyuan.project.model.entity.EntryMember;
import com.xiyuan.project.model.entity.User;
import com.xiyuan.project.model.vo.UserVO;
import com.xiyuan.project.service.EntryMemberService;
import com.xiyuan.project.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyuan
 * @description 针对表【entry_member(作品成员)】的数据库操作Service实现
 * @createDate 2024-02-04 22:54:16
 */
@Service
public class EntryMemberServiceImpl extends ServiceImpl<EntryMemberMapper, EntryMember>
        implements EntryMemberService {

    @Resource
    UserService userService;

    @Override
    public List<UserVO> getEntryMemberToUserVOList(Long entryId) {
        if (entryId == null || entryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<EntryMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entry_id", entryId);
        List<EntryMember> entryMemberList = this.list(queryWrapper);
        List<User> userList = entryMemberList.stream().map(e -> userService.getById(e.getMemberId())).collect(Collectors.toList());
        return userService.getUserVO(userList);
    }

    @Override
    public List<EntryMember> getEntryMemberList(EntryMember entryMember) {
        if (entryMember == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long entryId = entryMember.getEntryId();
        Long memberId = entryMember.getMemberId();
        if (entryId == null || memberId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<EntryMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entry_id", entryId);
        queryWrapper.eq("member_id", memberId);
        return this.list(queryWrapper);
    }

    @Override
    public void removeEntryMember(EntryMember entryMember) {
        if (entryMember == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long entryId = entryMember.getEntryId();
        Long memberId = entryMember.getMemberId();
        if (entryId == null || memberId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<EntryMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entry_id", entryId);
        queryWrapper.eq("member_id", memberId);
        boolean result = this.remove(queryWrapper);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
    }

    @Override
    public void addEntryMember(EntryMember entryMember) {
        if (entryMember == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long entryId = entryMember.getEntryId();
        Long memberId = entryMember.getMemberId();
        if (entryId == null || memberId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = this.save(entryMember);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
    }
}




