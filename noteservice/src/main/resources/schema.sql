-- Create user table. 
DROP TABLE IF EXISTS note_user;

CREATE TABLE note_user (
    id                     SERIAL PRIMARY KEY NOT NULL,
    email                  VARCHAR(100) NOT NULL,
    password               VARCHAR(50) NOT NULL,
    create_date            TIMESTAMP,
    update_date            TIMESTAMP
);

-- Create note table.
DROP TABLE IF EXISTS note_note;

CREATE TABLE note_note (
    id                     SERIAL PRIMARY KEY NOT NULL,
    user_id                BIGINT NOT NULL,
    title                  VARCHAR(50) NOT NULL,
    note                   TEXT NULL,
    create_date            TIMESTAMP,
    update_date            TIMESTAMP
);
