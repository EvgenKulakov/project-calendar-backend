CREATE TABLE employees
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50)           NOT NULL,
    last_name  VARCHAR(50)           NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);