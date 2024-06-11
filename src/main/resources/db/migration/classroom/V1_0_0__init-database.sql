CREATE TABLE classroom
(
    id                      UUID    NOT NULL,
    establishment_id        UUID    NOT NULL,
    name                    varchar NOT NULL,
    "level"                 varchar NOT NULL,
    max_students_capacity   int     NOT NULL,
    CONSTRAINT classroom_pk PRIMARY KEY (id)
);

CREATE TABLE planning
(
    id                      UUID    NOT NULL,
    classroom_id            UUID    NOT NULL,
    "day"                   varchar NOT NULL,
    begin_hour              varchar NOT NULL,
    end_hour                varchar NOT NULL,
    subject                 varchar NOT NULL,
    teacher_id              UUID    NOT NULL,
    location                varchar NOT NULL,
    CONSTRAINT planning_pk PRIMARY KEY (id)
);


ALTER TABLE planning
    ADD CONSTRAINT planning_id FOREIGN KEY (classroom_id) REFERENCES classroom (id) ON DELETE CASCADE;