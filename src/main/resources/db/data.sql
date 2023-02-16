insert into user_tb(username, password, email, profile, created_at) values ('ssar', '1234', 'ssar@nate.com', 'ha.png', now());
insert into user_tb(username, password, email, created_at) values ('love', '1234', 'love@nate.com', now());
insert into user_tb(username, password, email, roll, created_at) values ('hee', '1004', 'hee@nate.com', 'ADMIN', now());

insert into board_tb(user_id, title, thumbnail, content,  created_at) values (1, '제목1', '/images/dora.png', '111111111', now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (1, '제목2', '/images/dora.png', '222222222',  now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목3', '/images/dora.png', '333333333', now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (1, '제목4', '/images/ha.jpg', '44444444444', now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목5', '/images/dora.png', '555555555',  now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목6', '/images/dora.png', '66666666666',  now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목7', '/images/ha.jpg', '777777777777',  now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목8', '/images/dora.png', '88888888888',  now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목9', '/images/good.jpg', '9999999999',  now());
insert into board_tb(user_id, title, thumbnail, content,  created_at) values (2, '제목10', '/images/dora.png', '0000000000',  now());

insert into reply_tb(user_id, board_id, comment, created_at) values (1, 1, '댓글1', now());
insert into reply_tb(user_id, board_id, comment, created_at) values (1, 2, '댓글2', now());
insert into reply_tb(user_id, board_id, comment, created_at) values (2, 1, '댓글3', now());
insert into reply_tb(user_id, board_id, comment, created_at) values (2, 2, '댓글4', now());

commit; 