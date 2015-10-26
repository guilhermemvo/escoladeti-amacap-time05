CREATE TABLE cor
(
  id bigint NOT NULL,
  nome character varying(255) NOT NULL,
  CONSTRAINT pk_cor PRIMARY KEY (id),
  CONSTRAINT uc_nome UNIQUE (nome)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cor
  OWNER TO postgres;
