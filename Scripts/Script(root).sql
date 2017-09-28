show databases;

use mysql_study01;

desc department;

select * from employee;
select * from department;

delete from employee where empno=1007;
delete from department where deptno=4;

alter table employee add column leaveOffic varchar(20);