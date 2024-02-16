package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.entrycomment.EntryCommentQueryRequest;
import com.xiyuan.project.model.entity.EntryComment;
import com.xiyuan.project.model.vo.EntryCommentVO;

import java.util.List;

/**
* @author xiyuan
* @description 针对表【entry_comment(作品评论)】的数据库操作Service
* @createDate 2024-02-06 20:42:45
*/
public interface EntryCommentService extends IService<EntryComment> {
    /**
     * 校验参数
     * @param entryComment
     * @param add
     */
    void validEntryComment(EntryComment entryComment, boolean add);
    /**
     * 获取脱敏的比赛信息
     *
     * @return
     */
    EntryCommentVO getEntryCommentVO(EntryComment entryComment);
    /**
     * 获取脱敏的比赛信息
     *
     * @param entryList
     * @return
     */
    List<EntryCommentVO> getEntryCommentVO(List<EntryComment> entryList);
    /**
     * 获取查询条件
     *
     * @param entryQueryRequest
     * @return
     */
    QueryWrapper<EntryComment> getQueryWrapper(EntryCommentQueryRequest entryQueryRequest);
}
