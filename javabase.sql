/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : javabase

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-05-28 15:23:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for name_sign
-- ----------------------------
DROP TABLE IF EXISTS `name_sign`;
CREATE TABLE `name_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sign` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of name_sign
-- ----------------------------
INSERT INTO `name_sign` VALUES ('2', 'up_name', 'up_sign');
INSERT INTO `name_sign` VALUES ('3', 'name1', 'sign1');
INSERT INTO `name_sign` VALUES ('4', 'name2', 'sign2');
INSERT INTO `name_sign` VALUES ('5', 'name1', 'sign1');
INSERT INTO `name_sign` VALUES ('6', 'name2', 'sign2');
INSERT INTO `name_sign` VALUES ('7', 'name1', 'sign1');
INSERT INTO `name_sign` VALUES ('8', 'name2', 'sign2');
