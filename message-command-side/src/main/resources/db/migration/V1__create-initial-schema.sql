-- Create initial schema
CREATE TABLE IF NOT EXISTS users (
  id UUID NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN DEFAULT TRUE,
  account_expired BOOLEAN DEFAULT FALSE,
  account_locked BOOLEAN DEFAULT FALSE,
  password_expired BOOLEAN DEFAULT FALSE,
  CONSTRAINT users_email_unq UNIQUE (email),
  CONSTRAINT users_username_unq UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS roles (
  authority VARCHAR(255) NOT NULL PRIMARY KEY,
  CONSTRAINT roles_authority_unq UNIQUE (authority)
);

CREATE TABLE IF NOT EXISTS user_role (
  user_id UUID NOT NULL,
  role VARCHAR(255) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  FOREIGN KEY (role) REFERENCES roles(authority) ON DELETE CASCADE,
  PRIMARY KEY (user_id, role)
);

CREATE TABLE IF NOT EXISTS projects (
  id UUID NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  owner VARCHAR(255),
  description TEXT
);

