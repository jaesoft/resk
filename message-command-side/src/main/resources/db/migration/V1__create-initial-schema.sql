-- Create initial schema
CREATE TABLE users (
  id UUID NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN DEFAULT TRUE,
  account_expired BOOLEAN DEFAULT FALSE,
  account_locked BOOLEAN DEFAULT FALSE,
  password_expired BOOLEAN DEFAULT FALSE,
  CONSTRAINT id UNIQUE (id),
  CONSTRAINT email UNIQUE (email),
  CONSTRAINT username UNIQUE (username)
);

CREATE TABLE roles (
  authority VARCHAR(255) NOT NULL PRIMARY KEY,
  CONSTRAINT authority UNIQUE (authority)
);

CREATE TABLE user_role (
  user_id UUID NOT NULL,
  role VARCHAR(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (role) REFERENCES roles(authority) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role)
);
