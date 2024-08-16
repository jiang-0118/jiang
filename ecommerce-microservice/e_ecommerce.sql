/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : e_ecommerce

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 16/08/2024 10:06:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_ecommerce_address
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_address`;
CREATE TABLE `t_ecommerce_address`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户 id',
  `username` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `phone` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '电话号码',
  `province` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '省',
  `city` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '市',
  `address_detail` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '详细地址',
  `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_ecommerce_address
-- ----------------------------
INSERT INTO `t_ecommerce_address` VALUES (2, 1, 'weida', '18627967620', '北京市', '北京市', '丰台区', '2022-12-01 07:04:01', '2022-12-01 07:04:01');
INSERT INTO `t_ecommerce_address` VALUES (3, 1, '张三', '12332122322', '湖北', '武汉', '金融刚刚', '2023-02-02 03:28:23', '2023-02-02 03:28:23');
INSERT INTO `t_ecommerce_address` VALUES (4, 1, '张三', '12332122322', '湖北', '武汉', '光谷', '2023-02-02 03:29:02', '2023-02-02 03:29:02');
INSERT INTO `t_ecommerce_address` VALUES (5, 1, '张三', '12332122322', '湖北', '武汉', '江汉路', '2023-02-02 03:29:02', '2023-02-02 03:29:02');
INSERT INTO `t_ecommerce_address` VALUES (10, 10, 'weida', '18627967620', '湖北省', '武汉市', '硚口区', '2022-12-01 07:04:01', '2022-12-01 07:04:01');
INSERT INTO `t_ecommerce_address` VALUES (13, 1, '李四', '12332122322', '湖北', '武汉', '光谷', '2023-04-18 03:30:43', '2023-04-18 03:30:43');
INSERT INTO `t_ecommerce_address` VALUES (14, 1, '李四', '12332122322', '湖北', '武汉', '江汉路', '2023-04-18 03:30:43', '2023-04-18 03:30:43');
INSERT INTO `t_ecommerce_address` VALUES (15, 1, 'zhangsan', '13500982314', '湖北省', '武汉市', '金融刚B27 9楼07室', '2023-06-07 02:45:22', '2023-06-07 02:45:22');
INSERT INTO `t_ecommerce_address` VALUES (16, 1, 'zhangsan', '13500982314', '湖北省', '武汉市', '金融刚A4 3楼05室', '2023-06-07 02:45:22', '2023-06-07 02:45:22');

-- ----------------------------
-- Table structure for t_ecommerce_balance
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_balance`;
CREATE TABLE `t_ecommerce_balance`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户 id',
  `balance` bigint NOT NULL DEFAULT 0 COMMENT '账户余额',
  `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id_key`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户账户余额表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_ecommerce_balance
-- ----------------------------
INSERT INTO `t_ecommerce_balance` VALUES (10, 10, 18996650, '2022-08-16 13:08:41', '2023-08-22 14:39:32');
INSERT INTO `t_ecommerce_balance` VALUES (11, 1, 4875, '2022-12-01 06:37:40', '2024-03-05 16:21:59');
INSERT INTO `t_ecommerce_balance` VALUES (12, 8, 1000, '2023-02-02 01:26:40', '2023-03-15 01:44:08');
INSERT INTO `t_ecommerce_balance` VALUES (13, 4, 5, '2023-04-18 03:35:54', '2023-04-21 02:21:58');

-- ----------------------------
-- Table structure for t_ecommerce_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_goods`;
CREATE TABLE `t_ecommerce_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `goods_category` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品类别',
  `brand_category` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '品牌分类',
  `goods_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_pic` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品图片',
  `goods_description` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品描述信息',
  `goods_status` int NOT NULL DEFAULT 0 COMMENT '商品状态',
  `price` int NOT NULL DEFAULT 0 COMMENT '商品价格',
  `supply` bigint NOT NULL DEFAULT 0 COMMENT '总供应量',
  `inventory` bigint NOT NULL DEFAULT 0 COMMENT '库存',
  `goods_property` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '商品属性',
  `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `goods_category_brand_name`(`goods_category` ASC, `brand_category` ASC, `goods_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88888 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_ecommerce_goods
-- ----------------------------
INSERT INTO `t_ecommerce_goods` VALUES (3, '10001', '20001', 'iphone 23', '', '苹果手机', 1001, 10, 2000000, 1000, '{\"color\":\"绿色\",\"material\":\"金属机身\",\"pattern\":\"纯色\",\"size\":\"12cm * 6.5cm\"}', '2024-04-09 10:23:02', '2024-04-09 10:23:02');
INSERT INTO `t_ecommerce_goods` VALUES (4, '10001', '20001', 'iphone 24', '', '苹果手机', 1001, 5, 2000000, 1000, '{\"color\":\"绿色\",\"material\":\"金属机身\",\"pattern\":\"纯色\",\"size\":\"12cm * 6.5cm\"}', '2024-04-09 10:23:02', '2024-04-09 10:23:02');
INSERT INTO `t_ecommerce_goods` VALUES (5, '10002', '20002', '小米 15', '', '小米手机', 1001, 5000, 2000000, 100, '{\"color\":\"绿色\",\"material\":\"金属机身\",\"pattern\":\"纯色\",\"size\":\"12cm * 6.5cm\"}', '2024-05-22 14:18:35', '2024-05-22 14:18:35');

-- ----------------------------
-- Table structure for t_ecommerce_order
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_order`;
CREATE TABLE `t_ecommerce_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint NOT NULL DEFAULT 0 COMMENT '用户 id',
  `address_id` bigint NOT NULL DEFAULT 0 COMMENT '用户地址记录 id',
  `order_detail` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '订单详情(json 存储, goodsId, count)',
  `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_ecommerce_order
-- ----------------------------
INSERT INTO `t_ecommerce_order` VALUES (2, 1, 0, '[{\"count\":20,\"goodsId\":13},{\"count\":20,\"goodsId\":14}]', '2023-11-06 15:06:39', '2023-11-06 15:06:39');
INSERT INTO `t_ecommerce_order` VALUES (6, 1, 0, '[{\"count\":10,\"goodsId\":13},{\"count\":5,\"goodsId\":14}]', '2024-01-09 14:49:41', '2024-01-09 14:49:41');
INSERT INTO `t_ecommerce_order` VALUES (7, 1, 0, '[{\"count\":10,\"goodsId\":13},{\"count\":5,\"goodsId\":14}]', '2024-01-09 16:35:13', '2024-01-09 16:35:13');
INSERT INTO `t_ecommerce_order` VALUES (8, 1, 1, '[{\"count\":10,\"goodsId\":13},{\"count\":10,\"goodsId\":14}]', '2024-03-05 10:43:44', '2024-03-05 10:43:44');
INSERT INTO `t_ecommerce_order` VALUES (9, 1, 0, '[{\"count\":5,\"goodsId\":3},{\"count\":2,\"goodsId\":4}]', '2024-04-09 11:39:16', '2024-04-09 11:39:16');
INSERT INTO `t_ecommerce_order` VALUES (10, 1, 0, '[{\"count\":5,\"goodsId\":3},{\"count\":2,\"goodsId\":4}]', '2024-04-09 11:39:40', '2024-04-09 11:39:40');
INSERT INTO `t_ecommerce_order` VALUES (11, 1, 0, '[{\"count\":1,\"goodsId\":3},{\"count\":1,\"goodsId\":4}]', '2024-05-23 09:31:00', '2024-05-23 09:31:00');
INSERT INTO `t_ecommerce_order` VALUES (12, 1, 0, '[{\"count\":10,\"goodsId\":3},{\"count\":5,\"goodsId\":4}]', '2024-08-13 17:08:38', '2024-08-13 17:08:38');

-- ----------------------------
-- Table structure for t_ecommerce_role
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_role`;
CREATE TABLE `t_ecommerce_role`  (
  `roleid` int NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  `right` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限',
  PRIMARY KEY (`roleid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ecommerce_role
-- ----------------------------
INSERT INTO `t_ecommerce_role` VALUES (1, 'superadmin', 'superadmin');
INSERT INTO `t_ecommerce_role` VALUES (2, 'admin', 'admin');
INSERT INTO `t_ecommerce_role` VALUES (3, 'user', 'user');

-- ----------------------------
-- Table structure for t_ecommerce_user
-- ----------------------------
DROP TABLE IF EXISTS `t_ecommerce_user`;
CREATE TABLE `t_ecommerce_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT 'MD5 加密之后的密码',
  `extra_info` varchar(1024) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '' COMMENT '额外的信息',
  `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1021 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_ecommerce_user
-- ----------------------------
INSERT INTO `t_ecommerce_user` VALUES (1, '18627967620@163.com', '202cb962ac59075b964b07152d234b70', '{}', '2022-11-29 01:05:10', '2022-11-29 01:05:10');
INSERT INTO `t_ecommerce_user` VALUES (3, '123456@qq.com', '202cb962ac59075b964b07152d234b70', '{}', '2022-11-29 01:43:45', '2022-11-29 01:43:45');
INSERT INTO `t_ecommerce_user` VALUES (7, '123456@163.com', '202cb962ac59075b964b07152d234b70', '{}', '2022-11-30 02:42:27', '2022-11-30 02:42:27');
INSERT INTO `t_ecommerce_user` VALUES (8, '123@qq.com', '202cb962ac59075b964b07152d234b70', '{}', '2023-01-30 06:13:10', '2023-01-30 06:13:10');
INSERT INTO `t_ecommerce_user` VALUES (10, '12306@163.com', '202cb962ac59075b964b07152d234b70', '{}', '2023-06-06 03:48:11', '2023-06-06 03:48:11');
INSERT INTO `t_ecommerce_user` VALUES (1018, 'yongkun', '202cb962ac59075b964b07152d234b70', '{}', '2024-06-26 18:10:26', '2024-06-26 18:10:26');
INSERT INTO `t_ecommerce_user` VALUES (1019, 'yanghua', '202cb962ac59075b964b07152d234b70', '{}', '2024-06-26 19:57:27', '2024-06-26 19:57:27');
INSERT INTO `t_ecommerce_user` VALUES (1020, 'jack', '202cb962ac59075b964b07152d234b70', '{}', '2024-06-28 16:26:30', '2024-06-28 16:26:30');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid` ASC, `branch_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `userId` int NOT NULL COMMENT '用户id',
  `roleId` int NOT NULL COMMENT '权限id',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (3, 3);

-- ----------------------------
-- Procedure structure for test_insert
-- ----------------------------
DROP PROCEDURE IF EXISTS `test_insert`;
delimiter ;;
CREATE PROCEDURE `test_insert`()
begin
DECLARE i int default 10;
while i<1011
DO
insert into t_ecommerce_user(username,password,extra_info,create_time,update_time)
values(concat('test',i),'202cb962ac59075b964b07152d234b70',
'{}',now(),now());
set i = i+1;
end while;
commit;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
