package com.xiyuan.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.entity.EntrySource;

import java.util.List;

/**
 * @author xiyuan
 * @description 针对表【entry_source(作品资源)】的数据库操作Service
 * @createDate 2024-02-04 22:57:12
 */
public interface EntrySourceService extends IService<EntrySource> {
    List<EntrySource> getEntrySourceList(Long entryId);

    void removeEntrySource(Long entrySourceId);

    void addEntrySource(EntrySource entrySource);
}
