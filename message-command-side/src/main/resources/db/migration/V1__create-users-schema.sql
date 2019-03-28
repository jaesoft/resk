CREATE TABLE users (
  id UUID NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN DEFAULT TRUE,
  accountExpired BOOLEAN DEFAULT FALSE,
  accountLocked BOOLEAN DEFAULT FALSE,
  passwordExpired BOOLEAN DEFAULT FALSE,
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

INSERT INTO users(id, email, username, password) values('2c9de2b9-d9e1-40b4-b0b7-2d60900a7a38', 'admin@resk.io', 'admin', '$2a$06$dOc5Awjqyq/mRLDf1fbdLOiG5KUbMelAP0B7Y0enkiSHR8Rwa.dGu');
INSERT INTO roles values('ROLE_ADMIN');
INSERT INTO user_role values('2c9de2b9-d9e1-40b4-b0b7-2d60900a7a38', 'ROLE_ADMIN');