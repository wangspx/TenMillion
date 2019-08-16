/*
 Navicat Premium Data Transfer

 Source Server         : 47.107.120.53
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 47.107.120.53:9000
 Source Schema         : luck

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 14/08/2019 11:42:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NULL DEFAULT NULL COMMENT '期号',
  `combination_id` int(11) NULL DEFAULT NULL COMMENT '组合id',
  `combination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组合',
  `open_date` datetime(0) NULL DEFAULT NULL COMMENT '开奖日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
