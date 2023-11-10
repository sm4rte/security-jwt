insert into roles(name)
values('ROLE_USER'), ('ROLE_ADMIN');

insert into users(id, username, password, email)
values
(1,'admin','$2a$12$KYz8zzyTp.pMs2IqA0BmBOZiu7WMQ95RKZO7KWAMGP6yyVccYLRzC','admin@mail.com'),
(2,'user','$2a$12$biDq92nHKOoc6ndbhpJLNuSjoKoAy04LH9q4qaKVZBDYdIrm2Jz0e','user@mail.com');
