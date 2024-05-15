package com.xiyuan.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.entity.EntryMember;
import com.xiyuan.project.model.vo.UserVO;

import java.util.List;

/**
 * @author xiyuan
 * @description 针对表【entry_member(作品成员)】的数据库操作Service
 * @createDate 2024-02-04 22:54:16
 */
public interface EntryMemberService extends IService<EntryMember> {
    /**
     * 获取作品成员列表
     *
     * @param entryId
     * @return
     */
    List<UserVO> getEntryMemberToUserVOList(Long entryId);

    List<EntryMember> getEntryMemberList(EntryMember entryMember);

    void removeEntryMember(EntryMember entryMember);

    void addEntryMember(EntryMember entryMember);
}
