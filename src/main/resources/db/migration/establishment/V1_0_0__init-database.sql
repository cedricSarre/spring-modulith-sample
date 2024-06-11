CREATE TABLE establishment
(
    id                      UUID    NOT NULL,
    "name"                  varchar NOT NULL,
    address                 varchar NULL,
    phone_number            varchar NULL,
    email                   varchar NULL,
    nb_max_classroom        int NOT NULL,
    CONSTRAINT establishment_pk PRIMARY KEY (id)
);

CREATE TABLE employee
(
    id                  UUID    NOT NULL,
    establishment_id    UUID    NOT NULL,
    firstname           varchar NOT NULL,
    lastname            varchar NOT NULL,
    role                varchar NOT NULL,
    email               varchar NULL,
    CONSTRAINT employee_pk PRIMARY KEY (id)
);

CREATE TABLE activity
(
    id                  UUID    NOT NULL,
    establishment_id    UUID    NOT NULL,
    title               varchar NOT NULL,
    description         varchar NOT NULL,
    "date"              date    NULL,
    CONSTRAINT activity_pk PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT employee_id FOREIGN KEY (establishment_id) REFERENCES establishment (id) ON DELETE CASCADE;
ALTER TABLE activity
    ADD CONSTRAINT activity_id FOREIGN KEY (establishment_id) REFERENCES establishment (id) ON DELETE CASCADE;
