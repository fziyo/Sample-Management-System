create table emp (
                      id int unsigned primary key auto_increment,
                      emp_no varchar(30) not null unique comment 'login account',
                      name varchar(50) not null comment 'real name',
                      gender tinyint unsigned not null default 0 comment '0=unknown 1=male 2=female',
                      team_id int unsigned not null comment 'business unit',
                      role_id int unsigned not null,
                      create_time datetime default current_timestamp,
                      update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table role (
                      id int unsigned primary key auto_increment,
                      name varchar(50) unique not null comment 'Admin Staff',
                      create_time datetime default current_timestamp,
                      update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) comment 'Admin Staff';

create table team (
                               id int unsigned primary key auto_increment,
                               name varchar(50) unique not null ,
                               create_time datetime default current_timestamp,
                               update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table asset (
                               id int unsigned primary key auto_increment,
                               asset_code VARCHAR(100) unique not null unique comment '00RY',
                               name varchar(50) not null comment 'device name',
                               model varchar(50) not null comment 'ELA-B19',

                               category_id int unsigned not null comment 'asset_category pk',
                               team_id int unsigned not null comment 'unit owned by',
                               owner_id int unsigned not null comment 'emp pk responsible person',

                               sn VARCHAR(50) unique comment 'serial number',
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
                                start_date DATETIME not null,
                                end_date DATETIME not null ,
                                status tinyint unsigned not null DEFAULT 0 comment '0=PENDING  1=APPROVED  2=REJECTED 3=Cancelled',
                                approver_id int unsigned comment 'emp pk',
                                approve_time DATETIME ,
                                create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


