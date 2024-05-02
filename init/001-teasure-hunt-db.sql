
CREATE DATABASE treasure_hunt_db;

GRANT ALL PRIVILEGES ON DATABASE treasure_hunt_db TO lasmik;

\connect treasure_hunt_db

BEGIN;

CREATE SCHEMA IF NOT EXISTS treasure_hunt_schema;

CREATE TABLE treasure_hunt_schema.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255)
);

INSERT INTO treasure_hunt_schema.users (username, password) VALUES
('oversvøm', 'hallo!');

COMMIT;