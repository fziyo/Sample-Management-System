


DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
                         `id` int unsigned NOT NULL AUTO_INCREMENT,
                         `emp_no` varchar(32) NOT NULL COMMENT 'Login Account',
                         `pwd` varchar(64) NOT NULL COMMENT 'Login Password',
                         `name` varchar(32) NOT NULL COMMENT 'Real Name',
                         `tel` varchar(18) DEFAULT NULL COMMENT 'Tel',
                         `email` varchar(64)  NOT NULL COMMENT 'Email',
                         `gender` tinyint  NOT NULL DEFAULT 0 comment '0 Unknown 1 Male 2 Female',
                         `account_non_expired` tinyint NOT NULL DEFAULT 1 COMMENT '0 Expired 1 Normal',
                         `credentials_non_expired` tinyint NOT NULL DEFAULT 1 COMMENT '0 Expired 1 Normal',
                         `account_non_locked` tinyint NOT NULL DEFAULT 1 COMMENT '0 Locked 1 Normal',
                         `account_enabled` tinyint NOT NULL DEFAULT 1 COMMENT '0 Disabled 1 Enabled',
                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create Time',
                         `create_by` int DEFAULT NULL COMMENT 'Created By',
                         `edit_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Edite Time',
                         `edit_by` int DEFAULT NULL COMMENT 'Edited By',
                         `last_login_time` datetime DEFAULT NULL COMMENT 'Last Login Time',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `emp_no`(`emp_no`),
                         UNIQUE KEY `tel`(`tel`),
                         UNIQUE KEY `email`(`email`)
) ENGINE=InnoDB
    AUTO_INCREMENT = 100000
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Employee Table';



DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `role_code` varchar(30) NOT NULL COMMENT 'Role Code (eg. ADMIN)',
                          `role_name` varchar(30) NOT NULL COMMENT 'Role Name (eg. Administrator)',
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                          `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Role Table';



DROP TABLE IF EXISTS `t_emp_role`;
CREATE TABLE `t_emp_role` (
                              `emp_id` int unsigned NOT NULL,
                              `role_id` int unsigned NOT NULL,
                              PRIMARY KEY (`emp_id`, `role_id`),
                              INDEX `idx_role_id` (`role_id`),
                              CONSTRAINT `fk_emp_role_emp` FOREIGN KEY (`emp_id`) REFERENCES `t_emp` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `fk_emp_role_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Employee Role Mapping';



DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
                                `id` int unsigned NOT NULL AUTO_INCREMENT,
                                `name` varchar(30) NOT NULL COMMENT 'Permission Name',
                                `code` varchar(30) NOT NULL COMMENT 'Permission Code (e.g. asset:list)',
                                `url` varchar(30) DEFAULT NULL,
                                `type` tinyint NOT NULL COMMENT '1=menu 2=button 3=api',
                                `parent_id` int unsigned DEFAULT NULL,
                                `order_no` int DEFAULT 0 COMMENT 'Order',
                                `icon` varchar(100) DEFAULT NULL COMMENT 'Menu Icon',
                                `component` varchar(50) DEFAULT NULL COMMENT 'Frontend Component',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_code` (`code`),
                                INDEX `idx_parent_id` (`parent_id`)

) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Permission Table';



DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
                                     `role_id` int unsigned NOT NULL,
                                     `permission_id` int unsigned NOT NULL,
                                     PRIMARY KEY (`role_id`, `permission_id`),
                                     INDEX `idx_permission_id` (`permission_id`),
                                     CONSTRAINT `fk_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                     CONSTRAINT `fk_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Role Permission Mapping';



DROP TABLE IF EXISTS `t_team`;
CREATE TABLE `t_team` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `team_name` varchar(30) NOT NULL COMMENT 'Team Name',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Create Time',
                          `create_by` int unsigned DEFAULT NULL COMMENT 'Created By',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update Time',
                          `update_by` int unsigned DEFAULT NULL COMMENT 'Updated By',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uk_team_name` (`team_name`)
) ENGINE=InnoDB
    AUTO_INCREMENT=1001
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Team Table';



DROP TABLE IF EXISTS `t_emp_team`;
CREATE TABLE `t_emp_team`  (
                               `emp_id` int unsigned NOT NULL,
                               `team_id` int unsigned NOT NULL,
                               PRIMARY KEY (`emp_id`),
                               INDEX `idx_team_id`(`team_id`),
                               CONSTRAINT `fk_emp_team_emp` FOREIGN KEY (`emp_id`) REFERENCES `t_emp` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                               CONSTRAINT `fk_emp_team_team` FOREIGN KEY (`team_id`) REFERENCES `t_team` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Emp Team Mapping';



DROP TABLE IF EXISTS `t_asset_category`;
CREATE TABLE `t_asset_category` (
                                    `id` int unsigned NOT NULL AUTO_INCREMENT,
                                    `name` varchar(50) NOT NULL COMMENT 'Category Name',
                                    `code` varchar(30) DEFAULT NULL COMMENT 'Category Code (optional)',
                                    `status` tinyint NOT NULL DEFAULT 1 COMMENT '0=Disabled 1=Enabled',
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
                                    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_category_name` (`name`)

) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Asset Category Table';



DROP TABLE IF EXISTS `t_asset`;
CREATE TABLE `t_asset` (
                           `id` int unsigned NOT NULL AUTO_INCREMENT,
                           `code` varchar(30) NOT NULL COMMENT 'Asset Code (e.g. 00RY)',
                           `name` varchar(50) NOT NULL COMMENT 'Asset Name',
                           `model` varchar(50) NOT NULL COMMENT 'Model (e.g. ELA-B19)',

                           `category_id` int unsigned NOT NULL COMMENT 'Asset Category ID',
                           `team_id` int unsigned NOT NULL COMMENT 'Owning Team ID',
                           `owner_id` int unsigned NOT NULL COMMENT 'Responsible Employee ID',

                           `sn` varchar(50) unique NOT NULL COMMENT 'Serial Number',
                           `mac_addr` varchar(50) DEFAULT NULL COMMENT 'MAC Address',
                           `release_year`  year DEFAULT NULL,

                           `asset_condition` tinyint NOT NULL DEFAULT 0 COMMENT '0=Good 1=Damaged',
                           `commercial_status` tinyint NOT NULL DEFAULT 0 COMMENT '0=Commercial 1=Non-commercial',
                           `is_active` tinyint NOT NULL DEFAULT 1 COMMENT '0=Inactive 1=Active',

                           `status` tinyint NOT NULL DEFAULT 0 COMMENT '0=Stock 1=In Use',
                           `current_user_id` int unsigned DEFAULT NULL COMMENT 'Current User',

                           `location` varchar(255) DEFAULT NULL,
                           `description` text,

                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                           PRIMARY KEY (`id`),

                           UNIQUE KEY `uk_code` (`code`),
                           UNIQUE KEY `uk_sn` (`sn`),
                           UNIQUE KEY `uk_mac` (`mac_addr`),

                           INDEX `idx_category_id` (`category_id`),
                           INDEX `idx_team_id` (`team_id`),
                           INDEX `idx_owner_id` (`owner_id`),
                           INDEX `idx_current_user_id` (`current_user_id`),

                           CONSTRAINT `fk_asset_category`
                               FOREIGN KEY (`category_id`)
                                   REFERENCES `t_asset_category` (`id`)
                                   ON DELETE RESTRICT,

                           CONSTRAINT `fk_asset_team`
                               FOREIGN KEY (`team_id`)
                                   REFERENCES `t_team` (`id`)
                                   ON DELETE RESTRICT,

                           CONSTRAINT `fk_asset_owner`
                               FOREIGN KEY (`owner_id`)
                                   REFERENCES `t_emp` (`id`)
                                   ON DELETE RESTRICT,

                           CONSTRAINT `fk_asset_user`
                               FOREIGN KEY (`current_user_id`)
                                   REFERENCES `t_emp` (`id`)
                                   ON DELETE SET NULL
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Asset Table';



DROP TABLE IF EXISTS `t_borrow_request`;
create table `t_borrow_request` (
                                    `id` int unsigned AUTO_INCREMENT,
                                    `asset_id` int unsigned NOT NULL COMMENT 'Asset ID',
                                    `borrower_id` int unsigned NOT NULL COMMENT 'Borrower (emp_id)',
                                    `status` tinyint NOT NULL DEFAULT 0 COMMENT
                                        '0=PENDING  1=CANCELLED 2=APPROVED  3=REJECTED 4=IN_USE 5=RETURN_PENDING
    6=FINISHED',

--  approver approve request
                                    `request_approver_id` int unsigned DEFAULT NULL COMMENT 'Request Approver',
                                    `request_approve_time` datetime DEFAULT NULL,
--  user confirm receive
                                    `borrow_start_time` datetime DEFAULT NULL,
                                    `return_request_time` datetime DEFAULT NULL,
-- approver approve return
                                    `return_approver_id` int unsigned DEFAULT NULL COMMENT 'Return Approver',
                                    `return_approve_time` datetime DEFAULT NULL,
-- request created/ updated
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP comment 'Time of Borrow Request',
                                    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                    PRIMARY KEY (`id`),

                                    INDEX `idx_asset_id` (`asset_id`),
                                    INDEX `idx_borrower_id` (`borrower_id`),
                                    INDEX `idx_status` (`status`),

                                    CONSTRAINT `fk_borrow_asset`
                                        FOREIGN KEY (`asset_id`) REFERENCES `t_asset` (`id`)
                                            ON DELETE RESTRICT,

                                    CONSTRAINT `fk_borrow_user`
                                        FOREIGN KEY (`borrower_id`) REFERENCES `t_emp` (`id`)
                                            ON DELETE RESTRICT,

                                    CONSTRAINT `fk_borrow_request_approver`
                                        FOREIGN KEY (`request_approver_id`) REFERENCES `t_emp` (`id`)
                                            ON DELETE SET NULL,

                                    CONSTRAINT `fk_borrow_return_approver`
                                        FOREIGN KEY (`return_approver_id`) REFERENCES `t_emp` (`id`)
                                            ON DELETE SET NULL
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Borrow Request Table';

-- user request borrow(pending/cancelled)：create_time > approver confirm(approved/rejected)：request_approver_id + request_approve_time > user confirm receive device(in_use)：borrow_start_time >
-- user request return(return_pending):return_request_time > approver confirm return(finished)：return_approver_id + return_approve_time

# permission
