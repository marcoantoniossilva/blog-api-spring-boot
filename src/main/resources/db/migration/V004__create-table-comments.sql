CREATE SEQUENCE comments_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1;

  CREATE TABLE comments (
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('comments_seq'),
    user_id INT CHECK (user_id > 0) NOT NULL,
    post_id INT CHECK (post_id > 0) NOT NULL,
    publicated_at TIMESTAMP NOT NULL,
    text TEXT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT post_id_fk FOREIGN KEY (post_id) REFERENCES posts (id)
  );