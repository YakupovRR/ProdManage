CREATE TABLE departments
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE task_statues (
                              id SERIAL PRIMARY KEY,
                              name VARCHAR(100)
);

CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    department_id INT REFERENCES departments(id),
    supervisor_id INT REFERENCES users(id) DEFERRABLE INITIALLY DEFERRED
);

CREATE TABLE products
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL UNIQUE,
    type          VARCHAR(150),
    department_id INT REFERENCES departments(id)
);

CREATE TABLE product_relations
(
    parent_product_id INT REFERENCES products(id),
    child_product_id  INT REFERENCES products(id),
    PRIMARY KEY (parent_product_id, child_product_id)
);

CREATE TABLE tasks
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_data TIMESTAMP,
    deadline TIMESTAMP,
    completion_date TIMESTAMP,
    status_id INT REFERENCES task_statues(id),
    creator_id INT REFERENCES users(id),
    executor_id INT REFERENCES users(id),
    parent_task_id INT REFERENCES tasks(id),
    department_id INT REFERENCES departments(id),
    CONSTRAINT fk_parent_task FOREIGN KEY (parent_task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
