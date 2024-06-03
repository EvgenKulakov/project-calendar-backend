CREATE TABLE tasks
(
    id             SERIAL PRIMARY KEY,
    stage_id       INT REFERENCES stages (id),
    name           VARCHAR(255)                                          NOT NULL,
    description    TEXT,
    link_to_gitlab TEXT,
    status         VARCHAR(20) CHECK (status IN ('ACTIVE', 'COMPLETED')) NOT NULL,
    create_date    DATE                                                  NOT NULL,
    deadline       DATE,
    is_deleted     BOOLEAN DEFAULT FALSE                                 NOT NULL
);