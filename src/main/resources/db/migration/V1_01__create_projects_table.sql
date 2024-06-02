CREATE TABLE projects
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255)                                                       NOT NULL,
    description      TEXT,
    status           VARCHAR(20) CHECK (status IN ('CREATED', 'IN_PROGRESS', 'FINISH')) NOT NULL,
    current_stage_id INT,
    start_date       DATE,
    deadline         DATE,
    estimated_hours  INT,
    is_deleted       BOOLEAN DEFAULT FALSE
);
