package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.constant.CommonConstant;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.mapper.EntryMapper;
import com.xiyuan.project.model.dto.entry.EntryQueryRequest;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.entity.EntrySource;
import com.xiyuan.project.model.vo.EntryVO;
import com.xiyuan.project.model.vo.UserVO;
import com.xiyuan.project.service.EntryMemberService;
import com.xiyuan.project.service.EntryService;
import com.xiyuan.project.service.EntrySourceService;
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
 * @description 针对表【entry(参赛作品)】的数据库操作Service实现
 * @createDate 2024-02-04 22:06:21
 */
@Service
public class EntryServiceImpl extends ServiceImpl<EntryMapper, Entry>
        implements EntryService {

    @Resource
    UserService userService;
    @Resource
    EntryMemberService entryMemberService;
    @Resource
    EntrySourceService entrySourceService;
    @Override
    public void validEntry(Entry entry, boolean add) {
        if (entry == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String entryName = entry.getEntryName();
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(entryName), ErrorCode.PARAMS_ERROR);
        }
        if (StringUtils.isNotBlank(entryName) && entryName.length() > 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "作品名称过长");
        }
    }

    @Override
    public EntryVO getEntryVO(Entry entry) {
        if (entry == null) {
            return null;
        }
        Long id = entry.getId();
        Long creatorId = entry.getCreatorId();
        EntryVO entryVO = new EntryVO();
        BeanUtils.copyProperties(entry, entryVO);
        // 获取创建者信息
        UserVO userVO = userService.getUserVO(creatorId);
        // 获取成员列表
        List<UserVO> entryMemberList = entryMemberService.getEntryMemberToUserVOList(id);
        // 获取资源列表
        List<EntrySource> entrySourceList = entrySourceService.getEntrySourceList(id);
        entryVO.setCreatorInfo(userVO);
        entryVO.setMembers(entryMemberList);
        entryVO.setSources(entrySourceList);
        return entryVO;
    }

    @Override
    public List<EntryVO> getEntryVO(List<Entry> entryList) {
        if (CollectionUtils.isEmpty(entryList)) {
            return new ArrayList<>();
        }
        return entryList.stream().map(this::getEntryVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Entry> getQueryWrapper(EntryQueryRequest entryQueryRequest) {

        if (entryQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = entryQueryRequest.getId();
        Long creatorId = entryQueryRequest.getCreatorId();
        String entryName = entryQueryRequest.getEntryName();
        String sortField = entryQueryRequest.getSortField();
        String sortOrder = entryQueryRequest.getSortOrder();
        // 拼接查询条件
        QueryWrapper<Entry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(creatorId != null, "creator_id", id);
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(StringUtils.isNotBlank(entryName), "entry_name", entryName);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




