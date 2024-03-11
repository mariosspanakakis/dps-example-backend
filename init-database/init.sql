-- create table to store exercises
CREATE TABLE IF NOT EXISTS exercises (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT
);

-- insert some initial data
INSERT INTO exercises (title, description) VALUES
    ('Push-Ups', 'The first example exercise.'),
    ('Crunches', 'The second example exercise.');
