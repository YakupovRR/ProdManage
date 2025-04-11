CREATE TABLE departments
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
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
    quantity INT NOT NULL DEFAULT 1
);

CREATE TABLE tasks
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_data TIMESTAMP,
    deadline TIMESTAMP,
    completion_date TIMESTAMP,
    status VARCHAR(100),
    creator_id INT REFERENCES users(id),
    executor_id INT REFERENCES users(id),
    parent_task_id INT REFERENCES tasks(id),
    department_id INT REFERENCES departments(id),
    CONSTRAINT fk_parent_task FOREIGN KEY (parent_task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
