insert into user(userId, userPw, username, email, phone, address, role, created_date, update_date) values
    ('user', '{noop}password','user','user@localhost.com', '000-0000-0000', '~', 'USER', now(), now());

insert into user(userId, userPw, username, email, phone, address, role, created_date, update_date) values
    ('admin', '{noop}password', 'admin', 'admin@localhost.com', '000-0000-0000', '~', 'ADMIN', now(), now());
