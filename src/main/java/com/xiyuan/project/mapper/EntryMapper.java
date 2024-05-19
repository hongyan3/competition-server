package com.xiyuan.project.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.model.entity.Entry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xiyuan
 * @description 针对表【entry(参赛作品)】的数据库操作Mapper
 * @createDate 2024-05-15 14:40:10
 * @Entity generator.domain.Entry
 */
public interface EntryMapper extends BaseMapper<Entry> {
    @Select("select distinct e.* from entry e join entry_member em on e.id = em.entry_id ${ew.customSqlSegment}")
    List<Entry> getEntryListWithMember(Page<Entry> page, @Param(Constants.WRAPPER) Wrapper<?> wrapper);
}




