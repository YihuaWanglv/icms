/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50628
Source Host           : localhost:3306
Source Database       : icms

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2016-12-12 12:39:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL,
  `pid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `descr` varchar(512) NOT NULL DEFAULT '',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `pid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `deleted` tinyint(1) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL,
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creator` int(11) NOT NULL,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `editor` int(11) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) NOT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `password` varchar(64) NOT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(64) NOT NULL,
  `mobile` varchar(32) NOT NULL DEFAULT '',
  `salt` varchar(64) NOT NULL DEFAULT '0',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `code` varchar(64) NOT NULL DEFAULT '',
  `ucid` bigint(20) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
