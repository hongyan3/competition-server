package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.competitionentry.CompetitionEntryQueryRequest;
import com.xiyuan.project.model.entity.CompetitionEntry;
import com.xiyuan.project.model.vo.CompetitionEntryVO;

import java.util.List;

/**
* @author xiyuan
* @description 针对表【competition_entry(参赛-作品关系表)】的数据库操作Service
* @createDate 2024-02-16 21:34:21
*/
public interface CompetitionEntryService extends IService<CompetitionEntry> {
    /**
     * 校验参数
     * @param competitionEntry
     * @param add
     */
    void validCompetitionEntry(CompetitionEntry competitionEntry, boolean add);
    /**
     * 获取脱敏的比赛信息
     *
     * @return
     */
    CompetitionEntryVO getCompetitionEntryVO(CompetitionEntry competitionEntry);
    /**
     * 获取脱敏的比赛信息
     *
     * @param entryList
     * @return
     */
    List<CompetitionEntryVO> getCompetitionEntryVO(List<CompetitionEntry> entryList);
    /**
     * 获取查询条件
     *
     * @param entryQueryRequest
     * @return
     */
    QueryWrapper<CompetitionEntry> getQueryWrapper(CompetitionEntryQueryRequest entryQueryRequest);
}
