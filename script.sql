--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-10-30 19:04:35

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
-- TOC entry 225 (class 1259 OID 25211)
-- Name: address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.address (
    number integer NOT NULL,
    state character varying(2) NOT NULL,
    address_id bigint NOT NULL,
    cep character varying(255) NOT NULL,
    city character varying(255) NOT NULL,
    district character varying(255) NOT NULL,
    street character varying(255) NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 25202)
-- Name: address_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.address_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 226 (class 1259 OID 25218)
-- Name: agenda; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.agenda (
    agenda_id bigint NOT NULL,
    schedule_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 25203)
-- Name: agenda_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.agenda_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 227 (class 1259 OID 25223)
-- Name: company; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.company (
    address_id bigint NOT NULL,
    company_id bigint NOT NULL,
    password character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 218 (class 1259 OID 25204)
-- Name: company_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.company_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 228 (class 1259 OID 25230)
-- Name: court; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.court (
    number integer NOT NULL,
    company_id bigint NOT NULL,
    court_id bigint NOT NULL
);


--
-- TOC entry 229 (class 1259 OID 25237)
-- Name: court_image; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.court_image (
    court_id bigint NOT NULL,
    court_image_id bigint NOT NULL,
    url character varying(255) NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 25205)
-- Name: court_image_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.court_image_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 220 (class 1259 OID 25206)
-- Name: court_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.court_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 230 (class 1259 OID 25242)
-- Name: court_sport; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.court_sport (
    court_id bigint NOT NULL,
    sport_id bigint NOT NULL
);


--
-- TOC entry 215 (class 1259 OID 16434)
-- Name: court_sport_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.court_sport_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 231 (class 1259 OID 25245)
-- Name: favorite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.favorite (
    company_id bigint NOT NULL,
    favorite_id bigint NOT NULL,
    user_id bigint NOT NULL
);


--
-- TOC entry 221 (class 1259 OID 25207)
-- Name: favorite_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.favorite_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 232 (class 1259 OID 25250)
-- Name: schedule; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.schedule (
    available boolean NOT NULL,
    date date NOT NULL,
    end_time time(6) without time zone NOT NULL,
    start_time time(6) without time zone NOT NULL,
    court_id bigint NOT NULL,
    schedule_id bigint NOT NULL
);


--
-- TOC entry 222 (class 1259 OID 25208)
-- Name: schedule_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.schedule_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 233 (class 1259 OID 25255)
-- Name: sport; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.sport (
    sport_id bigint NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 223 (class 1259 OID 25209)
-- Name: sport_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.sport_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 224 (class 1259 OID 25210)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 234 (class 1259 OID 25260)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    user_id bigint NOT NULL,
    password character varying(100) NOT NULL,
    email character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 4670 (class 2606 OID 25217)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (address_id);


--
-- TOC entry 4672 (class 2606 OID 25222)
-- Name: agenda agenda_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.agenda
    ADD CONSTRAINT agenda_pkey PRIMARY KEY (agenda_id);


--
-- TOC entry 4674 (class 2606 OID 25229)
-- Name: company company_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (company_id);


--
-- TOC entry 4680 (class 2606 OID 25241)
-- Name: court_image court_image_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court_image
    ADD CONSTRAINT court_image_pkey PRIMARY KEY (court_image_id);


--
-- TOC entry 4676 (class 2606 OID 25236)
-- Name: court court_number_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court
    ADD CONSTRAINT court_number_key UNIQUE (number);


--
-- TOC entry 4678 (class 2606 OID 25234)
-- Name: court court_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court
    ADD CONSTRAINT court_pkey PRIMARY KEY (court_id);


--
-- TOC entry 4682 (class 2606 OID 25249)
-- Name: favorite favorite_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT favorite_pkey PRIMARY KEY (favorite_id);


--
-- TOC entry 4684 (class 2606 OID 25254)
-- Name: schedule schedule_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT schedule_pkey PRIMARY KEY (schedule_id);


--
-- TOC entry 4686 (class 2606 OID 25259)
-- Name: sport sport_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.sport
    ADD CONSTRAINT sport_pkey PRIMARY KEY (sport_id);


--
-- TOC entry 4688 (class 2606 OID 25266)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4696 (class 2606 OID 25307)
-- Name: favorite fka2lwa7bjrnbti5v12mga2et1y; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT fka2lwa7bjrnbti5v12mga2et1y FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4689 (class 2606 OID 25267)
-- Name: agenda fkaegk7ix8mk2ncdih2kl1awsig; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.agenda
    ADD CONSTRAINT fkaegk7ix8mk2ncdih2kl1awsig FOREIGN KEY (schedule_id) REFERENCES public.schedule(schedule_id);


--
-- TOC entry 4691 (class 2606 OID 25277)
-- Name: company fkgfifm4874ce6mecwj54wdb3ma; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.company
    ADD CONSTRAINT fkgfifm4874ce6mecwj54wdb3ma FOREIGN KEY (address_id) REFERENCES public.address(address_id);


--
-- TOC entry 4697 (class 2606 OID 25302)
-- Name: favorite fkgjcqqa0rt0cl61e0couipqrf1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.favorite
    ADD CONSTRAINT fkgjcqqa0rt0cl61e0couipqrf1 FOREIGN KEY (company_id) REFERENCES public.company(company_id);


--
-- TOC entry 4690 (class 2606 OID 25272)
-- Name: agenda fkk8w5rrpupqpsaij30p1o6xq1x; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.agenda
    ADD CONSTRAINT fkk8w5rrpupqpsaij30p1o6xq1x FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- TOC entry 4694 (class 2606 OID 25292)
-- Name: court_sport fkkcdryc214c4ost0phowmpmssw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court_sport
    ADD CONSTRAINT fkkcdryc214c4ost0phowmpmssw FOREIGN KEY (sport_id) REFERENCES public.sport(sport_id);


--
-- TOC entry 4695 (class 2606 OID 25297)
-- Name: court_sport fkmp7jf2qqvwt8x7ns4h3uqaiuf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court_sport
    ADD CONSTRAINT fkmp7jf2qqvwt8x7ns4h3uqaiuf FOREIGN KEY (court_id) REFERENCES public.court(court_id);


--
-- TOC entry 4692 (class 2606 OID 25282)
-- Name: court fknewvv7eunspe9ra51aupxaev9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court
    ADD CONSTRAINT fknewvv7eunspe9ra51aupxaev9 FOREIGN KEY (company_id) REFERENCES public.company(company_id);


--
-- TOC entry 4698 (class 2606 OID 25312)
-- Name: schedule fkouxv2f8odhsshr1vmf8d9mmku; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schedule
    ADD CONSTRAINT fkouxv2f8odhsshr1vmf8d9mmku FOREIGN KEY (court_id) REFERENCES public.court(court_id);


--
-- TOC entry 4693 (class 2606 OID 25287)
-- Name: court_image fkpawitio8mtcfdpyp89owy1bl7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.court_image
    ADD CONSTRAINT fkpawitio8mtcfdpyp89owy1bl7 FOREIGN KEY (court_id) REFERENCES public.court(court_id);


-- Completed on 2024-10-30 19:04:35

--
-- PostgreSQL database dump complete
--

