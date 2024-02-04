package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.constant.CommonConstant;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.mapper.CompetitionMapper;
import com.xiyuan.project.model.dto.competition.CompetitionQueryRequest;
import com.xiyuan.project.model.entity.Competition;
import com.xiyuan.project.model.vo.CompetitionVO;
import com.xiyuan.project.service.CompetitionService;
import com.xiyuan.project.utils.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyuan
 * @description 针对表【competition(竞赛)】的数据库操作Service实现
 * @createDate 2024-02-04 20:54:39
 */
@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition>
        implements CompetitionService {

    @Override
    public void validCompetition(Competition competition, boolean add) {
        if (competition == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String competitionName = competition.getCompetitionName();
        String creatorId = competition.getCreatorId();
        String organizer = competition.getOrganizer();
        Date startTime = competition.getStartTime();
        Date endTime = competition.getEndTime();
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(competitionName,creatorId,organizer),ErrorCode.PARAMS_ERROR);
            if (startTime == null || endTime == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
            if (startTime.after(endTime)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"开始时间不能大于结束时间");
            }
        }
        if (StringUtils.isNotBlank(competitionName) && competitionName.length() > 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"竞赛名称过长");
        }
        if (StringUtils.isNotBlank(organizer) && organizer.length() > 100) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"主办单位名称过长");
        }
    }

    @Override
    public CompetitionVO getCompetitionVO(Competition competition) {
        if (competition == null) {
            return null;
        }
        CompetitionVO competitionVO = new CompetitionVO();
        BeanUtils.copyProperties(competition, competitionVO);
        return competitionVO;
    }

    @Override
    public List<CompetitionVO> getCompetitionVO(List<Competition> competitionList) {
        if (CollectionUtils.isEmpty(competitionList)) {
            return new ArrayList<>();
        }
        return competitionList.stream().map(this::getCompetitionVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Competition> getQueryWrapper(CompetitionQueryRequest competitionQueryRequest) {
        if (competitionQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = competitionQueryRequest.getId();
        String competitionName = competitionQueryRequest.getCompetitionName();
        String creatorId = competitionQueryRequest.getCreatorId();
        String organizer = competitionQueryRequest.getOrganizer();
        String sortField = competitionQueryRequest.getSortField();
        String sortOrder = competitionQueryRequest.getSortOrder();
        // 拼接查询条件
        QueryWrapper<Competition> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(creatorId != null, "creator_id", creatorId);
        queryWrapper.like(StringUtils.isNotBlank(competitionName), "competition_name", competitionName);
        queryWrapper.like(StringUtils.isNotBlank(organizer), "organizer", organizer);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




