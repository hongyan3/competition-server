package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.entry.EntryQueryRequest;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.vo.EntryVO;

import java.util.List;

/**
* @author xiyuan
* @description 针对表【entry(参赛作品)】的数据库操作Service
* @createDate 2024-02-04 22:06:21
*/
public interface EntryService extends IService<Entry> {

    /**
     * 校验参数
     * @param entry
     * @param add
     */
    void validEntry(Entry entry, boolean add);
    /**
     * 获取脱敏的比赛信息
     *
     * @return
     */
    EntryVO getEntryVO(Entry entry);
    /**
     * 获取脱敏的比赛信息
     *
     * @param entryList
     * @return
     */
    List<EntryVO> getEntryVO(List<Entry> entryList);
    /**
     * 获取查询条件
     *
     * @param entryQueryRequest
     * @return
     */
    QueryWrapper<Entry> getQueryWrapper(EntryQueryRequest entryQueryRequest);
}
