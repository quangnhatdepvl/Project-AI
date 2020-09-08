/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100413
 Source Host           : localhost:3306
 Source Schema         : ghosteye

 Target Server Type    : MySQL
 Target Server Version : 100413
 File Encoding         : 65001

 Date: 08/09/2020 13:24:29
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
  `fullName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `className` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `monHoc` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fkMonHoc_id`(`monHoc`) USING BTREE,
  CONSTRAINT `fkMonHoc_id` FOREIGN KEY (`monHoc`) REFERENCES `face_monhoc` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of face_bio
-- ----------------------------
INSERT INTO `face_bio` VALUES (1, 123, 'nhat', 'dao', 2);
INSERT INTO `face_bio` VALUES (2, 98, 'klll', 'nmbnm', 2);

-- ----------------------------
-- Table structure for face_monhoc
-- ----------------------------
DROP TABLE IF EXISTS `face_monhoc`;
CREATE TABLE `face_monhoc`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenMonHoc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `giaoVien` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of face_monhoc
-- ----------------------------
INSERT INTO `face_monhoc` VALUES (1, 'CNPM', 'Thay Phuoc');
INSERT INTO `face_monhoc` VALUES (2, 'AI', 'Thay Du');

SET FOREIGN_KEY_CHECKS = 1;
