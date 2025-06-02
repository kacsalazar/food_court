INSERT INTO roles (name, description)
VALUES
('ROLE_ADMIN', 'Administrator role with full access')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id_rol, name, last_name, dni, phone_number,
birthday_date, email, password) VALUES
(1, 'Pepe', 'Perez', '12345678', '123456789', '1990-01-01', 'pepeperez@mail.com', 'password123')
ON CONFLICT (dni) DO NOTHING;

--(2, 'ROLE_USER', 'Standard user role with limited access')