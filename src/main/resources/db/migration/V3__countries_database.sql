-- SEQUENCE: public.country_sequence

-- DROP SEQUENCE IF EXISTS public.country_sequence;

CREATE SEQUENCE IF NOT EXISTS public.country_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.country_sequence
    OWNER TO postgres;

-- Table: public.countries

-- DROP TABLE IF EXISTS public.countries;

CREATE TABLE IF NOT EXISTS public.countries
(
    id bigint NOT NULL,
    capital character varying(255) COLLATE pg_catalog."default",
    code character varying(255) COLLATE pg_catalog."default",
    currency character varying(255) COLLATE pg_catalog."default",
    emoji character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT countries_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.countries
    OWNER to postgres;