drop tabel if exists File;
drop table if exists Log;

create table file(id bigint primary key auto_increment, user_name varchar(32), md5 varchar(32), length bigint, path varchar(64));
create table log(id bigint primary key, user_name varchar(32), state bit);