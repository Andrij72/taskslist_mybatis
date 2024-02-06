create schema if not exists taskslist;

create table if not exists taskslist.users
(
    id      bigserial primary key,
    name     varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null
);

create table if not exists taskslist.tasks
(
    id              bigserial primary key,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    expiration_date timestamp    null
);

create table if not exists taskslist.users_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_users_tasks_user foreign key (user_id) references taskslist.users (id) on delete cascade on update no action,
    constraint fk_users_tasks_task foreign key (task_id) references taskslist.tasks (id) on delete cascade on update no action
);

create table if not exists taskslist.users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references taskslist.users (id) on delete cascade on update no action
);



