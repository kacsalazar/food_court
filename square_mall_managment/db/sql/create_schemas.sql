CREATE TABLE users (
    id               BIGINT NOT NULL,
    id_rol           BIGINT NOT NULL,
    name             VARCHAR(20),
    last_name        VARCHAR(20),
    dni              VARCHAR(20),
    phone_number     VARCHAR,
    birthday_date    DATE,
    email            VARCHAR(50),
    password         VARCHAR(100),
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_role_r FOREIGN KEY (id_rol) REFERENCES roles (id) ON DELETE NO ACTION,
    CONSTRAINT chk_dni_only_digits CHECK (dni ~ '^[0-9]+$'),
    CONSTRAINT chk_phone_number CHECK (phone_number ~ '^\+?[0-9]{1,13}$'),
    CONSTRAINT chk_email_valid CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);

CREATE TABLE roles(
    id                 BIGINIT NOT NULL,
    name               BIGINIT NOT NULL,
    description        varchar(50),
    CONSTRAINT pk_user PRIMARY KEY (id)
);