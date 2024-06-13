
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
    role VARCHAR(50),
    notes TEXT
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
('Lasse123', '$2a$10$xVckpwY2AJ4vdBGwROqpqOn5agaLZzl2zCXiUZy7Jh8LTfavxSQUm', 'lasse@live.dk', '20627140', 'Lasse Dall', 'new');

INSERT INTO treasure_hunt_schema.clues (text, image, role) VALUES
('Mit første er dagslyset, <br /> Mit andet gør mit første til farver for evigt, <br /> Mit tredje er min by, <br /> Mit fjerde er mine arme og ben, <br /> Og mit sidste kan du vel gætte selv', pg_read_binary_file('/app/kashmir.jpg'), 'ny'),
('Kig godt efter..', pg_read_binary_file('/app/kashmir.jpg'), 'gåde 2+'),
('Kig efter..', pg_read_binary_file('/app/kashmir.jpg'), 'gåde 1'),
('Kig rigtig godt efter..', pg_read_binary_file('/app/kashmir.jpg'), 'gåde 3++');

INSERT INTO treasure_hunt_schema.codes (name, password, new_role) VALUES
('første', '$2a$10$eb8PhgW0DR9ufyyMc1lC.e8pGxz8pDwyzFAOkuT6PCai61/hP2/Ju', 'gåde 1'),
('grids', '$2a$10$45DH8QcSnFclB2kd/s5d7er8QE6yray.jXMXB.9K6vukhlEdZ.RZe', 'gåde 2+'),
('map', '$2a$10$WzjFMHtwrQPesYWmheaDu.xr/tJijQvvFAff2Gd3N4AbT3a8LeZqq', 'gåde 3++'),
('chess', '$2a$10$BN6ra65gUU8VNZVqPW53SugNNNHeeYDe9AEcH2giftw3rPcxyfWcm', 'gåde 4+++');

COMMIT;