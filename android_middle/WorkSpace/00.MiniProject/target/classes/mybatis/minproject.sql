--ListView 테이블 생성
create table ListView(
title varchar2(200),
content varchar2(1000),
imgresId number,
constraint list_pk primary key (title, imgresId)
);

insert into ListView
values ('title1', 'content1', 1);
insert into ListView
values ('title2', 'content1', 2);
insert into ListView
values ('title3', 'content1', 3);
insert into ListView
values ('title4', 'content1', 4);
insert into ListView
values ('title5', 'content1', 5);
insert into ListView
values ('title6', 'content1', 6);
insert into ListView
values ('title7', 'content1', 7);
insert into ListView
values ('title8', 'content1', 8);
insert into ListView
values ('title9', 'content1', 9);
insert into ListView
values ('title10', 'content1', 10);

select * from ListView;

commit;

--usertbl 테이블 생성
create table usertbl(
id varchar2(30),
pw varchar2(30),
name varchar2(30),
birth number,
constraint user_pk primary key (id)
);

insert into usertbl values ('a', 'a', 'a', 2111219);
insert into usertbl values ('b', 'b', 'b', 2111220);
insert into usertbl values ('c', 'c', 'c', 2111221);
insert into usertbl values ('d', 'd', 'd', 2111222);
insert into usertbl values ('e', 'e', 'e', 2111223);

select * from usertbl;

commit;
