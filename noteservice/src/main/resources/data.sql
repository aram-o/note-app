-- Insert default users.
INSERT INTO note_user (email, password, create_date, update_date) VALUES ('test1@email.com', 'password', current_timestamp, current_timestamp);
INSERT INTO note_user (email, password, create_date, update_date) VALUES ('test2@email.com', 'password', current_timestamp, current_timestamp);
INSERT INTO note_user (email, password, create_date, update_date) VALUES ('test3@email.com', 'password', current_timestamp, current_timestamp);
INSERT INTO note_user (email, password, create_date, update_date) VALUES ('test4@email.com', 'password', current_timestamp, current_timestamp);
INSERT INTO note_user (email, password, create_date, update_date) VALUES ('test5@email.com', 'password', current_timestamp, current_timestamp);

INSERT INTO note_authority (email, authority) values ('test1@email.com', 'ROLE_USER');
INSERT INTO note_authority (email, authority) values ('test2@email.com', 'ROLE_USER');
INSERT INTO note_authority (email, authority) values ('test3@email.com', 'ROLE_USER');
INSERT INTO note_authority (email, authority) values ('test4@email.com', 'ROLE_USER');
INSERT INTO note_authority (email, authority) values ('test5@email.com', 'ROLE_USER');