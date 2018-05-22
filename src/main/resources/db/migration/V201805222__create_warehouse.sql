CREATE SEQUENCE warehouse_id_seq;

CREATE TABLE warehouse (
  id integer NOT NULL DEFAULT nextval('warehouse_id_seq') PRIMARY KEY,
  name text,
  address text,
  city text,
  state text,
  zip text
);

ALTER SEQUENCE warehouse_id_seq
OWNED BY warehouse.id;