-- auto Generated on 2019-07-21
-- DROP TABLE IF EXISTS all_combination;
CREATE TABLE all_combination(
    id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time TIMESTAMP NOT NULL COMMENT '更新时间',
    `status` INT (11) NOT NULL COMMENT '数据状态 0：可用，其余不可用',
    combination VARCHAR (14) NOT NULL COMMENT '6个红球和1个篮球的组合',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'all_combination';
