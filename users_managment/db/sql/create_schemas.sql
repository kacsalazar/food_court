CREATE TABLE users(
    id                 BIGINIT NOT NULL,
    id_rol             BIGINIT NOT NULL,
    name               varchar(20),
    last_name          varchar(20),
    dni                varchar(20),
    phone_number       varchar(20),
    birthday_date      varchar(20),
    email              varchar(20),
    password           varchar(20),
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_role_r FOREIGN KEY ( id_rol ) REFERENCES roles (id) ON DELETE NO ACTION
);

CREATE TABLE roles(
    id                 BIGINIT NOT NULL,
    name               BIGINIT NOT NULL,
    description        varchar(50),
    CONSTRAINT pk_user PRIMARY KEY (id)
);