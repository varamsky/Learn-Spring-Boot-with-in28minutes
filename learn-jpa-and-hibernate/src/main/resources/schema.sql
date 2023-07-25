-- This sql file will automatically picked up by H2 and the course table will be created
create table course
(
    id bigint not null,
    name varchar(255) not null,
    author varchar(255) not null,
    primary key (id)
);