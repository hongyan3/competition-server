package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.constant.CommonConstant;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.mapper.CompetitionEntryMapper;
import com.xiyuan.project.model.dto.competitionentry.CompetitionEntryQueryRequest;
import com.xiyuan.project.model.entity.Competition;
import com.xiyuan.project.model.entity.CompetitionEntry;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.vo.CompetitionEntryVO;
import com.xiyuan.project.model.vo.CompetitionVO;
import com.xiyuan.project.model.vo.EntryVO;
import com.xiyuan.project.service.CompetitionEntryService;
import com.xiyuan.project.service.CompetitionService;
import com.xiyuan.project.service.EntryService;
import com.xiyuan.project.utils.SqlUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author xiyuan
* @description 针对表【competition_entry(参赛-作品关系表)】的数据库操作Service实现
* @createDate 2024-02-16 21:34:21
*/
@Service
public class CompetitionEntryServiceImpl extends ServiceImpl<CompetitionEntryMapper, CompetitionEntry>
    implements CompetitionEntryService{

    @Resource
    CompetitionService competitionService;
    @Resource
    EntryService entryService;
    @Override
    public void validCompetitionEntry(CompetitionEntry competitionEntry, boolean add) {
        if (competitionEntry == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long competitionId = competitionEntry.getCompetitionId();
        Long entryId = competitionEntry.getEntryId();
        Integer rank = competitionEntry.getCompetitionRank();
        if (add) {
            if (competitionId == null || competitionId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            if (entryId == null || entryId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (rank != null) {
            if (rank > 3) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
    }

    @Override
    public CompetitionEntryVO getCompetitionEntryVO(CompetitionEntry competitionEntry) {
        if (competitionEntry == null) {
            return null;
        }
        Long competitionId = competitionEntry.getCompetitionId();
        Long entryId = competitionEntry.getEntryId();
        // 获取竞赛详细信息
        Competition competition = competitionService.getById(competitionId);
        CompetitionVO competitionVO = competitionService.getCompetitionVO(competition);
        // 获取作品信息
        Entry entry = entryService.getById(entryId);
        EntryVO entryVO = entryService.getEntryVO(entry);

        CompetitionEntryVO competitionEntryVO = new CompetitionEntryVO();
        BeanUtils.copyProperties(competitionEntry,competitionEntryVO);
        competitionEntryVO.setCompetition(competitionVO);
        competitionEntryVO.setEntry(entryVO);
        return competitionEntryVO;
    }

    @Override
    public List<CompetitionEntryVO> getCompetitionEntryVO(List<CompetitionEntry> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(this::getCompetitionEntryVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<CompetitionEntry> getQueryWrapper(CompetitionEntryQueryRequest entryQueryRequest) {
        if (entryQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = entryQueryRequest.getId();
        Long competitionId = entryQueryRequest.getCompetitionId();
        Long entryId = entryQueryRequest.getEntryId();
        Integer rank = entryQueryRequest.getCompetitionRank();
        Long captainId = entryQueryRequest.getCaptainId();
        String sortField = entryQueryRequest.getSortField();
        String sortOrder = entryQueryRequest.getSortOrder();
        // 拼接查询条件
        QueryWrapper<CompetitionEntry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(entryId != null, "entry_id", entryId);
        queryWrapper.eq(competitionId != null, "competition_id", competitionId);
        queryWrapper.eq(rank != null, "competition_rank", rank);
        queryWrapper.eq(captainId != null, "captain_id", captainId);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




