package com.xiyuan.project.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyuan
 * @interfaceName EntryCommentTypeEnum
 * @description TODO
 * @date 2024/2/6 22:17
 */
@Getter
public enum EntryCommentTypeEnum {

    COMMON("普通评论", 1),
    REPLY_COMMON("回复评论", 2);
    private final String text;
    private final int value;

    EntryCommentTypeEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static EntryCommentTypeEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        for (EntryCommentTypeEnum typeEnum : EntryCommentTypeEnum.values()) {
            if (typeEnum.value == value) {
                return typeEnum;
            }
        }
        return null;
    }
}
