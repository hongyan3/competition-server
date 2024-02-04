package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.competition.CompetitionQueryRequest;
import com.xiyuan.project.model.entity.Competition;
import com.xiyuan.project.model.vo.CompetitionVO;

import java.util.List;

/**
* @author xiyuan
* @description 针对表【competition(竞赛)】的数据库操作Service
* @createDate 2024-02-04 20:54:39
*/
public interface CompetitionService extends IService<Competition> {
    /**
     * 校验参数
     * @param competition
     * @param add
     */
    void validCompetition(Competition competition,boolean add);
    /**
     * 获取脱敏的已登录比赛信息
     *
     * @return
     */
    CompetitionVO getCompetitionVO(Competition competition);
    /**
     * 获取脱敏的比赛信息
     *
     * @param competitionList
     * @return
     */
    List<CompetitionVO> getCompetitionVO(List<Competition> competitionList);
    /**
     * 获取查询条件
     *
     * @param competitionQueryRequest
     * @return
     */
    QueryWrapper<Competition> getQueryWrapper(CompetitionQueryRequest competitionQueryRequest);
}
