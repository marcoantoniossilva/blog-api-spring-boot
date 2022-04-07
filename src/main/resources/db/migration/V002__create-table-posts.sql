CREATE SEQUENCE posts_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1;

  CREATE TABLE posts (
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('posts_seq'),
    user_id INT CHECK (user_id > 0) NOT NULL,
    title VARCHAR(200) NOT NULL,
    text TEXT NOT NULL,
    publicated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
  );