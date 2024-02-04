package com.xiyuan.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.mapper.EntrySourceMapper;
import com.xiyuan.project.model.entity.EntrySource;
import com.xiyuan.project.model.enums.EntrySourceTypeEnum;
import com.xiyuan.project.service.EntrySourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xiyuan
* @description 针对表【entry_source(作品资源)】的数据库操作Service实现
* @createDate 2024-02-04 22:57:12
*/
@Service
public class EntrySourceServiceImpl extends ServiceImpl<EntrySourceMapper, EntrySource>
    implements EntrySourceService{

    @Override
    public List<EntrySource> getEntrySourceList(Long entryId) {
        if (entryId == null || entryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<EntrySource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entry_id", entryId);
        return this.list(queryWrapper);
    }

    @Override
    public void removeEntrySource(Long entrySourceId) {
        if (entrySourceId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = this.removeById(entrySourceId);
        ThrowUtils.throwIf(!result,ErrorCode.OPERATION_ERROR);
    }

    @Override
    public void addEntrySource(EntrySource entrySource) {
        if (entrySource == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long entryId = entrySource.getEntryId();
        if (entryId == null || entryId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String sourceType = entrySource.getSourceType();
        String sourceUrl = entrySource.getSourceUrl();
        String sourceName = entrySource.getSourceName();
        // 校验参数是否为空白
        if (StringUtils.isAnyBlank(sourceType,sourceUrl,sourceName)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        EntrySourceTypeEnum typeEnum = EntrySourceTypeEnum.getEnumByValue(sourceType);
        // 校验类型枚举是否合法
        if (typeEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = this.save(entrySource);
        ThrowUtils.throwIf(!result,ErrorCode.OPERATION_ERROR);
    }
}




