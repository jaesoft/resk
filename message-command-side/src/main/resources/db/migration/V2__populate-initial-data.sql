-- Create initial users
INSERT INTO users(id, email, username, password) values('2c9de2b9-d9e1-40b4-b0b7-2d60900a7a38', 'admin@resk.io', 'admin', '$2a$10$rfjZ0l/Q78mXOwU9mfczZuYF7kEPqodx9ZI.tLRUmIa65WTmMfB9e');
INSERT INTO roles values('ROLE_ADMIN');
INSERT INTO user_role values('2c9de2b9-d9e1-40b4-b0b7-2d60900a7a38', 'ROLE_ADMIN');