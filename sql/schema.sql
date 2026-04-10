DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
                       `id` int(11) NOT NULL AUTO_INCREMENT,
                       `emp_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Login Account',
                       `pwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Login Password',
                       `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Real Name',
                       `tel` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Tel',
                       `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Email',
                       `gender` tinyint unsigned NOT NULL DEFAULT 0 comment '0 Unknown 1 Male 2 Female',
#                        `team_id` int unsigned NOT NULL COMMENT 'Business Unit',
                       `account_non_expired` int(11) NULL DEFAULT NULL COMMENT '0 Expired 1 Normal',
                       `credentials_non_expired` int(11) NULL DEFAULT NULL COMMENT '0 Expired 1 Normal',
                       `account_non_locked` int(11) NULL DEFAULT NULL COMMENT '0 Locked 1 Normal',
                       `account_enabled` int(11) NULL DEFAULT NULL COMMENT '0 Disabled 1 Enabled',
                       `create_time` datetime NULL DEFAULT NULL COMMENT 'Create Time',
                       `create_by` int(11) NULL DEFAULT NULL COMMENT 'Created By',
                       `edit_time` datetime NULL DEFAULT NULL COMMENT 'Edite Time',
                       `edit_by` int(11) NULL DEFAULT NULL COMMENT 'Edited By',
                       `last_login_time` datetime NULL DEFAULT NULL COMMENT 'Last Login Time',
                       PRIMARY KEY (`id`) USING BTREE,
                       UNIQUE INDEX `emp_no`(`emp_no`) USING BTREE,
                       UNIQUE INDEX `tel`(`tel`) USING BTREE,
                       UNIQUE INDEX `email`(`email`) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT = 50 CHARSET = UTF8 COLLATE = utf8mb3_general_ci COMMENT = 'Employee Table' ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
                      `id` int(11) NOT NULL AUTO_INCREMENT,
                      `role` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                      `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Role Table' ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
                                 `id` int(11) NOT NULL AUTO_INCREMENT,
                                 `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                 `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                 `url` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                 `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                 `parent_id` int(11) NULL DEFAULT NULL,
                                 `order_no` int(11) NULL DEFAULT NULL,
                                 `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Memu Icon',
                                 `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Component Name',
                                 PRIMARY KEY (`id`) USING BTREE

) ENGINE = InnoDB AUTO_INCREMENT = 1113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Permission Table' ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
                                    `id` int(11) NOT NULL AUTO_INCREMENT,
                                    `role_id` int(11) NULL DEFAULT NULL,
                                    `permission_id` int(11) NULL DEFAULT NULL,
                                     PRIMARY KEY (`id`) USING BTREE,
                                     INDEX `t_role_permission_ibfk_1`(`role_id`) USING BTREE,
                                     INDEX `t_role_permission_ibfk_2`(`permission_id`) USING BTREE,
                                     CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                     CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE

) ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Role Permission Table' ROW_FORMAT = DYNAMIC;



DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
                               id int unsigned primary key auto_increment,
                               name varchar(50) unique not null ,
                               create_time datetime default current_timestamp,
                               update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table asset (
                               id int unsigned primary key auto_increment,
                               asset_code VARCHAR(100) unique not null comment '00RY',
                               name varchar(50) not null comment 'device name',
                               model varchar(50) not null comment 'ELA-B19',

                               category_id int unsigned not null comment 'asset_category pk',
                               team_id int unsigned not null comment 'unit owned by',
                               owner_id int unsigned not null comment 'emp pk responsible person',

                               sn VARCHAR(50) unique not null comment 'serial number',
                               mac_addr VARCHAR(50) unique ,

                               release_year year,

                               asset_condition tinyint unsigned not null default 0 comment '0=Good 1=Damaged',
                               commercial_status tinyint unsigned not null comment '0=Commercial 1=Non-commercial',
                               is_active BOOLEAN DEFAULT TRUE comment 'Yes No',

                               status tinyint unsigned not null DEFAULT 0 comment '0=Stock 1=In Use',
                               current_user_id int unsigned comment 'emp pk',

                               location VARCHAR(255),
                               description TEXT,

                               create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                               update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table asset_category (
                                 id int unsigned PRIMARY KEY AUTO_INCREMENT,
                                 name VARCHAR(50) UNIQUE NOT NULL comment 'wearable phone tablet',
                                 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

) comment 'wearable phone tablet';

create table borrow_request (
                                id int unsigned PRIMARY KEY AUTO_INCREMENT,
                                asset_id int unsigned not null comment 'asset pk',
                                borrower_id int unsigned not null comment 'emp pk',
                                status tinyint unsigned not null DEFAULT 0 comment
                                    '0=PENDING  1=CANCELLED 2=APPROVED  3=REJECTED 4=IN_USE 5=RETURN_PENDING
6=FINISHED',

--  approver approve request
                                request_handler_id int unsigned comment 'emp pk',
                                request_approve_time DATETIME comment 'time request is approved',
--  user confirm receive
                                borrow_start_time DATETIME comment 'time borrow is confirmed by user',
                                return_request_time DATETIME comment 'time return is requested',
-- approver approve return
                                return_handler_id int unsigned,
                                return_approve_time DATETIME comment 'admin approved return',
-- request created/ updated
                                create_time DATETIME DEFAULT CURRENT_TIMESTAMP comment 'time borrow is requested',
                                update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- user request borrow(pending/cancelled)：create_time > approver confirm(approved/rejected)：request_approver_id + request_approve_time > user confirm receive device(in_use)：borrow_start_time >
-- user request return(return_pending):return_request_time > approver confirm return(finished)：return_approver_id + return_approve_time

# permission
