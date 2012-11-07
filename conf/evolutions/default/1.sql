# Shops scheme

# --- !Ups
CREATE SEQUENCE shop_id_sequence;

CREATE TABLE shop (
	id integer NOT NULL DEFAULT nextval('shop_id_sequence'),
	name varchar(255) NOT NULL,
	likely double NOT NULL
);

# ---!Downs
DROP TABLE shop;
DROP SEQUENCE shop_id_sequence;