/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50509
Source Host           : localhost:3306
Source Database       : icms

Target Server Type    : MYSQL
Target Server Version : 50509
File Encoding         : 65001

Date: 2017-02-26 23:03:25
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
INSERT INTO `category` VALUES ('11', '0', '新分类', '', '0', '0', '0', '2017-02-21 00:06:41', '2017-02-21 00:06:41');

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
INSERT INTO `config` VALUES ('1', 'home', '{\"isOnWebSiteHeader\":1,\"webSiteName\":\"5858\",\"webSiteDetail\":\"这是一个神奇的网站\",\"isOnHeadline\":1,\"headlineCategoryId\":1,\"headlinePostId\":1,\"headlinePost\":null,\"templateItems\":[{\"tiid\":1,\"name\":\"时事要闻\",\"categoryId\":2,\"templateId\":1,\"indexs\":1,\"count\":6,\"staticPosts\":[2],\"posts\":null},{\"tiid\":2,\"name\":\"体坛快讯\",\"categoryId\":2,\"templateId\":2,\"indexs\":2,\"count\":6,\"staticPosts\":[2,3,1],\"posts\":null},{\"tiid\":3,\"name\":\"暴走大事件\",\"categoryId\":2,\"templateId\":1,\"indexs\":3,\"count\":6,\"staticPosts\":[],\"posts\":null}]}', '0', '2017-02-11 17:47:49', '2017-02-12 04:01:02', '1');
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
  `introduction` varchar(500) NOT NULL DEFAULT '' COMMENT '引言，介绍',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '你不得不看的今日大头条！', '新闻内容。。。\r\n。。。。。。。。。。。。。。。。。。。。。。。\r\n.........................', '0', '0', '0', '2017-02-11 19:56:31', '1', '2017-02-11 19:56:34', '1', '');
INSERT INTO `post` VALUES ('2', '2', '最新消息，最新消息', '<p>街上卖煎饼果子的大叔熟练的摊着面饼，额头渗着细微汗珠，</p><p>环卫工人装上最后一桶垃圾，留下整洁的路面缓缓离去，</p><p>公交车停在斑马线前等待着一群上学的小学生，</p><p>我戴着耳机迎着阳光，看着身边形形色色的人们迎接新一天的开始。</p><p><br/></p><p>&lt;div id=&quot;area-player&quot;&gt;&lt;div id=&quot;noflash-alert&quot; style=&quot;display:none&quot; class=&quot;noflash-alert&quot;&gt;wenzhang&lt;/div&gt;&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;华为心声社区今天公布了一份创始人任正非近期的内部讲话。在这份讲话中任正非对近期网传的华为清理34岁老员工一事进行了回应。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt; &lt;img class=&quot;lazy&quot; src=&quot;http://img.ithome.com/newsuploadfiles/2017/2/20170224_170252_872.jpg&quot; style=&quot;vertical-align: bottom; max-width: 728px; display: inline&quot;&gt;&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;任正非称，“我们公司没有退休金，公司是替在职的员工买了社保、医保、意外伤害保险等。你的退休得合乎国家政策。你即使离职了，也得自己去缴费，否则就中断了，国家不承认，你以后就没有养老金了。”&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;他在讲话中表示，“30多岁年青力壮，不努力，光想躺在床上数钱，可能吗？”任正非在讲话中也分享了自己的以身作则的拼搏经历，这位今年已经73岁的创始人表示，“我鼓励你们奋斗，我自己会践行。”&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;清理老员工的传言最初来源于华为内部沟通网站心声社区上的一篇帖子。当时的帖子透露，华为中国区开始集中清理34+的交付工程维护人员，而研发则是开始集中清退40+的老员工，主要针对程序员。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;对于此事，凤凰科技当时也进行了报道，华为官方当时回应称系属谣言。网上对此事的评价也褒贬不一。有说这是在进行变相裁员，也有支持者表示这是企业经营压力增大后不得已而为之。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;另外，任正非此次也透露，华为尼泊尔办事处从2014年亏损2.7亿美元，到2015年亏损3000万美元，于去年实现了盈利2140万美元。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;以下是讲话全文：&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;《&lt;span style=&quot;font-weight: 700; font-style: normal&quot;&gt;任正非：我若贪生怕死，何来让你们英勇奋斗&lt;/span&gt;》&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;非常高兴尼泊尔代表处的进步，你们的一个历史项目概算亏损，从大前年亏损2.7亿美金，到前年亏损3000万美金，到去年盈利2140万美金。在喜马拉雅南麓一路爬坡，辛苦了。听说去年你们都涨了工资，我十分高兴。巴西代表处也历经磨难，终于走上了成功之路。他们说，再过两到三年能把前二十年的亏损全部补回来，我认为五年能做平就不错了，我就很高兴了。巴西的亏损也有我们盲目领导的责任，不能全怪员工。你们真伟大，从泥坑里爬出来的人，都是圣人。我也向全球在努力扭转亏损的弟兄们致敬。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;这次有机会去了珠峰大本营看了看你们站点，到5200米，我真的不行了，得慢慢地走，不敢快，英雄不是当年。我想，你们把一根一根铁塔部件背上山的艰难。十几年前，公司在西藏墨脱开通“450”设备的一个站点时，王文征带200名民工，背着拆开的各种部件，4天4夜翻过4座4000-5000米的雪山，风餐露宿，开通了墨脱的通信，为公司在中国保留了一个“450”设备西藏试验区作出了贡献。来回是8天8夜，都是野外啊，想想都流泪了。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;网上传有员工34岁要退休，不知谁来给他们支付退休金？我们公司没有退休金，公司是替在职的员工买了社保、医保、意外伤害保险等。你的退休得合乎国家政策。你即使离职了，也得自己去缴费，否则就中断了，国家不承认，你以后就没有养老金了。当然你们也可以问西藏、玻利维亚、战乱、瘟疫……地区的英勇奋斗员工，征集他们愿不愿意为你们提供养老金，因为这些地区的奖金高。他们爬冰卧雪、含辛茹苦，可否分点给你。华为是没有钱的，大家不奋斗就垮了，不可能为不奋斗者支付什么。30多岁年青力壮，不努力，光想躺在床上数钱，可能吗？&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;春节期间我去了拉美。以前都跑的是大国体会还不足，这次跑的都是小国，深刻体会拉美员工的艰难。两个相邻国，应该一脚就迈过去了，因经济落后，没有直达飞机。结果要转三次飞机，每次飞40-50分钟，到一个机场等2-3个小时，再飞1小时；再转一次飞机，从下午飞，到第二天天亮才能到，而且全是经济舱。因此，我们要理解，他们不仅跨两个大洋，隔我们两万公里。而且在陆地上，也非常不方便，有效工作时间也不足，在考核基线上，要考虑这些困难。我也经历过两次空中危险，幸亏飞行员迫降成功。员工乘经济舱连续飞行40多个小时，他们这么辛苦，哪里想挤出钱来养那些不想干活的人。公司允许我乘商务舱，比员工还好一些，乘坐头等舱差价是我自己支付的，陪同人员的机票等是我自己支付的，并非公司支付。公司文件中，只有病员才允许陪同。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;我承诺，只要我还飞得动，就会到艰苦地区来看你们，到战乱、瘟疫……地区来陪你们。我若贪生怕死，何来让你们去英勇奋斗。在阿富汗战乱时，我去看望过员工。利比亚开战前两天，我在利比亚，我飞到伊拉克时，利比亚就开战了。我飞到伊拉克不到两天，伊拉克首富告我：“我今天必须将你送走，明天伊拉克就封路开战了。我不能用专机送你，不安全，我派保镖送你。”结果前后一个大车队，十多名保镖，连续奔驰一千多公里，把我送上了最后一架飞机。一路上换车队，就如从深圳到西藏，经过广西换广西车队；经过贵州、云南换当地车队。粤B一直开到那里，那里就太显眼了。&lt;/p&gt;&nbsp;</p><p>&lt;p style=&quot;margin-top: 0px; margin-bottom: 0px; padding-top: 10px; padding-bottom: 10px; color: rgb(66, 66, 66); font-size: 1pc; line-height: 30px; font-family: Microsoft Yahei; font-style: normal; font-weight: normal; text-align: justify; white-space: normal&quot;&gt;我鼓励你们奋斗，我自己会践行。谢谢在叙利亚、也门……奋斗的员工，至今我、徐直军、陈黎芳、彭中阳……都认为也门饭是世界最好吃的饭。&lt;/p&gt;&nbsp;</p><p>&lt;p&gt; &lt;br&gt;&lt;/p&gt;&lt;span class=&quot;clearfix&quot;&gt;&lt;/span&gt;&lt;/div&gt;</p><p><br/></p>', '0', '0', '0', '2017-02-11 20:52:32', '1', '2017-02-11 01:26:22', '1', '街上卖煎饼果子的大叔熟练的摊着面饼，额头渗着细微汗珠，\n环卫工人装上最后一桶垃圾，留下整洁的路面缓缓离去，\n公交车停在斑马线前等待着一群上学的小学生，\n我戴着耳机迎着阳光，看着身边形形色色的人们迎接新一天的开始。\n我戴着耳机迎着阳光，看着身边形形色色的人们迎接新一天的开始。\n我戴着耳机迎着阳光，看着身边形形色色的人们迎接新一天的开始。\n我戴着耳机迎着阳光，看着身边形形色色的人们迎接新一天的开始。');
INSERT INTO `post` VALUES ('3', '2', '来日方长', '<p><span style=\"color: rgb(66, 66, 66); font-family: &quot;Microsoft Yahei&quot;; text-align: justify; background-color: rgb(247, 247, 247);\">华为心声社区今天公布了一份创始人任正非近期的内部讲话。在这份讲话中任正非对近期网传的华为清理34岁老员工一事进行了回应。</span></p><p><span style=\"color: rgb(66, 66, 66); font-family: &quot;Microsoft Yahei&quot;; text-align: justify; background-color: rgb(247, 247, 247);\"><br/></span></p><p><span style=\"color: rgb(66, 66, 66); font-family: &quot;Microsoft Yahei&quot;; text-align: justify; background-color: rgb(247, 247, 247);\"><a href=\"http://img.ithome.com/newsuploadfiles/2017/2/20170224_170252_872.jpg\">http://img.ithome.com/newsuploadfiles/2017/2/20170224_170252_872.jpg</a> </span></p><p><br/></p><p><img src=\"http://img.ithome.com/newsuploadfiles/2017/2/20170224_170252_872.jpg\"/></p><p><br/></p><p>任正非称，“我们公司没有退休金，公司是替在职的员工买了社保、医保、意外伤害保险等。你的退休得合乎国家政策。你即使离职了，也得自己去缴费，否则就中断了，国家不承认，你以后就没有养老金了。”</p><p><br/></p><p>他在讲话中表示，“30多岁年青力壮，不努力，光想躺在床上数钱，可能吗？”任正非在讲话中也分享了自己的以身作则的拼搏经历，这位今年已经73岁的创始人表示，“我鼓励你们奋斗，我自己会践行。”</p><p><br/></p><p>清理老员工的传言最初来源于华为内部沟通网站心声社区上的一篇帖子。当时的帖子透露，华为中国区开始集中清理34+的交付工程维护人员，而研发则是开始集中清退40+的老员工，主要针对程序员。</p><p><br/></p><p>对于此事，凤凰科技当时也进行了报道，华为官方当时回应称系属谣言。网上对此事的评价也褒贬不一。有说这是在进行变相裁员，也有支持者表示这是企业经营压力增大后不得已而为之。</p><p><br/></p><p>另外，任正非此次也透露，华为尼泊尔办事处从2014年亏损2.7亿美元，到2015年亏损3000万美元，于去年实现了盈利2140万美元。</p><p><br/></p><p>以下是讲话全文：</p><p><br/></p><p>《任正非：我若贪生怕死，何来让你们英勇奋斗》</p><p><br/></p><p>非常高兴尼泊尔代表处的进步，你们的一个历史项目概算亏损，从大前年亏损2.7亿美金，到前年亏损3000万美金，到去年盈利2140万美金。在喜马拉雅南麓一路爬坡，辛苦了。听说去年你们都涨了工资，我十分高兴。巴西代表处也历经磨难，终于走上了成功之路。他们说，再过两到三年能把前二十年的亏损全部补回来，我认为五年能做平就不错了，我就很高兴了。巴西的亏损也有我们盲目领导的责任，不能全怪员工。你们真伟大，从泥坑里爬出来的人，都是圣人。我也向全球在努力扭转亏损的弟兄们致敬。</p><p><br/></p><p>这次有机会去了珠峰大本营看了看你们站点，到5200米，我真的不行了，得慢慢地走，不敢快，英雄不是当年。我想，你们把一根一根铁塔部件背上山的艰难。十几年前，公司在西藏墨脱开通“450”设备的一个站点时，王文征带200名民工，背着拆开的各种部件，4天4夜翻过4座4000-5000米的雪山，风餐露宿，开通了墨脱的通信，为公司在中国保留了一个“450”设备西藏试验区作出了贡献。来回是8天8夜，都是野外啊，想想都流泪了。</p><p><br/></p><p>网上传有员工34岁要退休，不知谁来给他们支付退休金？我们公司没有退休金，公司是替在职的员工买了社保、医保、意外伤害保险等。你的退休得合乎国家政策。你即使离职了，也得自己去缴费，否则就中断了，国家不承认，你以后就没有养老金了。当然你们也可以问西藏、玻利维亚、战乱、瘟疫……地区的英勇奋斗员工，征集他们愿不愿意为你们提供养老金，因为这些地区的奖金高。他们爬冰卧雪、含辛茹苦，可否分点给你。华为是没有钱的，大家不奋斗就垮了，不可能为不奋斗者支付什么。30多岁年青力壮，不努力，光想躺在床上数钱，可能吗？</p><p><br/></p><p>春节期间我去了拉美。以前都跑的是大国体会还不足，这次跑的都是小国，深刻体会拉美员工的艰难。两个相邻国，应该一脚就迈过去了，因经济落后，没有直达飞机。结果要转三次飞机，每次飞40-50分钟，到一个机场等2-3个小时，再飞1小时；再转一次飞机，从下午飞，到第二天天亮才能到，而且全是经济舱。因此，我们要理解，他们不仅跨两个大洋，隔我们两万公里。而且在陆地上，也非常不方便，有效工作时间也不足，在考核基线上，要考虑这些困难。我也经历过两次空中危险，幸亏飞行员迫降成功。员工乘经济舱连续飞行40多个小时，他们这么辛苦，哪里想挤出钱来养那些不想干活的人。公司允许我乘商务舱，比员工还好一些，乘坐头等舱差价是我自己支付的，陪同人员的机票等是我自己支付的，并非公司支付。公司文件中，只有病员才允许陪同。</p><p><br/></p><p>我承诺，只要我还飞得动，就会到艰苦地区来看你们，到战乱、瘟疫……地区来陪你们。我若贪生怕死，何来让你们去英勇奋斗。在阿富汗战乱时，我去看望过员工。利比亚开战前两天，我在利比亚，我飞到伊拉克时，利比亚就开战了。我飞到伊拉克不到两天，伊拉克首富告我：“我今天必须将你送走，明天伊拉克就封路开战了。我不能用专机送你，不安全，我派保镖送你。”结果前后一个大车队，十多名保镖，连续奔驰一千多公里，把我送上了最后一架飞机。一路上换车队，就如从深圳到西藏，经过广西换广西车队；经过贵州、云南换当地车队。粤B一直开到那里，那里就太显眼了。</p><p><br/></p><p>我鼓励你们奋斗，我自己会践行。谢谢在叙利亚、也门……奋斗的员工，至今我、徐直军、陈黎芳、彭中阳……都认为也门饭是世界最好吃的饭。</p><p><br/></p>', '0', '0', '0', '2017-02-11 20:56:51', '0', '2017-02-11 20:56:53', '0', '床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n床前明月光，意思地上霜。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。\n举头望明月，低头思故乡。');
INSERT INTO `post` VALUES ('4', '2', '火云邪神', '<p>如来神掌gggggggggggggggggg</p><p>周星驰</p><p>功夫大幅度随风倒似懂非懂士大夫大师傅胜多负少第三代</p><p>哈哈哈哈哈哈哈哈哈哈哈</p><p>添加点内容777刚刚66ggggg</p><p>ggdsgsdg sfsdf sdf&nbsp;</p>', '0', '0', '0', '2017-02-11 20:56:54', '0', '2017-02-11 20:56:56', '0', '现在朋友见面都说我是肌肉男，夜跑3年，健身一年，有很多辛苦与不易，但也在这途中遇到更好的自己。每天夜跑一小时，听着自己的歌单，让灵魂和大自然交融，风吹着我，雨淋着我，都好像和我交流，有一瞬间，我真的感觉在跑向更好的自己。事实也是，我变得越来越好，却越来越单纯。今晚夜跑刚回来，好帅');
INSERT INTO `post` VALUES ('5', '2', '方法对付对付对付就业饿不摧哦去', '封建法律精神分裂上岛咖啡角度看凌凤街道法律框架的分开两地警方\\n减肥ijfdoifjdoifjdfojifufo哦发奖金地方的粉底哦发电机房成本', '0', '0', '0', '2017-02-12 03:21:21', '0', '2017-02-12 03:21:23', '0', '');
INSERT INTO `post` VALUES ('6', '2', '打分得分第三方斯蒂芬', '<p>顺风使舵福鼎市反对反对萨芬</p><p><br/></p><p>&quot;喂！&quot; &quot;干什么？&quot; &quot;走了？&quot; &quot;是啊。&quot; &quot;去哪里啊？&quot; &quot;回家。&quot; &quot;然后呢？&quot; &quot;上班喽！&quot; &quot;不上班行不行？&quot; &quot;不上班你养我啊？&quot; &quot;喂！&quot; &quot;又怎么了？&quot; &quot;我养你啊！&quot; &quot;你先养好你自己吧，傻瓜！&quot;</p><p><br/></p><p>我初二上了不到半个月就辍学了！去工厂上班，我总是把简历填成高中学历，就是怕别人不要我，曾经为爱执着，为工作起早贪黑。打工十年！闯了十年.拼了十年.十年梦想破灭.十年一事无成.十年挥洒青春。</p><p><br/></p><p>觉得星爷所拍的电影充满正能量！情形阿婆被人勒索100元，星爷看到默默自己扔出100元在地上。。。一句：我养你啊！这是责任的表现。正是作为当代年轻人所需要学习的。可惜现在不能再看到像星爷再拍电影，现在的电影很难找到那种感觉了，只有再次去重温当年的星爷。。</p>', '0', '0', '0', '2017-02-12 03:26:03', '0', '2017-02-12 03:26:05', '0', '');
INSERT INTO `post` VALUES ('7', '2', '第三方大幅度反对萨芬的', '哦ioioweiwqoiiofidfds发生的繁华的科技示范户但是客户', '0', '0', '0', '2017-02-12 03:26:09', '0', '2017-02-12 03:26:11', '0', '');
INSERT INTO `post` VALUES ('8', '2', '犯得上反对犯得上发', '<p>十分士大夫士大夫士大夫似的士大夫十分速度发射点发士大夫速度发士大夫但是发士大夫速度速度发</p>', '0', '0', '0', '2017-02-18 14:10:08', '1', '2017-02-18 14:10:08', '1', '');
INSERT INTO `post` VALUES ('9', '2', 'sdfdsfdsfdsfdsfsdfsdfsd', '<p>sdfdsfsdfdsfsdfdsfsdf</p>', '0', '0', '0', '2017-02-20 23:51:33', '1', '2017-02-20 23:51:33', '1', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '0');
INSERT INTO `role` VALUES ('2', 'user', '0');

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
  PRIMARY KEY (`uid`),
  UNIQUE KEY `icms_index_user_username` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'iyihua', '0', '457ebd4372bbb8f58881e3aabb254ade16296f0deba1853f', '1', '619361578@qq.com', '13560427799', '123456789', '0', '', '0', '2017-02-11 14:20:52', '2017-02-26 16:06:11', '0');
INSERT INTO `user` VALUES ('2', '2', 'test', '0', '457ebd4372bbb8f58881e3aabb254ade16296f0deba1853f', '0', 'ewabebe@163.com', '13827112233', '123456789', '0', '', '0', '2017-02-26 16:12:09', '2017-02-26 16:12:13', '0');
INSERT INTO `user` VALUES ('3', '2', 'test1', '0', '729112b13f364546ccf16696fe4f94065bd17af7b4ce288a', '1', 'ewabebe2@163.com', '13560420000', '9157107892537778267', '0', 'baa11d18df8a7f2f38c185b9a7252c0de6220292', '0', '2017-02-26 22:08:45', '2017-02-26 22:08:45', '0');
INSERT INTO `user` VALUES ('4', '2', 'test2', '0', '77ed599bc8fa4bb3342fbf5d3c0129bfd2504c8ccd8d3aaa', '1', 'ewabebe3@163.com', '13560420001', '6926086150598874314', '0', 'ccaf736e4195366556fc2147b7fca03099dc007f', '0', '2017-02-26 22:22:21', '2017-02-26 22:22:21', '0');
INSERT INTO `user` VALUES ('5', '2', 'test3', '0', '041ea989ef0a05df1d11fc8c87756be0a0d1ab1927971bf2', '1', 'ewabebe3@163.com', '13560420003', '-1838192639563978719', '0', 'f8a56ac50b5cd21435bef149d050cd7194dda760', '0', '2017-02-26 22:29:04', '2017-02-26 22:29:04', '0');
INSERT INTO `user` VALUES ('6', '2', 'test4', '0', 'd873d1d386258100834cb957e3a8ae699e3330567fc5a304', '1', 'ewabebe4@163.com', '13560420004', '-3312722845781616078', '0', '03d4749cd1cbd7b730811929009aa7772a7c8e57', '0', '2017-02-26 22:33:25', '2017-02-26 22:33:25', '0');
