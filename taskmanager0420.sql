/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : taskmanager

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-04-20 17:37:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `account` varchar(30) NOT NULL COMMENT '登录帐号，以数字和英文字母组成',
  `password` varchar(255) NOT NULL COMMENT '登录密码',
  `create_time` datetime DEFAULT NULL COMMENT '生成时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('7', '清泉', '7456', '$2a$10$eAGpD0d17joAjh0r7jxqi.lmp2PADlnYYgZV5dRA0zzFh//qfAGhG', '2019-04-04 18:15:58', '2019-04-04 18:15:58');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `content` varchar(255) NOT NULL COMMENT '任务内容',
  `pic` varchar(255) DEFAULT NULL COMMENT '任务图片路径（最多存三张）',
  `remind_time` bigint(15) DEFAULT NULL COMMENT '提醒时间',
  `remind_way` varchar(30) DEFAULT NULL COMMENT '提醒方式(可多选)\r\n1：邮件。2：短信。3：微信（预留方式）\r\n  同时以邮箱和短信方式提醒 eg: {1,2}',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='任务表';

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('2', '1', '内置性能分析插件：可输出 Sql 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询\r\n内置全局拦截插件：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作\r\n内置 Sql 注入剥离器：支持 Sql 注入剥离，有效预防 Sql 注入攻', null, '1555752261448', '2', '2019-04-20 14:12:00', '2019-04-20 14:12:05');

-- ----------------------------
-- Table structure for task_remind
-- ----------------------------
DROP TABLE IF EXISTS `task_remind`;
CREATE TABLE `task_remind` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL COMMENT '任务ID',
  `remind_time` bigint(15) NOT NULL COMMENT '提醒时间',
  `create_time` datetime NOT NULL,
  `last_update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of task_remind
-- ----------------------------
INSERT INTO `task_remind` VALUES ('2', '2', '1555752691448', '2019-04-20 14:17:11', '2019-04-20 14:17:14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `nick_name` varchar(30) NOT NULL COMMENT '昵称',
  `phone_num` varchar(11) DEFAULT NULL COMMENT '手机号',
  `wx_num` varchar(30) DEFAULT NULL COMMENT '微信号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱帐号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '啊三', '啊三', '15913189393', 'wx56184318461', '1822948363@qq.com', '2019-04-20 11:46:33', '2019-04-20 11:46:35');
DROP TRIGGER IF EXISTS `testtir`;
DELIMITER ;;
CREATE TRIGGER `testtir` BEFORE INSERT ON `system_user` FOR EACH ROW begin
set NEW.create_time = NOW(),NEW.last_update_time = NOW();
end
;;
DELIMITER ;
