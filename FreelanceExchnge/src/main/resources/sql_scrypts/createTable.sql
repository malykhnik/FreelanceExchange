-- Создание таблицы users
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE,
    password   VARCHAR(255),
    name       VARCHAR(255),
    role_id    INTEGER,
    tel_number VARCHAR(20),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- Создание таблицы orders
CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER,
    order_name  VARCHAR(255),
    description TEXT,
    status      VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Создание таблицы Freelance_announcement
CREATE TABLE freelance_announcement
(
    id          SERIAL PRIMARY KEY,
    description TEXT,
    user_id     INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Создание таблицы roles
CREATE TABLE roles
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(50)
);