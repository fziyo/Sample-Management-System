


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                         `id` int unsigned NOT NULL AUTO_INCREMENT,
                         `username` varchar(32) NOT NULL COMMENT 'Login Account',
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
                         UNIQUE KEY `username`(`username`),
                         UNIQUE KEY `tel`(`tel`),
                         UNIQUE KEY `email`(`email`)
) ENGINE=InnoDB
    AUTO_INCREMENT = 100000
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='userloyee Table';


INSERT INTO t_user (id, username, pwd, name, tel, email, gender)
VALUES
    (100001, 'E100001', '$2a$10$7QJ8b8u3n9v7wYx3F7Y0QOePq9cM7F1YV3vWz7p9Zp3c1vXlYQz6K', 'Alice Johnson', '600100001', 'alice.johnson@company.com', 2),

    (100002, 'E100002', '$2a$10$7QJ8b8u3n9v7wYx3F7Y0QOePq9cM7F1YV3vWz7p9Zp3c1vXlYQz6K', 'Michael Smith', '600100002', 'michael.smith@company.com', 1),

    (100003, 'E100003', '$2a$10$7QJ8b8u3n9v7wYx3F7Y0QOePq9cM7F1YV3vWz7p9Zp3c1vXlYQz6K', 'David Brown', '600100003', 'david.brown@company.com', 1),

    (100004, 'E100004', '$2a$10$7QJ8b8u3n9v7wYx3F7Y0QOePq9cM7F1YV3vWz7p9Zp3c1vXlYQz6K', 'Emma Wilson', '600100004', 'emma.wilson@company.com', 2),

    (100005, 'E100005', '$2a$10$7QJ8b8u3n9v7wYx3F7Y0QOePq9cM7F1YV3vWz7p9Zp3c1vXlYQz6K', 'James Taylor', '600100005', 'james.taylor@company.com', 1);



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

INSERT INTO t_role (id, role_code, role_name) VALUES
                                                  (1, 'ADMIN', 'Administrator'),
                                                  (2, 'ASSET_MANAGER', 'Asset Manager'),
                                                  (3, 'USER', 'Normal User');



DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
                              `user_id` int unsigned NOT NULL,
                              `role_id` int unsigned NOT NULL,
                              PRIMARY KEY (`user_id`, `role_id`),
                              INDEX `idx_role_id` (`role_id`),
                              CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                              CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='User Role Mapping';




DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
                                `id` int unsigned NOT NULL AUTO_INCREMENT,
                                `name` varchar(30) NOT NULL COMMENT 'Permission Name',
                                `code` varchar(30) NOT NULL COMMENT 'Permission Code (e.g. asset:list)',
                                `url` varchar(30) DEFAULT NULL,
                                `type` tinyint NOT NULL COMMENT '1=menu 2=button',
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


-- =============================
-- Dashboard
-- =============================
INSERT INTO t_permission VALUES (1, 'Dashboard', 'dashboard', '/dashboard', 1, 0, 1, 'HomeFilled', 'DashboardView');

-- =============================
-- Asset Management
-- =============================
INSERT INTO t_permission VALUES (10, 'Asset Management', 'asset', NULL, 1, 0, 2, 'Monitor', NULL);
INSERT INTO t_permission VALUES (11, 'Asset', 'asset:menu', '/dashboard/asset', 1, 10, 1, 'Cpu', 'AssetView');

INSERT INTO t_permission VALUES (12, 'Asset List', 'asset:list', NULL, 2, 11, 1, NULL, NULL);
INSERT INTO t_permission VALUES (13, 'Create Asset', 'asset:add', NULL, 2, 11, 2, NULL, NULL);
INSERT INTO t_permission VALUES (14, 'Update Asset', 'asset:update', NULL, 2, 11, 3, NULL, NULL);
INSERT INTO t_permission VALUES (15, 'Delete Asset', 'asset:delete', NULL, 2, 11, 4, NULL, NULL);
INSERT INTO t_permission VALUES (16, 'View Asset', 'asset:view', NULL, 2, 11, 5, NULL, NULL);

-- =============================
-- Asset Category
-- =============================
INSERT INTO t_permission VALUES (20, 'Asset Category', 'asset-category', NULL, 1, 0, 3, 'Collection', NULL);
INSERT INTO t_permission VALUES (21, 'Category', 'asset-category:menu', '/dashboard/category', 1, 20, 1, 'Grid', 'CategoryView');

INSERT INTO t_permission VALUES (22, 'Category List', 'asset-category:list', NULL, 2, 21, 1, NULL, NULL);
INSERT INTO t_permission VALUES (23, 'Create Category', 'asset-category:add', NULL, 2, 21, 2, NULL, NULL);
INSERT INTO t_permission VALUES (24, 'Update Category', 'asset-category:update', NULL, 2, 21, 3, NULL, NULL);
INSERT INTO t_permission VALUES (25, 'Delete Category', 'asset-category:delete', NULL, 2, 21, 4, NULL, NULL);

-- =============================
-- Borrow Request
-- =============================
INSERT INTO t_permission VALUES (30, 'Borrow Request', 'borrow-request', NULL, 1, 0, 4, 'Document', NULL);
INSERT INTO t_permission VALUES (31, 'Request', 'borrow-request:menu', '/dashboard/borrow', 1, 30, 1, 'Tickets', 'BorrowView');

INSERT INTO t_permission VALUES (32, 'Request List', 'borrow-request:list', NULL, 2, 31, 1, NULL, NULL);
INSERT INTO t_permission VALUES (33, 'Create Request', 'borrow-request:create', NULL, 2, 31, 2, NULL, NULL);
INSERT INTO t_permission VALUES (34, 'Approve Request', 'borrow-request:approve', NULL, 2, 31, 3, NULL, NULL);
INSERT INTO t_permission VALUES (35, 'Reject Request', 'borrow-request:reject', NULL, 2, 31, 4, NULL, NULL);
INSERT INTO t_permission VALUES (36, 'Cancel Request', 'borrow-request:cancel', NULL, 2, 31, 5, NULL, NULL);

-- =============================
-- userloyee Management
-- =============================
INSERT INTO t_permission VALUES (40, 'userloyee Management', 'userloyee', NULL, 1, 0, 5, 'User', NULL);
INSERT INTO t_permission VALUES (41, 'userloyee', 'userloyee:menu', '/dashboard/userloyee', 1, 40, 1, 'UserFilled', 'userloyeeView');

INSERT INTO t_permission VALUES (42, 'userloyee List', 'userloyee:list', NULL, 2, 41, 1, NULL, NULL);
INSERT INTO t_permission VALUES (43, 'Create userloyee', 'userloyee:add', NULL, 2, 41, 2, NULL, NULL);
INSERT INTO t_permission VALUES (44, 'Update userloyee', 'userloyee:update', NULL, 2, 41, 3, NULL, NULL);
INSERT INTO t_permission VALUES (45, 'Delete userloyee', 'userloyee:delete', NULL, 2, 41, 4, NULL, NULL);
INSERT INTO t_permission VALUES (46, 'View userloyee', 'userloyee:view', NULL, 2, 41, 5, NULL, NULL);

-- =============================
-- Team Management
-- =============================
INSERT INTO t_permission VALUES (50, 'Team Management', 'team', NULL, 1, 0, 6, 'OfficeBuilding', NULL);
INSERT INTO t_permission VALUES (51, 'Team', 'team:menu', '/dashboard/team', 1, 50, 1, 'School', 'TeamView');

INSERT INTO t_permission VALUES (52, 'Team List', 'team:list', NULL, 2, 51, 1, NULL, NULL);
INSERT INTO t_permission VALUES (53, 'Create Team', 'team:add', NULL, 2, 51, 2, NULL, NULL);
INSERT INTO t_permission VALUES (54, 'Delete Team', 'team:delete', NULL, 2, 51, 3, NULL, NULL);

-- =============================
-- Role Management
-- =============================
INSERT INTO t_permission VALUES (60, 'Role Management', 'role', NULL, 1, 0, 7, 'Lock', NULL);
INSERT INTO t_permission VALUES (61, 'Role', 'role:menu', '/dashboard/role', 1, 60, 1, 'UserFilled', 'RoleView');

INSERT INTO t_permission VALUES (62, 'Role List', 'role:list', NULL, 2, 61, 1, NULL, NULL);
INSERT INTO t_permission VALUES (63, 'Create Role', 'role:add', NULL, 2, 61, 2, NULL, NULL);
INSERT INTO t_permission VALUES (64, 'Delete Role', 'role:delete', NULL, 2, 61, 3, NULL, NULL);

-- =============================
-- System Config（放最后）
-- =============================
INSERT INTO t_permission VALUES (70, 'System Config', 'system-config', NULL, 1, 0, 8, 'Tools', NULL);
INSERT INTO t_permission VALUES (71, 'Config', 'system-config:menu', '/dashboard/system', 1, 70, 1, 'Setting', 'SystemView');

INSERT INTO t_permission VALUES (72, 'Config View', 'system:view', NULL, 2, 71, 1, NULL, NULL);
INSERT INTO t_permission VALUES (73, 'Config Update', 'system:update', NULL, 2, 71, 2, NULL, NULL);



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



DROP TABLE IF EXISTS `t_user_team`;
CREATE TABLE `t_user_team`  (
                               `user_id` int unsigned NOT NULL,
                               `team_id` int unsigned NOT NULL,
                               PRIMARY KEY (`user_id`),
                               INDEX `idx_team_id`(`team_id`),
                               CONSTRAINT `fk_user_team_user` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                               CONSTRAINT `fk_user_team_team` FOREIGN KEY (`team_id`) REFERENCES `t_team` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='user Team Mapping';



DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
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
                           `owner_id` int unsigned NOT NULL COMMENT 'Responsible userloyee ID',

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

                           CONSTRAINT `fk_category`
                               FOREIGN KEY (`category_id`)
                                   REFERENCES `t_category` (`id`)
                                   ON DELETE RESTRICT,

                           CONSTRAINT `fk_asset_team`
                               FOREIGN KEY (`team_id`)
                                   REFERENCES `t_team` (`id`)
                                   ON DELETE RESTRICT,

                           CONSTRAINT `fk_asset_owner`
                               FOREIGN KEY (`owner_id`)
                                   REFERENCES `t_user` (`id`)
                                   ON DELETE RESTRICT,

                           CONSTRAINT `fk_asset_user`
                               FOREIGN KEY (`current_user_id`)
                                   REFERENCES `t_user` (`id`)
                                   ON DELETE SET NULL
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Asset Table';



DROP TABLE IF EXISTS `t_borrow_request`;
create table `t_borrow_request` (
                                    `id` int unsigned AUTO_INCREMENT,
                                    `asset_id` int unsigned NOT NULL COMMENT 'Asset ID',
                                    `borrower_id` int unsigned NOT NULL COMMENT 'Borrower (user_id)',
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
                                        FOREIGN KEY (`borrower_id`) REFERENCES `t_user` (`id`)
                                            ON DELETE RESTRICT,

                                    CONSTRAINT `fk_borrow_request_approver`
                                        FOREIGN KEY (`request_approver_id`) REFERENCES `t_user` (`id`)
                                            ON DELETE SET NULL,

                                    CONSTRAINT `fk_borrow_return_approver`
                                        FOREIGN KEY (`return_approver_id`) REFERENCES `t_user` (`id`)
                                            ON DELETE SET NULL
) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_general_ci
    COMMENT='Borrow Request Table';

-- user request borrow(pending/cancelled)：create_time > approver confirm(approved/rejected)：request_approver_id + request_approve_time > user confirm receive device(in_use)：borrow_start_time >
-- user request return(return_pending):return_request_time > approver confirm return(finished)：return_approver_id + return_approve_time

# permission
