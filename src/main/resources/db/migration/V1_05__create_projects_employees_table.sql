CREATE TABLE IF NOT EXISTS projects_employees
(
    project_id  INT,
    employee_id INT,
    PRIMARY KEY (project_id, employee_id),
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (employee_id) REFERENCES employees (id)
);