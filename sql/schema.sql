create table emp (
                      id int unsigned primary key auto_increment,
                      emp_no varchar(50) not null unique comment 'login account',
                      real_name varchar(50) not null comment 'real name',
                      password varchar(255) not null comment 'password hash',
                      gender tinyint unsigned not null comment '0=male 1=female',
                      create_time datetime not null default current_timestamp,
                      update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table role (
                      id int unsigned primary key auto_increment,
                      role_code varchar(50) unique not null ,
                      create_time datetime default current_timestamp,
                      update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table emp_role (
                           emp_id int unsigned not null comment 'emp pk',
                           role_id int unsigned not null comment 'role pk',
                           primary key (emp_id, role_id),
                           create_time datetime default current_timestamp
);

create table business_unit (
                               id int unsigned primary key auto_increment,
                               unit_name varchar(50) unique not null ,
                               create_time datetime default current_timestamp,
                               update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table asset (
                               id int unsigned primary key auto_increment,
                               asset_code VARCHAR(100) unique not null comment '00RY',
                               name varchar(50) not null ,
                               model_name varchar(50) not null comment 'ELA-B19',

                               category_id int not null comment 'asset_category pk',
                               business_unit_id int not null comment 'unit owned by',
                               owner int unsigned not null comment 'emp pk responsible person',

                               serial_number VARCHAR(50),
                               mac_addr VARCHAR(50),

                               release_year year,

                               asset_condition VARCHAR(50) comment 'Good Damaged',
                               commercial_status VARCHAR(50) comment 'Commercial Non-commercial',
                               is_active BOOLEAN DEFAULT TRUE comment 'Yes No',

                               status VARCHAR(50) not null DEFAULT 'STOCK' comment 'Stock In Use',
                               current_user_id int unsigned comment 'emp pk',

                               location VARCHAR(255),
                               description TEXT,

                               create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                               update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

);

create table asset_category (
                                 id int unsigned PRIMARY KEY AUTO_INCREMENT,
                                 category_name VARCHAR(50) UNIQUE NOT NULL comment 'wearable phone tablet...',
                                 create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                 update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

) comment 'wearable phone tablet...';

create table borrow_request (
                                id int unsigned PRIMARY KEY AUTO_INCREMENT,
                                asset_id int unsigned not null comment 'asset pk',
                                borrower int unsigned not null comment 'emp pk',
                                start_date DATETIME not null,
                                end_date DATETIME not null ,
                                status VARCHAR(50) not null DEFAULT 'PENDING' comment 'PENDING  APPROVED  REJECTED',
                                approve_by int unsigned comment 'emp pk',
                                approve_time DATETIME ,
                                create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
                                update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);