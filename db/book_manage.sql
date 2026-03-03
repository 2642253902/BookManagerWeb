/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726 (5.7.26)
 Source Host           : localhost:3306
 Source Schema         : book_manage

 Target Server Type    : MySQL
 Target Server Version : 50726 (5.7.26)
 File Encoding         : 65001

 Date: 03/03/2026 21:55:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '图书管理员', '123456');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '三体', '刘慈欣科幻小说，三部曲第一部。', 68.00);
INSERT INTO `book` VALUES (2, '活着', '余华著，讲述普通人命运的悲欢。', 39.50);
INSERT INTO `book` VALUES (3, '百年孤独', '加西亚·马尔克斯代表作，魔幻现实主义经典。', 88.00);
INSERT INTO `book` VALUES (4, '算法导论', 'CLRS，计算机科学经典教材，适合深入学习算法。', 128.00);
INSERT INTO `book` VALUES (5, '深入理解MySQL', 'MySQL 性能优化与架构设计实战。', 158.00);
INSERT INTO `book` VALUES (6, 'Java编程思想', 'Bruce Eckel 著，面向对象与 Java 核心概念详解。', 149.00);
INSERT INTO `book` VALUES (7, '简爱', '夏洛蒂·勃朗特著，经典英国文学。', 45.00);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NULL DEFAULT NULL,
  `bid` int(11) NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (7, 3, 1, '2026-03-02 23:22:51');
INSERT INTO `borrow` VALUES (6, 1, 3, '2026-03-02 23:22:39');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` enum('男','女') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '男',
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (5, '陈杰', '男', 22);
INSERT INTO `student` VALUES (4, '刘洋', '女', 18);
INSERT INTO `student` VALUES (3, '李强', '男', 21);
INSERT INTO `student` VALUES (2, '王芳', '女', 19);
INSERT INTO `student` VALUES (1, '张伟', '男', 20);
INSERT INTO `student` VALUES (6, '赵敏', '女', 20);
INSERT INTO `student` VALUES (7, '周鹏', '男', 23);
INSERT INTO `student` VALUES (8, '孙莉', '女', 17);
INSERT INTO `student` VALUES (9, '胡超', '男', 24);
INSERT INTO `student` VALUES (10, '高翔', '男', 19);

SET FOREIGN_KEY_CHECKS = 1;
