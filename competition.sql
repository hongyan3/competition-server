CREATE TABLE IF NOT EXISTS `user`
(
    `id`           bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_name`    varchar(30)        NOT NULL COMMENT '用户名',
    `user_account` varchar(30)        NOT NULL COMMENT '用户账号',
    `password`     varchar(256)       NOT NULL COMMENT '密码',
    `user_avatar`  varchar(256)                DEFAULT NULL COMMENT '用户头像',
    `gender`       tinyint(1)                  DEFAULT NULL COMMENT '性别 1-男 2-女 3-保密',
    `school_id`    bigint             NOT NULL COMMENT '学校ID',
    `role`         tinyint(1)         NOT NULL DEFAULT '1' COMMENT '角色 1-普通用户 2-管理员',
    `status`       tinyint(1)         NOT NULL DEFAULT '1' COMMENT '用户状态 0-禁用 1-正常',
    `create_time`  datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`    tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
) comment '用户表';

CREATE TABLE IF NOT EXISTS `school`
(
    `id`          bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '学校ID',
    `school_name` varchar(100)       NOT NULL COMMENT '学校名称',
    `create_time` datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
)