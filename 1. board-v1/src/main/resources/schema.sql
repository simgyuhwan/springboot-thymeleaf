drop table if exists user;
drop table if exists posts;

create table posts(
      post_id int auto_increment,
      created_date datetime,
      update_date datetime,
      content varchar(1000) not null,
      title varchar(20) not null,
      writer varchar(20) not null,
      primary key (post_id)
);

create table user(
      id int auto_increment,
      userId varchar(20) not null,
      userPw varchar(20) not null,
      username varchar(20) not null,
      email varchar(30) not null unique,
      phone varchar(20),
      address varchar(50),
      role varchar(20),
      created_date datetime,
      update_date datetime,
      primary key(id)
);