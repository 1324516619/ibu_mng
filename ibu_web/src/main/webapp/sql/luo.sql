/*
Navicat MySQL Data Transfer


Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : luo

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-19 16:52:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `task_schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `task_schedule_job`;
CREATE TABLE `task_schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `job_name` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `job_status` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `bean_class` varchar(255) DEFAULT NULL,
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '1',
  `spring_id` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) NOT NULL,
  PRIMARY KEY (`job_id`),
  UNIQUE KEY `name_group` (`job_name`,`job_group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_schedule_job
-- ----------------------------
INSERT INTO `task_schedule_job` VALUES ('1', null, '2018-10-09 09:29:09', 'test', 'test', '0', '0/20 * * * * ?', 'test', 'com.luolong.task.TaskTest', '0', null, 'run1');
INSERT INTO `task_schedule_job` VALUES ('2', null, '2018-09-20 10:44:57', 'test1', 'test', '0', '0/5 * * * * ?', 'test2', null, '1', 'taskTest', 'run');
INSERT INTO `task_schedule_job` VALUES ('10', '2014-04-25 16:52:17', '2018-09-20 10:44:59', '中间', '1111', '0', '0/1 * * * * ?', 'xxx', 'com.luolong.task.TaskTest', '1', '', 'run');

-- ----------------------------
-- Table structure for `t_export_data_tmp`
-- ----------------------------
DROP TABLE IF EXISTS `t_export_data_tmp`;
CREATE TABLE `t_export_data_tmp` (
  `batch_no` varchar(50) NOT NULL COMMENT '批号',
  `file_name` varchar(100) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(150) DEFAULT NULL COMMENT '文件路径',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `report_process` varchar(20) DEFAULT NULL COMMENT '处理进度',
  `report_state` varchar(500) DEFAULT NULL COMMENT '处理结果',
  `DOWNLOAD_TIMES` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `template_path` varchar(250) DEFAULT NULL COMMENT '模板路径',
  `search_sql` text COMMENT '处理SQL',
  `created_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `created_name` varchar(50) DEFAULT NULL COMMENT '创建人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近更新时间',
  PRIMARY KEY (`batch_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报表导出记录表';

-- ----------------------------
-- Records of t_export_data_tmp
-- ----------------------------
INSERT INTO `t_export_data_tmp` VALUES ('115662761018140700', '用户数据2018101814.csv', '/1/exportfile/2018/10/87dccf7f-c035-4520-9deb-7f0b7b40cbbd.csv', '4670', '3', '1', '0', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 14:07:01', '2018-10-18 14:07:01');
INSERT INTO `t_export_data_tmp` VALUES ('156010731018140346', '缴费记录数据2018101814.csv', '/1/exportfile/2018/10/c4437a5d-93d5-4797-8567-48c719bfc78f.csv', '4670', '3', '1', '0', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 14:03:47', '2018-10-18 14:03:47');
INSERT INTO `t_export_data_tmp` VALUES ('176531051018135456', '缴费记录数据2018101813.csv', '/1/exportfile/2018/10/b81ebab0-9422-408e-b116-53e8e85e97ed.csv', '4671', '3', '1', '0', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 13:54:56', '2018-10-18 13:54:57');
INSERT INTO `t_export_data_tmp` VALUES ('212657371018142211', '用户数据2018101814.csv', '/1/exportfile/2018/10/d32cbec1-eb56-4bec-9231-cf02420f263a.csv', '4670', '3', '1', '1', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 14:22:11', '2018-10-18 14:22:31');
INSERT INTO `t_export_data_tmp` VALUES ('243460611018140938', '用户数据2018101814.csv', '/1/exportfile/2018/10/4fb54e9c-fc07-4555-b714-dcdde044d166.csv', '4670', '3', '1', '2', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 14:09:38', '2018-10-18 14:11:20');
INSERT INTO `t_export_data_tmp` VALUES ('312716451018135649', '缴费记录数据2018101813.csv', '/1/exportfile/2018/10/2f06db56-2ab2-4d12-a0d2-f7fde260de58.csv', '4670', '3', '1', '0', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 13:56:59', '2018-10-18 13:58:23');
INSERT INTO `t_export_data_tmp` VALUES ('442327221018141655', '用户数据2018101814.csv', '/1/exportfile/2018/10/9dd2b5da-d912-4e74-9d85-6f8b97f65e17.csv', '4670', '3', '1', '1', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 14:16:55', '2018-10-18 14:17:00');
INSERT INTO `t_export_data_tmp` VALUES ('472374711018141833', '用户数据2018101814.csv', '/1/exportfile/2018/10/55f5f5c1-ebe9-4be1-b2bb-7077b112a140.csv', '4670', '3', '1', '3', null, 'SELECT user_id,user_name,user_password,nick_name,phone,create_date,CASE WHEN type =1 THEN \'运营人员\'   WHEN type =2 THEN \'客户\'  WHEN type =3  THEN \'销售\' END type  FROM t_user where 1=1  order by create_date desc ', '1', '玉面小飞龙', '2018-10-18 14:18:34', '2018-10-18 14:23:04');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201801`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201801`;
CREATE TABLE `t_order_201801` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201801
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201802`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201802`;
CREATE TABLE `t_order_201802` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201802
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201803`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201803`;
CREATE TABLE `t_order_201803` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201803
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201804`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201804`;
CREATE TABLE `t_order_201804` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201804
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201805`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201805`;
CREATE TABLE `t_order_201805` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201805
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201806`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201806`;
CREATE TABLE `t_order_201806` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201806
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201807`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201807`;
CREATE TABLE `t_order_201807` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201807
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201808`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201808`;
CREATE TABLE `t_order_201808` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201808
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201809`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201809`;
CREATE TABLE `t_order_201809` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201809
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201810`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201810`;
CREATE TABLE `t_order_201810` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201810
-- ----------------------------
INSERT INTO `t_order_201810` VALUES ('1', '2018-10-01 14:47:30', '201800001', '1.0000');

-- ----------------------------
-- Table structure for `t_order_201811`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201811`;
CREATE TABLE `t_order_201811` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201811
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_201812`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_201812`;
CREATE TABLE `t_order_201812` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `order_no` varchar(40) DEFAULT NULL COMMENT '订单编号',
  `amount` decimal(22,4) DEFAULT '0.0000' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 订单表';

-- ----------------------------
-- Records of t_order_201812
-- ----------------------------

-- ----------------------------
-- Table structure for `t_system_account_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_account_role`;
CREATE TABLE `t_system_account_role` (
  `account_role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账号ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`account_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='账号与角色关系表';

-- ----------------------------
-- Records of t_system_account_role
-- ----------------------------
INSERT INTO `t_system_account_role` VALUES ('2', '4', '3');
INSERT INTO `t_system_account_role` VALUES ('3', '6', '3');
INSERT INTO `t_system_account_role` VALUES ('7', '3', '3');
INSERT INTO `t_system_account_role` VALUES ('8', '3', '4');

-- ----------------------------
-- Table structure for `t_system_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `parent_menu_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `menu_code` varchar(50) DEFAULT NULL COMMENT '菜单编码',
  `menu_url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `page_id` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `admin_flag` varchar(10) DEFAULT NULL COMMENT '可见',
  PRIMARY KEY (`menu_id`),
  KEY `index_parent_ment` (`parent_menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_system_menu
-- ----------------------------
INSERT INTO `t_system_menu` VALUES ('1', '系统管理', '0', '1', null, 'images/system_b.png', 'system', '1');
INSERT INTO `t_system_menu` VALUES ('2', '菜单管理', '1', '2', 'menu/menuList.do', null, 'menuManage', '1');
INSERT INTO `t_system_menu` VALUES ('3', '角色管理', '1', '3', 'role/roleList.do', null, 'roleManage', '1');
INSERT INTO `t_system_menu` VALUES ('4', '订单管理', '0', '4', null, 'images/report_b.png', 'order', '1');
INSERT INTO `t_system_menu` VALUES ('5', '充值订单', '4', '5', 'order/orderList.do', null, 'orderManage', '1');
INSERT INTO `t_system_menu` VALUES ('6', '定时任务', '0', '6', null, 'images/system_b.png', 'job', '1');
INSERT INTO `t_system_menu` VALUES ('7', '定时任务管理', '6', '7', 'task/taskList.do', null, 'jobManage', '1');
INSERT INTO `t_system_menu` VALUES ('8', '用户管理', '1', '4', 'user/userList.do', null, 'userManage', '1');

-- ----------------------------
-- Table structure for `t_system_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_permission`;
CREATE TABLE `t_system_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_name` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `permi_desc` varchar(50) DEFAULT NULL COMMENT '权限描述',
  `permi_value` varchar(50) DEFAULT NULL COMMENT '权限标示',
  `sort_order` int(11) DEFAULT NULL COMMENT '权限顺序',
  `page_id` varchar(50) DEFAULT NULL COMMENT 't_system_menu.page_id关联',
  `request_url` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_system_permission
-- ----------------------------
INSERT INTO `t_system_permission` VALUES ('1', '菜单管理', '新增', 'menuManage:add', '1', 'menuManage', '');
INSERT INTO `t_system_permission` VALUES ('2', '菜单管理', '编辑', 'menuManage:edit', '2', 'menuManage', '');
INSERT INTO `t_system_permission` VALUES ('3', '菜单管理', '删除', 'menuManage:del', '3', 'menuManage', '');
INSERT INTO `t_system_permission` VALUES ('4', '角色管理', '新增', 'roleManage:add', '1', 'roleManage', '');
INSERT INTO `t_system_permission` VALUES ('5', '角色管理', '修改', 'roleManage:edit', '2', 'roleManage', '');
INSERT INTO `t_system_permission` VALUES ('6', '角色管理', '删除', 'roleManage:del', '3', 'roleManage', '');
INSERT INTO `t_system_permission` VALUES ('7', '用户管理', '新增', 'userManage:add', '1', 'userManage', '');
INSERT INTO `t_system_permission` VALUES ('8', '用户管理', '修改', 'userManage:edit', '2', 'userManage', '');
INSERT INTO `t_system_permission` VALUES ('9', '用户管理', '删除', 'userManage:del', '3', 'userManage', '');

-- ----------------------------
-- Table structure for `t_system_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role` VALUES ('3', '销售', 'luolong');
INSERT INTO `t_system_role` VALUES ('4', '运营一部', 'xiaolili');

-- ----------------------------
-- Table structure for `t_system_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_menu`;
CREATE TABLE `t_system_role_menu` (
  `role_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='角色与菜单关联表';

-- ----------------------------
-- Records of t_system_role_menu
-- ----------------------------
INSERT INTO `t_system_role_menu` VALUES ('10', '3', '1');
INSERT INTO `t_system_role_menu` VALUES ('11', '3', '2');
INSERT INTO `t_system_role_menu` VALUES ('12', '3', '3');
INSERT INTO `t_system_role_menu` VALUES ('13', '3', '8');
INSERT INTO `t_system_role_menu` VALUES ('14', '3', '4');
INSERT INTO `t_system_role_menu` VALUES ('15', '3', '5');
INSERT INTO `t_system_role_menu` VALUES ('22', '4', '1');
INSERT INTO `t_system_role_menu` VALUES ('23', '4', '2');
INSERT INTO `t_system_role_menu` VALUES ('24', '4', '3');
INSERT INTO `t_system_role_menu` VALUES ('25', '4', '8');
INSERT INTO `t_system_role_menu` VALUES ('26', '4', '4');
INSERT INTO `t_system_role_menu` VALUES ('27', '4', '5');
INSERT INTO `t_system_role_menu` VALUES ('28', '4', '6');
INSERT INTO `t_system_role_menu` VALUES ('29', '4', '7');

-- ----------------------------
-- Table structure for `t_system_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_permission`;
CREATE TABLE `t_system_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='角色与权限关联表';

-- ----------------------------
-- Records of t_system_role_permission
-- ----------------------------
INSERT INTO `t_system_role_permission` VALUES ('9', '3', 'menuManage:add');
INSERT INTO `t_system_role_permission` VALUES ('10', '3', 'menuManage:edit');
INSERT INTO `t_system_role_permission` VALUES ('11', '3', 'menuManage:del');
INSERT INTO `t_system_role_permission` VALUES ('12', '3', 'roleManage:add');
INSERT INTO `t_system_role_permission` VALUES ('13', '3', 'roleManage:edit');
INSERT INTO `t_system_role_permission` VALUES ('14', '3', 'roleManage:del');
INSERT INTO `t_system_role_permission` VALUES ('15', '3', 'userManage:add');
INSERT INTO `t_system_role_permission` VALUES ('16', '3', 'userManage:edit');
INSERT INTO `t_system_role_permission` VALUES ('17', '3', 'userManage:del');
INSERT INTO `t_system_role_permission` VALUES ('27', '4', 'menuManage:add');
INSERT INTO `t_system_role_permission` VALUES ('28', '4', 'menuManage:edit');
INSERT INTO `t_system_role_permission` VALUES ('29', '4', 'menuManage:del');
INSERT INTO `t_system_role_permission` VALUES ('30', '4', 'roleManage:add');
INSERT INTO `t_system_role_permission` VALUES ('31', '4', 'roleManage:edit');
INSERT INTO `t_system_role_permission` VALUES ('32', '4', 'roleManage:del');
INSERT INTO `t_system_role_permission` VALUES ('33', '4', 'userManage:add');
INSERT INTO `t_system_role_permission` VALUES ('34', '4', 'userManage:edit');
INSERT INTO `t_system_role_permission` VALUES ('35', '4', 'userManage:del');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `type` char(1) DEFAULT NULL COMMENT '用户类型:1管理 2.客户 3.销售',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'luolong', '123456', '玉面小飞龙', '13560729471', '2018-10-16 14:39:03', '1');
INSERT INTO `t_user` VALUES ('3', 'xiaolili', '123456', '小丽丽', '13510432088', '2018-10-17 10:45:18', '1');
INSERT INTO `t_user` VALUES ('4', 'xiaohong', '123456', '小红', '15899968888', '2018-10-16 16:32:10', '3');
INSERT INTO `t_user` VALUES ('6', 'll', '123456', 'll', '13510432078', '2018-10-16 16:38:13', '3');
