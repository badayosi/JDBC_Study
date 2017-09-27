show databases;

use sampledb;

create table student(
	id char(7),
	name varchar(10) not null,
	dept varchar(20) not null,
	primary key(id)
);

show tables;

desc student;

insert into student values('1091011','김철수','컴퓨터시스템');
insert into student values('0792012','최고봉','멀티미디어');
insert into student values('0494013','이기자','컴퓨터공학');

select * from student;

insert into student values('1234567', '김개똥', '정보보안');

delete from student where id='1234567';