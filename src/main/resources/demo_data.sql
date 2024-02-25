insert into users(name, username, password)
values ('John Silver', 'jnsilver@gmail.com', '$2a$10$auDg8kb2VcFrofzJf6HHZ.sSOQE9G5KrukRgxUbYX1lladT8ArlYS'),
       ('Friderik Jakobs', 'fridjakobs@gmail.com', '$2a$12$7Nh4r8t2g71b.H.54qEEO.wPefEtjIBWF1Ji0Y.NcG.WUQa9I7U.W');


insert into tasks(title, description, status, expiration_date)
values ('Buy Butter', null, 'TODO', '2023-06-14 15:23:42'),
       ('Do homework', 'Mathematics, Physics', 'IN_PROGRESS', '2023-01-01 10:00:00'),
       ('Clean room', null, 'DONE', null),
       ('Call Nino', 'Ask him about next work', 'TODO', '2023-03-03 00:00:00');
insert into users_tasks(task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);
insert into users_roles(user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');

