/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100406
 Source Host           : localhost:3306
 Source Schema         : ghosteye

 Target Server Type    : MySQL
 Target Server Version : 100406
 File Encoding         : 65001

 Date: 19/07/2020 15:55:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for face_bio
-- ----------------------------
DROP TABLE IF EXISTS `face_bio`;
CREATE TABLE `face_bio`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(10) NOT NULL,
  `first_name` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_name` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `reg` int(10) NOT NULL,
  `age` int(10) NOT NULL,
  `section` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of face_bio
-- ----------------------------
INSERT INTO `face_bio` VALUES (46, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (47, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (48, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (49, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (50, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (51, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (52, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (53, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (54, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (55, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (56, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (57, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (58, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (59, 1, 'quang', 'nhat', 12, 12, '12');
INSERT INTO `face_bio` VALUES (60, 2, 'Thanh', 'Nghi', 13, 13, '13');
INSERT INTO `face_bio` VALUES (61, 2, 'Thanh', 'Nghi', 13, 13, '13');
INSERT INTO `face_bio` VALUES (62, 2, 'Thanh', 'Nghi', 13, 13, '13');
INSERT INTO `face_bio` VALUES (63, 2, 'Thanh', 'Nghi', 13, 13, '13');
INSERT INTO `face_bio` VALUES (64, 2, 'Thanh', 'Nghi', 13, 13, '13');
INSERT INTO `face_bio` VALUES (65, 2, 'Thanh', 'Nghi', 13, 13, '13');
INSERT INTO `face_bio` VALUES (66, 2, 'Thanh', 'Nghi', 13, 13, '13');

SET FOREIGN_KEY_CHECKS = 1;
