CREATE TABLE tbcad_volume_log
(
  id bigserial NOT NULL,
  idlog bigserial NOT NULL primary key,
  status integer,
  colaborador character varying(255),
  data_alteracao date,
  observacao character varying(255), 
  usuario character varying(255)
);

CREATE OR REPLACE FUNCTION oldvolume() RETURNS TRIGGER AS '
BEGIN
  INSERT INTO tbcad_volume_log (id, status, colaborador, data_alteracao, usuario) 
     VALUES (OLD.id, OLD.status, OLD.colaborador, CURRENT_TIMESTAMP, session_user );
  RETURN NEW;
END
'LANGUAGE plpgsql;

CREATE TRIGGER toldvolume AFTER UPDATE
ON tbcad_volume FOR EACH ROW
  EXECUTE PROCEDURE oldvolume();
