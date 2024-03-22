package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.constant.CommonConstant;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.mapper.EntryCommentMapper;
import com.xiyuan.project.model.dto.entrycomment.EntryCommentQueryRequest;
import com.xiyuan.project.model.entity.EntryComment;
import com.xiyuan.project.model.entity.User;
import com.xiyuan.project.model.enums.EntryCommentTypeEnum;
import com.xiyuan.project.model.vo.EntryCommentVO;
import com.xiyuan.project.model.vo.UserVO;
import com.xiyuan.project.service.EntryCommentService;
import com.xiyuan.project.service.UserService;
import com.xiyuan.project.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author xiyuan
* @description 针对表【entry_comment(作品评论)】的数据库操作Service实现
* @createDate 2024-02-06 20:42:45
*/
@Service
public class EntryCommentServiceImpl extends ServiceImpl<EntryCommentMapper, EntryComment>
    implements EntryCommentService{

    @Resource
    UserService userService;
    @Override
    public void validEntryComment(EntryComment entryComment, boolean add) {
        if (entryComment == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String content = entryComment.getContent();
        Long entryId = entryComment.getEntryId();
        Integer commentType = entryComment.getCommentType();
        Long replyId = entryComment.getReplyId();
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(content), ErrorCode.PARAMS_ERROR);
            EntryCommentTypeEnum enumByValue = EntryCommentTypeEnum.getEnumByValue(commentType);
            if (enumByValue == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"评论类型错误");
            }
        }
        if (entryId == null || entryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (commentType == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        } else if (commentType == 2 && replyId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public EntryCommentVO getEntryCommentVO(EntryComment entryComment) {
        if (entryComment == null) {
            return null;
        }
        Long userId = entryComment.getUserId();
        Long replyId = entryComment.getReplyId();
        // 获取评论人信息
        User user = userService.getById(userId);
        UserVO userVO = userService.getUserVO(user);
        // 获取被评论内容
        EntryComment replyComment = null;
        if (replyId != null) {
            replyComment = this.getById(replyId);
        }
        EntryCommentVO entryCommentVO = new EntryCommentVO();
        BeanUtils.copyProperties(entryComment,entryCommentVO);

        entryCommentVO.setReplyComment(replyComment);
        entryCommentVO.setUserInfo(userVO);
        return entryCommentVO;
    }

    @Override
    public List<EntryCommentVO> getEntryCommentVO(List<EntryComment> entryList) {
        if (CollectionUtils.isEmpty(entryList)) {
            return new ArrayList<>();
        }
        return entryList.stream().map(this::getEntryCommentVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<EntryComment> getQueryWrapper(EntryCommentQueryRequest entryQueryRequest) {
        if (entryQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = entryQueryRequest.getId();
        String content = entryQueryRequest.getContent();
        Long entryId = entryQueryRequest.getEntryId();
        Long userId = entryQueryRequest.getUserId();
        Integer commentType = entryQueryRequest.getCommentType();
        Long replyId = entryQueryRequest.getReplyId();
        String sortField = entryQueryRequest.getSortField();
        String sortOrder = entryQueryRequest.getSortOrder();
        // 拼接查询条件
        QueryWrapper<EntryComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(entryId != null, "entry_id", entryId);
        queryWrapper.eq(userId != null, "user_id", userId);
        queryWrapper.eq(commentType != null, "comment_type", commentType);
        queryWrapper.eq(replyId != null, "reply_id", replyId);
        queryWrapper.like(StringUtils.isNotBlank(content), "content", content);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




