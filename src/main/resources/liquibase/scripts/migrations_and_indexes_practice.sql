-- liquibase formatted sql

-- changeset kirill_tyurikov:1
CREATE INDEX students_name_index ON student (name);
-- changeset kirill_tyurikov:2
CREATE INDEX faculty_name_color_index ON faculty (name, color);