CREATE TABLE stages
(
    id          SERIAL PRIMARY KEY,
    project_id  INT REFERENCES projects (id),
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    start_date  DATE,
    deadline    DATE,
    is_deleted  BOOLEAN DEFAULT FALSE
);