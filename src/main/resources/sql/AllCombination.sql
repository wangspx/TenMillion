-- auto Generated on 2019-07-21
DROP TABLE IF EXISTS all_combination;
CREATE TABLE all_combination
(
    id          bigint(15) auto_increment comment '自增长主键' primary key,
    `sort`      int(11)     NULL DEFAULT NULL COMMENT '序号',
    combination varchar(14) NULL comment '6个红球和1个篮球的组合',
    create_time datetime    NULL comment '创建时间',
    update_time timestamp   NULL comment '更新时间',
    status      int         NULL comment '数据状态 0：可用，其余不可用'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT 'all_combination';
CREATE INDEX combination_index ON all_combination(combination);
