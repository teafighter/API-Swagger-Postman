ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age > 15);
ALTER TABLE student ALTER COLUMN name SET NOT NULL;
ALTER TABLE student ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE student ADD CONSTRAINT name_faculty_unique UNIQUE (name, faculty);
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;