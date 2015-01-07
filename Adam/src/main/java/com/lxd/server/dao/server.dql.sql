create table t_file(id bigint primary key auto_increment, user_name varchar(32), md5 varchar(32), length bigint, path varchar(256), last bigint);
create table t_log(id bigint primary key, user_name varchar(32), state bit);
create table t_user(user_name varchar(32) primary key, user_pwd varchar(32));