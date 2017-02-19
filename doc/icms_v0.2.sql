/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : icms

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2017-02-19 09:00:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `descr` varchar(512) NOT NULL DEFAULT '',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL,
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '0', '新闻1+1', 'xxx', '0', '0', '0', '2017-02-11 20:49:56', '2017-02-11 20:49:59');
INSERT INTO `category` VALUES ('2', '0', '新闻直通车', '新闻直通车', '0', '0', '0', '2017-02-11 20:50:56', '2017-02-11 20:50:58');
INSERT INTO `category` VALUES ('3', '0', 'ffhh', '', '0', '0', '0', '2017-02-14 23:46:16', '2017-02-14 23:57:33');
INSERT INTO `category` VALUES ('4', '0', '33', '', '0', '0', '0', '2017-02-14 23:57:44', '2017-02-15 00:26:11');
INSERT INTO `category` VALUES ('5', '0', '44444', '', '0', '0', '0', '2017-02-15 00:26:22', '2017-02-15 21:53:33');
INSERT INTO `category` VALUES ('6', '0', '55555', '', '0', '0', '0', '2017-02-15 00:27:09', '2017-02-15 21:53:35');
INSERT INTO `category` VALUES ('7', '0', '666', '', '0', '0', '0', '2017-02-15 00:28:04', '2017-02-15 21:53:37');
INSERT INTO `category` VALUES ('8', '0', '777', '', '0', '0', '0', '2017-02-15 21:50:15', '2017-02-15 21:53:40');
INSERT INTO `category` VALUES ('9', '0', '体坛快讯', '', '0', '0', '0', '2017-02-15 21:53:59', '2017-02-15 21:53:59');
INSERT INTO `category` VALUES ('10', '0', '广告歌', '', '0', '0', '0', '2017-02-19 01:31:47', '2017-02-19 01:31:47');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) NOT NULL DEFAULT '',
  `content` longtext,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `editor` int(11) DEFAULT '0',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `unique_name` (`cname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', 'home', '{\"isOnWebSiteHeader\":1,\"webSiteName\":\"web site name\",\"webSiteDetail\":\"web site detail\",\"isOnHeadline\":1,\"headlineCategoryId\":1,\"headlinePostId\":1,\"templateItems\":[{\"tiid\":1,\"name\":\"itemname\",\"categoryId\":2,\"templateId\":1,\"staticPosts\":[2],\"indexs\":1},{\"tiid\":2,\"name\":\"itemname2\",\"categoryId\":2,\"templateId\":2,\"staticPosts\":[2,3],\"indexs\":2}]}', '0', '2017-02-11 17:47:49', '2017-02-12 04:01:02', '1');
INSERT INTO `config` VALUES ('2', 'home2', '{\"isOnWebSiteHeader\":1,\"webSiteName\":\"web site name\",\"webSiteDetail\":\"web site detail\",\"isOnHeadline\":1,\"headlineCategoryId\":1,\"headlinePost\":1,\"templateItems\":[{\"tiid\":1,\"name\":\"itemname\",\"categoryId\":2,\"templateId\":1,\"staticPosts\":[1],\"indexs\":1}]}', '0', '2017-02-11 18:03:49', '2017-02-11 18:03:49', '1');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `type` tinyint(4) NOT NULL DEFAULT '0',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creator` int(11) NOT NULL DEFAULT '0',
  `updated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `editor` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '你不得不看的今日大头条！', '新闻内容。。。\r\n。。。。。。。。。。。。。。。。。。。。。。。\r\n.........................', '0', '0', '0', '2017-02-11 19:56:31', '1', '2017-02-11 19:56:34', '1');
INSERT INTO `post` VALUES ('2', '2', '最新消息，最新消息', '<p>消息内容，消息内容\\n文章内容哈哈哈急急急</p>', '0', '0', '0', '2017-02-11 20:52:32', '1', '2017-02-11 01:26:22', '1');
INSERT INTO `post` VALUES ('3', '2', '来日方长', '<p>《静夜思》</p><p>床前明月光，意思地上霜。</p><p>举头望明月，低头思故乡。</p><p>——李白</p><p><br/></p>', '0', '0', '0', '2017-02-11 20:56:51', '0', '2017-02-11 20:56:53', '0');
INSERT INTO `post` VALUES ('4', '2', '火云邪神', '<p>如来神掌</p><p>周星驰</p><p>功夫大幅度随风倒似懂非懂士大夫大师傅胜多负少第三代</p><p>哈哈哈哈哈哈哈哈哈哈哈</p><p>添加点内容777刚刚66ggggg</p><p>ggdsgsdg sfsdf sdf&nbsp;</p>', '0', '0', '0', '2017-02-11 20:56:54', '0', '2017-02-11 20:56:56', '0');
INSERT INTO `post` VALUES ('5', '2', '方法对付对付对付就业饿不摧哦去', '封建法律精神分裂上岛咖啡角度看凌凤街道法律框架的分开两地警方\\n减肥ijfdoifjdoifjdfojifufo哦发奖金地方的粉底哦发电机房成本', '0', '0', '0', '2017-02-12 03:21:21', '0', '2017-02-12 03:21:23', '0');
INSERT INTO `post` VALUES ('6', '2', '打分得分第三方斯蒂芬', '顺风使舵福鼎市反对反对萨芬', '0', '0', '0', '2017-02-12 03:26:03', '0', '2017-02-12 03:26:05', '0');
INSERT INTO `post` VALUES ('7', '2', '第三方大幅度反对萨芬的', '哦ioioweiwqoiiofidfds发生的繁华的科技示范户但是客户', '0', '0', '0', '2017-02-12 03:26:09', '0', '2017-02-12 03:26:11', '0');
INSERT INTO `post` VALUES ('8', '2', '犯得上反对犯得上发', '<p>十分士大夫士大夫士大夫似的士大夫十分速度发射点发士大夫速度发士大夫但是发士大夫速度速度发</p>', '0', '0', '0', '2017-02-18 14:10:08', '1', '2017-02-18 14:10:08', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `user` VALUES ('1', '1', 'iyihua', '0', '457ebd4372bbb8f58881e3aabb254ade16296f0deba1853f', '1', '619361578@qq.com', '13560427799', '123456789', '0', '', '0', '2017-02-11 14:20:52', '0000-00-00 00:00:00', '0');
