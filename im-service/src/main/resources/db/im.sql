/*
 Navicat Premium Data Transfer

 Source Server         : mysql-test
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : im

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 13/03/2022 14:56:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for im_message
-- ----------------------------
DROP TABLE IF EXISTS `im_message`;
CREATE TABLE `im_message` (
  `id` int NOT NULL,
  `message_id` varchar(64) NOT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '消息体',
  `room_id` char(10) NOT NULL COMMENT '房间id',
  `type` varchar(255) DEFAULT NULL COMMENT '消息类型',
  `audit_type` varchar(255) DEFAULT NULL COMMENT '审核类型',
  `sender` varchar(255) DEFAULT NULL COMMENT '发送者',
  `receiver` varchar(255) DEFAULT NULL COMMENT '接受者',
  `time` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for im_online
-- ----------------------------
DROP TABLE IF EXISTS `im_online`;
CREATE TABLE `im_online` (
  `id` int NOT NULL,
  `user_id` char(10) NOT NULL,
  `client` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for im_room
-- ----------------------------
DROP TABLE IF EXISTS `im_room`;
CREATE TABLE `im_room` (
  `id` int NOT NULL,
  `room_id` char(10) NOT NULL,
  `mute` int DEFAULT NULL,
  `audit_type` varchar(255) DEFAULT NULL COMMENT '审计方式, 1: 人工审核, 2: 智能审核, 3: 不审核, 0: 默认, 黑词过滤',
  `create_time` datetime DEFAULT NULL,
  `deleted` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for im_user
-- ----------------------------
DROP TABLE IF EXISTS `im_user`;
CREATE TABLE `im_user` (
  `id` int NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
