CREATE TABLE student
(
    id                      UUID,
    classroom_id            UUID,
    establishment_id        UUID,
    firstname               varchar NOT NULL,
    lastname                varchar NOT NULL,
    birthdate               date    NOT NULL,
    CONSTRAINT classroom_pk PRIMARY KEY (id)
);

CREATE TABLE absence
(
    id                      UUID    NOT NULL,
    student_id              UUID    NOT NULL,
    "date"                  date    NOT NULL,
    CONSTRAINT absence_pk PRIMARY KEY (id)
);

CREATE TABLE rate --notes
(
    id                      UUID    NOT NULL,
    student_id              UUID    NOT NULL,
    subject                 varchar NOT NULL, -- matière
    "date"                  date    NOT NULL,
    "value"                 float   NOT NULL, -- note
    CONSTRAINT rate_pk PRIMARY KEY (id)
);

ALTER TABLE absence
    ADD CONSTRAINT absence_id FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE;
ALTER TABLE rate
    ADD CONSTRAINT rate_id FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE;