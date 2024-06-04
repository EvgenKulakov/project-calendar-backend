CREATE TABLE IF NOT EXISTS employees
(
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR(50)           NOT NULL,
    last_name   VARCHAR(50)           NOT NULL,
    project_ids INT[],
    is_deleted  BOOLEAN DEFAULT FALSE NOT NULL
);