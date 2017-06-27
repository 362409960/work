/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : device

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-06-02 10:59:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_case_info
-- ----------------------------
DROP TABLE IF EXISTS `d_case_info`;
CREATE TABLE `d_case_info` (
  `id` varchar(32) NOT NULL,
  `patient_name` varchar(100) DEFAULT NULL COMMENT '病人姓名',
  `patinet_no` varchar(50) DEFAULT NULL COMMENT '病人号',
  `hospital` varchar(250) DEFAULT NULL COMMENT '医院',
  `hospital_no` varchar(250) DEFAULT NULL COMMENT '住院号',
  `check_parts` varchar(150) DEFAULT NULL COMMENT '检查部位',
  `check_type` varchar(10) DEFAULT NULL COMMENT '检查类型',
  `clinic_number` varchar(50) DEFAULT NULL COMMENT '门诊号',
  `report_doctor` varchar(32) DEFAULT NULL COMMENT '报告医生',
  `report_state` varchar(10) DEFAULT NULL COMMENT '报告状态',
  `age` int(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `check_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `report_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '申请时间',
  `application_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_case_info
-- ----------------------------

-- ----------------------------
-- Table structure for d_patient_picture
-- ----------------------------
DROP TABLE IF EXISTS `d_patient_picture`;
CREATE TABLE `d_patient_picture` (
  `id` varchar(32) NOT NULL,
  `pic_id` varchar(100) DEFAULT NULL,
  `case_id` varchar(100) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_patient_picture
-- ----------------------------

-- ----------------------------
-- Table structure for d_user
-- ----------------------------
DROP TABLE IF EXISTS `d_user`;
CREATE TABLE `d_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `user_type` varchar(10) DEFAULT NULL COMMENT '用户类型，0超级管理1上传账号2查看账号',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `upate_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_user
-- ----------------------------
INSERT INTO `d_user` VALUES ('1', 'admin', '63c0b6d314a64e60d675fac79e4d68be', '0', '0', null, null);
SET FOREIGN_KEY_CHECKS=1;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for d_user_correspond
-- ----------------------------
DROP TABLE IF EXISTS `d_user_correspond`;
CREATE TABLE `d_user_correspond` (
  `id` varchar(32) NOT NULL,
  `user_u_id` varchar(32) DEFAULT NULL COMMENT '上传账号id',
  `user_v_id` varchar(32) DEFAULT NULL COMMENT '查看用户id',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;

DROP TABLE IF EXISTS `d_pic_info`;
CREATE TABLE `d_pic_info` (
  `id` varchar(32) NOT NULL,
  `pic_path` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `disabled` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

