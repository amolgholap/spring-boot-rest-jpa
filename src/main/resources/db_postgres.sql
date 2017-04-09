-- Table: public.book_category

-- DROP TABLE public.book_category;

CREATE TABLE public.book_category
(
  id smallint NOT NULL DEFAULT nextval('book_category_id_seq'::regclass),
  name character varying(255),
  CONSTRAINT book_category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.book_category
  OWNER TO postgres;

  -------------------
  
 -- Table: public.book

-- DROP TABLE public.book;

CREATE TABLE public.book
(
  id smallint NOT NULL DEFAULT nextval('book_id_seq'::regclass),
  name character varying(255),
  book_category_id smallint NOT NULL DEFAULT nextval('book_book_category_id_seq'::regclass),
  CONSTRAINT book_pkey PRIMARY KEY (id),
  CONSTRAINT book_book_category_id_fkey FOREIGN KEY (book_category_id)
      REFERENCES public.book_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.book
  OWNER TO postgres;
 