CREATE SEQUENCE albums_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1;

  CREATE TABLE albums (
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('albums_seq'),
    user_id INT CHECK (user_id > 0) NOT NULL,
    name VARCHAR(200) NOT NULL,
    publicated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
  );