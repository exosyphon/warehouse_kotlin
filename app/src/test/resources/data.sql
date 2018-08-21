--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 9.6.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: warehouse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.warehouse (id, name, address, city, state, zip) VALUES (1, 'warehouse 1', '1234 Bellvedere Way', 'Tom Collins', 'TX', '79707');
INSERT INTO public.warehouse (id, name, address, city, state, zip) VALUES (2, 'warehouse 2', '665 1st St', 'San Francisco', 'CA', '90210');


--
-- Name: warehouse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.warehouse_id_seq', 2, true);


--
-- PostgreSQL database dump complete
--

