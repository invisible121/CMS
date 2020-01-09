/*

 Target Server Type    : MySQL
 Target Server Version : 50557
 File Encoding         : 65001

 Date: 10/01/2020 00:06:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attachment
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `con_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`con_id`,`id`),
  KEY `FK_attach_to` (`id`),
  CONSTRAINT `FK_attach_file` FOREIGN KEY (`con_id`) REFERENCES `contract_attachment` (`id`),
  CONSTRAINT `FK_attach_to` FOREIGN KEY (`id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for authorization_relation
-- ----------------------------
DROP TABLE IF EXISTS `authorization_relation`;
CREATE TABLE `authorization_relation` (
  `fun_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`fun_id`,`id`),
  KEY `FK_authorization_relation_role` (`id`),
  CONSTRAINT `FK_authorization_relation_func` FOREIGN KEY (`fun_id`) REFERENCES `function_entity` (`id`),
  CONSTRAINT `FK_authorization_relation_role` FOREIGN KEY (`id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` int(11) DEFAULT NULL,
  `num` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `customer` varchar(40) DEFAULT NULL,
  `content` text NOT NULL,
  `beginTime` date NOT NULL,
  `endTime` date NOT NULL,
  `del` int(11) NOT NULL,
  `CommitComment` varchar(255) DEFAULT NULL COMMENT '定稿意见',
  `EditComment` varchar(255) DEFAULT NULL COMMENT '修改意见',
  PRIMARY KEY (`id`),
  KEY `FK_contract_produce` (`cus_id`),
  KEY `contract_user_entity_id_fk` (`user_id`),
  CONSTRAINT `contract_user_entity_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user_entity` (`id`),
  CONSTRAINT `FK_contract_produce` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contract_attachment
-- ----------------------------
DROP TABLE IF EXISTS `contract_attachment`;
CREATE TABLE `contract_attachment` (
  `id` int(11) NOT NULL,
  `con_id` int(11) NOT NULL,
  `fileName` varchar(40) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `uploadTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contract_process
-- ----------------------------
DROP TABLE IF EXISTS `contract_process`;
CREATE TABLE `contract_process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(40) DEFAULT NULL,
  `del` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract_process
-- ----------------------------
BEGIN;
INSERT INTO `contract_process` VALUES (1, '起草', 0);
INSERT INTO `contract_process` VALUES (2, '分配', 0);
INSERT INTO `contract_process` VALUES (3, '已会签', 0);
INSERT INTO `contract_process` VALUES (4, '已定稿', 0);
INSERT INTO `contract_process` VALUES (5, '已审批', 0);
INSERT INTO `contract_process` VALUES (6, '已签订', 0);
COMMIT;

-- ----------------------------
-- Table structure for contract_state
-- ----------------------------
DROP TABLE IF EXISTS `contract_state`;
CREATE TABLE `contract_state` (
  `con_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`con_id`),
  KEY `contract_state_contract_process_id_fk` (`id`),
  CONSTRAINT `contract_state_contract_id_fk` FOREIGN KEY (`con_id`) REFERENCES `contract` (`id`),
  CONSTRAINT `contract_state_contract_process_id_fk` FOREIGN KEY (`id`) REFERENCES `contract_process` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `bank` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `del` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for forMemory
-- ----------------------------
DROP TABLE IF EXISTS `forMemory`;
CREATE TABLE `forMemory` (
  `code` int(11) DEFAULT NULL,
  `permissions` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for function_entity
-- ----------------------------
DROP TABLE IF EXISTS `function_entity`;
CREATE TABLE `function_entity` (
  `id` int(11) NOT NULL,
  `num` varchar(10) NOT NULL,
  `name` varchar(40) NOT NULL,
  `URL` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `del` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for herotable
-- ----------------------------
DROP TABLE IF EXISTS `herotable`;
CREATE TABLE `herotable` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of herotable
-- ----------------------------
BEGIN;
INSERT INTO `herotable` VALUES (1, 'qyxtql');
INSERT INTO `herotable` VALUES (2, 'dsytql');
COMMIT;

-- ----------------------------
-- Table structure for log_entity
-- ----------------------------
DROP TABLE IF EXISTS `log_entity`;
CREATE TABLE `log_entity` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `del` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL DEFAULT '0',
  `token` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `user_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `con_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `permissions` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rights
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `rol_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`rol_id`,`id`),
  KEY `FK_rights_user_entity` (`id`),
  CONSTRAINT `FK_rights_role` FOREIGN KEY (`rol_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_rights_user_entity` FOREIGN KEY (`id`) REFERENCES `user_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `del` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (0, '操作员', '操作员(普通用户)', 0);
INSERT INTO `role` VALUES (1, '管理员', '管理员', 0);
INSERT INTO `role` VALUES (2, 'root', '超级用户', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_entity
-- ----------------------------
DROP TABLE IF EXISTS `user_entity`;
CREATE TABLE `user_entity` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(40) NOT NULL,
  `del` int(11) NOT NULL,
  `permissions` int(11) NOT NULL DEFAULT '0' COMMENT '1 : 会签\n2 : 审批\n4 : 签订',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Triggers structure for table contract
-- ----------------------------
DROP TRIGGER IF EXISTS `contract_ai`;
delimiter ;;
CREATE TRIGGER `contract_ai` AFTER INSERT ON `contract` FOR EACH ROW begin
    DECLARE u_id INTEGER;
    DECLARE v_finished INTEGER DEFAULT 0;
    DECLARE u_set CURSOR FOR SELECT user_entity.id FROM user_entity;
    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET v_finished = 1;
    OPEN u_set;

    get_id: LOOP

        FETCH u_set INTO u_id;

        IF v_finished = 1 THEN
            LEAVE get_id;
        END IF;

        INSERT INTO permissions(user_id,con_id,permissions) VALUES(u_id,new.id,0);

    END LOOP get_id;

    CLOSE u_set;

end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user_entity
-- ----------------------------
DROP TRIGGER IF EXISTS `user_entity_ai`;
delimiter ;;
CREATE TRIGGER `user_entity_ai` AFTER INSERT ON `user_entity` FOR EACH ROW begin
    DECLARE v_finished INTEGER DEFAULT 0;
    DECLARE c_id INTEGER;
    DECLARE c_set CURSOR FOR SELECT contract.id FROM contract;
    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET v_finished = 1;
    OPEN c_set;

    get_id: LOOP

        FETCH c_set INTO c_id;

        IF v_finished = 1 THEN
            LEAVE get_id;
        END IF;

        INSERT INTO permissions(user_id,con_id,permissions) VALUES(new.id,c_id,0);

    END LOOP get_id;

    CLOSE c_set;

end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
