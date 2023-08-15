--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-08-15 23:35:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3342 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 58995)
-- Name: mst_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_item (
    item_id bigint NOT NULL,
    item_name character varying(255)
);


ALTER TABLE public.mst_item OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 58966)
-- Name: mst_item_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_item_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_item_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 58967)
-- Name: mst_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_role (
    role_id bigint NOT NULL,
    role_name character varying(255)
);


ALTER TABLE public.mst_role OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 58979)
-- Name: mst_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_role_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_role_seq OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 58988)
-- Name: mst_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mst_user (
    user_id bigint NOT NULL,
    password character varying(255),
    role_id bigint,
    token character varying(255),
    user_email character varying(255),
    user_name character varying(255)
);


ALTER TABLE public.mst_user OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 58980)
-- Name: mst_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mst_user_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mst_user_seq OWNER TO postgres;

--
-- TOC entry 3336 (class 0 OID 58995)
-- Dependencies: 219
-- Data for Name: mst_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_item (item_id, item_name) FROM stdin;
102	Algoritma Lanjut
103	Python Dasar
\.


--
-- TOC entry 3332 (class 0 OID 58967)
-- Dependencies: 215
-- Data for Name: mst_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_role (role_id, role_name) FROM stdin;
1	Admin
2	Super Admin
\.


--
-- TOC entry 3335 (class 0 OID 58988)
-- Dependencies: 218
-- Data for Name: mst_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mst_user (user_id, password, role_id, token, user_email, user_name) FROM stdin;
102	5f4dcc3b5aa765d61d8327deb882cf99	2	\N	borne@gmail.com	Jason Borne
103	5f4dcc3b5aa765d61d8327deb882cf99	1	eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXNzd29yZCIsImV4cCI6MTY5MjE5ODE2NH0.HXbE5rq5IFEmBLR9shkpvkUfmk-zLxIJusABylvTMI8nv_VLsdaqkyT_ZH6DWCFURoxhkhgOq1wU2cmvo_gMHA	bond@gmail.com	James Bond
\.


--
-- TOC entry 3343 (class 0 OID 0)
-- Dependencies: 214
-- Name: mst_item_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_item_seq', 151, true);


--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 216
-- Name: mst_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_role_seq', 101, true);


--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 217
-- Name: mst_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mst_user_seq', 201, true);


--
-- TOC entry 3188 (class 2606 OID 58999)
-- Name: mst_item mst_item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_item
    ADD CONSTRAINT mst_item_pkey PRIMARY KEY (item_id);


--
-- TOC entry 3184 (class 2606 OID 58971)
-- Name: mst_role mst_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_role
    ADD CONSTRAINT mst_role_pkey PRIMARY KEY (role_id);


--
-- TOC entry 3186 (class 2606 OID 58994)
-- Name: mst_user mst_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mst_user
    ADD CONSTRAINT mst_user_pkey PRIMARY KEY (user_id);


-- Completed on 2023-08-15 23:35:23

--
-- PostgreSQL database dump complete
--

