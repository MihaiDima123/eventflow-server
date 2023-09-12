create table user_permissions(
    id serial primary key,
    name varchar(64) unique
);

insert into user_permissions(id, name) values (1, 'USER'), (2, 'ADMIN');

create table users (
    id serial primary key,
    email varchar(256) unique,
    password varchar(512),
    permission_id int,
    constraint  FK_user_permission foreign key (permission_id) references user_permissions(id)
);
