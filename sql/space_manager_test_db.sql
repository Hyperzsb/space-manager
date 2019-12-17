#Create database and admin
drop database if exists `space_manager_test`;
create database `space_manager_test`;
create user 'space_manager'@'localhost' identified by 'space_manager';
grant all on space_manager_test.* to 'space_manager'@'localhost';
#Switch to database space_manager_test
use space_manager_test;
#Create tables for entities
create table `academy`
(
    `id`           int(5)      not null auto_increment,
    `academy_name` varchar(20) not null,
    primary key (id)
);
create table `borrower`
(
    `id`            int(10)     not null,
    `borrower_name` varchar(20) not null,
    `academy_id`    int(5)      not null,
    primary key (id)
);
create table `borrowing_order`
(
    `id`           int(10)  not null auto_increment,
    `borrower_id`  int(10)  not null,
    `room_id`      int(5)   not null,
    `order_note`   varchar(500)      default null,
    `order_time`   datetime not null,
    `start_time`   datetime not null,
    `end_time`     datetime not null,
    `order_status` int(5)   not null default 2,
    primary key (id)
);
create table `room`
(
    `id`                int(5)      not null auto_increment,
    `room_name`         varchar(20) not null,
    `room_note`         varchar(500) default null,
    `room_availability` int(5)      not null,
    primary key (id)
);