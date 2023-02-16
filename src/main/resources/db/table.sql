create table user_tb (
    id int auto_increment primary key, 
    username varchar not null unique,
    password varchar not null,
    email varchar not null unique, 
    profile varchar,
    roll varchar not null default 'USER', 
    created_at timestamp not null
);

create table board_tb (
    id int auto_increment primary key,
    user_id int not null, 
    title varchar(100) not null,
    thumbnail longtext not null, 
    content longtext not null,
    created_at timestamp not null
); 

create table reply_tb(
    id int auto_increment primary key,
    user_id int not null,
    board_id int not null,
    comment varchar(100) not null,
    created_at timestamp not null
);

create table like_tb(
    id int auto_increment primary key,
    board_id int not null, 
    user_id int not null,
    code varchar
);

commit;