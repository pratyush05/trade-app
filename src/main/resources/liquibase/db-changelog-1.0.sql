--liquibase formatted sql

--changeset gpratyush:1.0.1
CREATE TABLE public.stock
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 ),
    symbol character varying NOT NULL,
    name character varying NOT NULL,
    price double precision,
    deleted timestamp without time zone,
    PRIMARY KEY (id)
)
--rollback DROP TABLE public.stock

--changeset gpratyush:1.0.2
ALTER TABLE public.stock OWNER to postgres

--changeset gpratyush:1.0.3
CREATE TABLE public.trader
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 ),
    name character varying NOT NULL,
    deleted timestamp without time zone,
    PRIMARY KEY (id)
)
--rollback DROP TABLE public.trader

--changeset gpratyush:1.0.4
ALTER TABLE public.trader OWNER to postgres

--changeset gpratyush:1.0.5
CREATE TABLE public.trade
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 ),
    type character varying NOT NULL,
    price double precision,
    shares integer,
    stockid integer,
    traderid integer,
    created timestamp without time zone,
    deleted timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (stockid)
        REFERENCES public.stock (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (traderid)
        REFERENCES public.trader (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
--rollback DROP TABLE public.trade

--changeset gpratyush:1.0.6
ALTER TABLE public.trade OWNER to postgres
