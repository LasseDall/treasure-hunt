
CREATE DATABASE treasure_hunt_db;

GRANT ALL PRIVILEGES ON DATABASE treasure_hunt_db TO lasmik;

\connect treasure_hunt_db

BEGIN;

CREATE SCHEMA IF NOT EXISTS treasure_hunt_schema;

CREATE TABLE treasure_hunt_schema.users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    name VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE treasure_hunt_schema.clues (
    id SERIAL PRIMARY KEY,
    text TEXT,
    image BYTEA,
    role VARCHAR(50)
);

CREATE TABLE treasure_hunt_schema.codes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    password VARCHAR(255),
    new_role VARCHAR(50)
);

INSERT INTO treasure_hunt_schema.users (username, password, email, phone, name, role) VALUES
('oversvøm', 'hallo!', 'lasse@live.dk', '20627140', 'Lasse Dall', 'new');

INSERT INTO treasure_hunt_schema.clues (text, image, role) VALUES
('Kig godt efter..', './ibens.jpg', 'new'),
('Kig efter..', './ibens.jpg', 'ny'),
('Kig rigtig godt efter..', './ibens.jpg', 'not new');

COMMIT;