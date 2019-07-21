-- auto Generated on 2019-07-21
-- DROP TABLE IF EXISTS red_combination;
CREATE TABLE red_combination(
    id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT '自增长主键',
    create_time DATETIME NOT NULL COMMENT '创建时间',
    update_time TIMESTAMP NOT NULL COMMENT '更新时间',
    `status` INT (11) NOT NULL COMMENT '数据状态 0：可用，其余不可用',
    one VARCHAR (50) NOT NULL COMMENT 'one',
    two VARCHAR (50) NOT NULL COMMENT 'two',
    three VARCHAR (50) NOT NULL COMMENT 'three',
    four VARCHAR (50) NOT NULL COMMENT 'four',
    five VARCHAR (50) NOT NULL COMMENT 'five',
    six VARCHAR (50) NOT NULL COMMENT 'six',
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'red_combination';
