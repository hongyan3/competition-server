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
) COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `school`
(
    `id`          bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '学校ID',
    `school_name` varchar(100)       NOT NULL COMMENT '学校名称',
    `create_time` datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
) COMMENT '学校表';

CREATE TABLE IF NOT EXISTS `competition`
(
    `id`               bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '竞赛ID',
    `competition_name` varchar(100)       NOT NULL COMMENT '竞赛名称',
    `description`      text               NOT NULL COMMENT '竞赛描述',
    `creator_id`       bigint             NOT NULL COMMENT '创建人ID',
    `organizer`        varchar(100)       NOT NULL COMMENT '主办单位',
    `start_time`       datetime           NOT NULL COMMENT '报名开始时间',
    `end_time`         datetime           NOT NULL COMMENT '报名结束时间',
    `create_time`      datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`        tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
) COMMENT '竞赛';

CREATE TABLE IF NOT EXISTS `entry`
(
    `id`          bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '作品ID',
    `entry_name`  varchar(100)       NOT NULL COMMENT '作品名称',
    `description` text               NOT NULL COMMENT '作品描述',
    `creator_id`  bigint             NOT NULL COMMENT '创建者ID',
    `create_time` datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`   tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
) COMMENT '参赛作品';

CREATE TABLE IF NOT EXISTS `entry_member`
(
    `id`        bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `entry_id`  bigint             NOT NULL COMMENT '作品ID',
    `member_id` bigint             NOT NULL COMMENT '成员ID'
) COMMENT '作品成员';

CREATE TABLE IF NOT EXISTS `entry_source`
(
    `id`          bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '资源ID',
    `entry_id`    bigint             NOT NULL COMMENT '作品ID',
    `source_name` varchar(30)        NOT NULL COMMENT '资源名称',
    `source_type` varchar(20)        NOT NULL COMMENT '资源类型 image|video|audio|file',
    `source_url`  varchar(256)       NOT NULL COMMENT '资源链接'
) COMMENT '作品资源';

CREATE TABLE IF NOT EXISTS `entry_comment`
(
    `id`           bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `content`      text               NOT NULL COMMENT '评论内容',
    `entry_id`     bigint             NOT NULL COMMENT '作品ID',
    `user_id`      bigint             NOT NULL COMMENT '评论人ID',
    `comment_type` tinyint            NOT NULL DEFAULT 1 COMMENT '评论类型 1-评论 2-回复',
    `reply_id`     bigint COMMENT '回复的评论ID',
    `create_time`  datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`    tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
) COMMENT '作品评论';

CREATE TABLE IF NOT EXISTS `competition_entry`
(
    `id`               bigint PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `competition_id`   bigint             NOT NULL COMMENT '竞赛ID',
    `entry_id`         bigint             NOT NULL COMMENT '作品ID',
    `captain_id`       bigint             NOT NULL COMMENT '项目队长ID',
    `competition_rank` tinyint(1)         NOT NULL DEFAULT 0 COMMENT '0-未获奖 1-三等奖 2-二等奖 3-一等奖',
    `create_time`      datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_delete`        tinyint(1)         NOT NULL DEFAULT 1 COMMENT '逻辑删除 0-删除 1-正常'
) COMMENT '参赛-作品关系表';