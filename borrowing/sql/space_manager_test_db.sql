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
) charset = utf8
  collate = utf8_general_ci;
create table `borrower`
(
    `id`            int(10)     not null,
    `borrower_name` varchar(20) not null,
    `academy_id`    int(5)      not null,
    primary key (id)
) charset = utf8
  collate = utf8_general_ci;
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
) charset = utf8
  collate = utf8_general_ci;
create table `room`
(
    `id`                int(5)      not null auto_increment,
    `room_name`         varchar(20) not null,
    `room_note`         varchar(500) default null,
    `room_availability` int(5)      not null,
    primary key (id)
) charset = utf8
  collate = utf8_general_ci;
insert into room (id, room_name, room_note, room_availability)
values (1, '研讨室 1', '小型研讨室，小团队必备的讨论空间', 1);
insert into room (id, room_name, room_note, room_availability)
values (2, '研讨室 2', '小型研讨室，小团队必备的讨论空间', 1);
insert into room (id, room_name, room_note, room_availability)
values (3, '共享办公室', '小型办公室，用以各社团、组织的日常办公等活动', 1);
insert into room (id, room_name, room_note, room_availability)
values (4, '报告厅', '中型报告厅，满足开会、宣讲、授课等需求', 1);
insert into room (id, room_name, room_note, room_availability)
values (5, 'Facetime室', 'Facetime空间，导师学生交流的圣地', 1);
insert into room (id, room_name, room_note, room_availability)
values (6, '会议室', '中型会议室，用于各种形式的会议', 1);
insert into room (id, room_name, room_note, room_availability)
values (7, '咖啡屋', '给同学们休闲娱乐的空间，放松身心，专注学习', 0);
insert into room (id, room_name, room_note, room_availability)
values (8, '学生活动空间', '可用用于各社团、组织、班级或个人的活动需求', 1);
insert into room (id, room_name, room_note, room_availability)
values (9, '导学空间', '一个导师和学生可以面对面答疑解惑、交流心得的地方', 1);
insert into room (id, room_name, room_note, room_availability)
values (10, '心理放松室', '休闲娱乐、放松心情，让你我的心理更健康', 1);
insert into room (id, room_name, room_note, room_availability)
values (11, '文体空间', '提供给同学们一个开展问题活动的自由空间', 1);
insert into room (id, room_name, room_note, room_availability)
values (12, '茶香书屋', '品茶、品书。遁入宁静感受生活的美好', 0);
insert into room (id, room_name, room_note, room_availability)
values (13, '自管办公室', '睿信书院自管委员会办公室，自管委负责社区的日常工作和活动', 0);
insert into room (id, room_name, room_note, room_availability)
values (14, '党建办公室', '党建工作专用办公室', 0);
insert into room (id, room_name, room_note, room_availability)
values (15, '院史馆', '睿信书院院史展览馆，让同学们了解书院历史，品味书院内涵', 0);
