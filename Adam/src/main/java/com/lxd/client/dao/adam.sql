drop tabel if exists File;
create table file(id bigint primary key auto_increment, user_name varchar(32), md5 varchar(32), length bigint, path varchar(256), last bigint, edition int);
