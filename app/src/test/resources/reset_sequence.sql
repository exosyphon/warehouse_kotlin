alter sequence warehouse_id_seq minvalue 0 start with 1;
SELECT setval('warehouse_id_seq', 0);