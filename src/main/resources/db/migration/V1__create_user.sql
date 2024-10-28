CREATE TABLE users (
    id UUID NOT NULL,
    username VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);
ALTER TABLE users ADD CONSTRAINT uc_users_email_address UNIQUE (email_address);