ALTER TABLE student
    ADD CONSTRAINT age_constraint2 CHECK (age > 15);

ALTER TABLE student
    ADD CONSTRAINT name_unique2 UNIQUE (name);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;

