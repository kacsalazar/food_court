CREATE TABLE categories(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(20) NOT NULL CHECK (name ~ '[^0-9]'),
    description        varchar(50)
);

CREATE TABLE restaurants (
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(20),
    address          VARCHAR(20),
    id_owner         VARCHAR(20),
    phone_number     VARCHAR,
    url_logo            VARCHAR(50),
    nit         VARCHAR(100),
    CONSTRAINT chk_nit_only_digits CHECK (nit ~ '^[0-9]+$'),
    CONSTRAINT chk_phone_number CHECK (phone_number ~ '^\+?[0-9]{1,13}$'),
    CONSTRAINT chk_name_valid CHECK (name ~ '[^0-9]')
);

CREATE TABLE dishes(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(20) NOT NULL CHECK (name ~ '[^0-9]'),
    id_category        BIGINT,
    description        varchar(50),
    price              INTEGER NOT NULL CHECK (price > 0),
    id_restaurant      BIGINT,
    url_image          VARCHAR(50),
    isActive           BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_restaurants_r FOREIGN KEY (id_restaurant) REFERENCES restaurants (id) ON DELETE NO ACTION
);

CREATE TABLE orders(
    id                 SERIAL PRIMARY KEY,
    id_client            BIGINT NOT NULL,
    id_restaurant      BIGINT NOT NULL,
    order_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_chef       BIGINT NOT NULL,
    status             VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED', 'DELIVERED')),
    --CONSTRAINT fk_user_o FOREIGN KEY (id_user) REFERENCES users (id) ON DELETE NO ACTION,
    CONSTRAINT fk_restaurant_o FOREIGN KEY (id_restaurant) REFERENCES restaurants (id) ON DELETE NO ACTION
);

CREATE TABLE order_items(
    id                 SERIAL PRIMARY KEY,
    id_order           BIGINT NOT NULL,
    id_dish            BIGINT NOT NULL,
    quantity           INTEGER NOT NULL CHECK (quantity > 0),
    CONSTRAINT fk_order_i FOREIGN KEY (id_order) REFERENCES orders (id) ON DELETE NO ACTION,
    CONSTRAINT fk_dish_i FOREIGN KEY (id_dish) REFERENCES dishes (id) ON DELETE NO ACTION
);