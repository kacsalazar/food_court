INSERT INTO roles (name, description)
VALUES
('ROLE_ADMIN', 'Administrator role with full access'),
('ROLE_CLIENT', 'Standard user role with limited access'),
('ROLE_OWNER', 'Role with access to manage their own resources'),
('ROLE_USER', 'Standard user role with limited access')
ON CONFLICT (name) DO NOTHING;

INSERT INTO users (id_rol, name, last_name, dni, phone_number,
birthday_date, email, password) VALUES
(1, 'Pepe', 'Perez', '12345678', '123456789', '1990-01-01', 'pepeperez@mail.com', 'password123')
ON CONFLICT (dni) DO NOTHING;

--(2, 'ROLE_USER', 'Standard user role with limited access')