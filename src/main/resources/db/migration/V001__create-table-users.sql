CREATE SEQUENCE users_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 2;

CREATE TABLE users (
  id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('users_seq'),
  name VARCHAR(120) NOT NULL,
  email VARCHAR(70) NOT NULL,
  password VARCHAR(64) NOT NULL,
  PRIMARY KEY (id)
);

