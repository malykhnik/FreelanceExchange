drop table freelance_announcement;
drop table orders;
drop table users;
drop table roles;

CREATE TABLE if not exists roles
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(50)
);

CREATE TABLE if not exists users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE,
    password   VARCHAR(255),
    name       VARCHAR(255),
    role_id    INTEGER,
    tel_number VARCHAR(20),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE if not exists orders
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER,
    order_name  VARCHAR(255),
    description TEXT,
    status      VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE if not exists freelance_announcement
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    user_id     INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
