-- auto Generated on 2019-07-21
DROP TABLE IF EXISTS red_combination;
CREATE TABLE red_combination
(
    id          BIGINT(15) AUTO_INCREMENT COMMENT '自增长主键',
    create_time DATETIME   NOT NULL default CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    update_time TIMESTAMP  NOT NULL default current_timestamp ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `status`    INT(11)    NULL COMMENT '数据状态 0：可用，其余不可用',
    one         VARCHAR(2) NULL COMMENT 'one',
    two         VARCHAR(2) NULL COMMENT 'two',
    three       VARCHAR(2) NULL COMMENT 'three',
    four        VARCHAR(2) NULL COMMENT 'four',
    five        VARCHAR(2) NULL COMMENT 'five',
    six         VARCHAR(2) NULL COMMENT 'six',
    PRIMARY KEY (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT 'red_combination';
