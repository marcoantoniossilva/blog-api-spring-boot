CREATE SEQUENCE images_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1;

CREATE TABLE images (
  id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('images_seq'),
  post_id INT CHECK (post_id > 0),
  album_id INT CHECK (post_id > 0),
  file_name VARCHAR(120) NOT NULL,
  type VARCHAR(120) NOT NULL,
  content bytea NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT post_id_fk FOREIGN KEY (post_id) REFERENCES posts (id),
  CONSTRAINT album_id_fk FOREIGN KEY (album_id) REFERENCES albums (id)
);