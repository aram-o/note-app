-- Create user table. 
DROP TABLE IF EXISTS note_user;

CREATE TABLE note_user (
    id                     SERIAL PRIMARY KEY NOT NULL,
    email                  VARCHAR(50) UNIQUE NOT NULL,
    password               VARCHAR(100) NOT NULL,
    enabled                BOOLEAN NOT NULL DEFAULT TRUE,
    create_date            TIMESTAMP,
    update_date            TIMESTAMP
);

-- Create note authority.
DROP TABLE IF EXISTS note_authority;

CREATE TABLE note_authority (
    id           SERIAL PRIMARY KEY NOT NULL,
    email        VARCHAR(50) NOT NULL,
    authority    VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES note_user(email)
);

CREATE UNIQUE INDEX ix_auth_email on note_authority (email, authority);

-- Create note table.
DROP TABLE IF EXISTS note_note;

CREATE TABLE note_note (
    id                     SERIAL PRIMARY KEY NOT NULL,
    user_email             VARCHAR(50) NOT NULL,
    title                  VARCHAR(50) NOT NULL,
    note                   TEXT NULL,
    create_date            TIMESTAMP,
    update_date            TIMESTAMP
);
