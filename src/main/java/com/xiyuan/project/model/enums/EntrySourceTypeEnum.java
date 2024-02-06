package com.xiyuan.project.model.enums;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyuan
 * @interfaceName EntrySourceTypeEnum
 * @description TODO
 * @date 2024/2/5 00:43
 */
@Getter
public enum EntrySourceTypeEnum {
    IMAGE("图片","image"),
    VIDEO("视频","video"),
    AUDIO("音频","audio"),
    FILE("文件","file");
    private final String text;
    private final String value;
    EntrySourceTypeEnum(String text,String value) {
        this.text = text;
        this.value = value;
    }
    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }
    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static EntrySourceTypeEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (EntrySourceTypeEnum anEnum : EntrySourceTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
