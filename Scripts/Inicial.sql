CREATE TABLE pais (
 pai_id INT NOT NULL,
 pai_nome VARCHAR(100),
 pai_sigla VARCHAR(2),
 pai_codigo VARCHAR(15)
);

ALTER TABLE pais ADD CONSTRAINT PK_pais PRIMARY KEY (pai_id);


CREATE TABLE pessoa (
 pes_id INT NOT NULL,
 pes_tipo_pessoa INT,
 pes_email VARCHAR(100),
 pes_data_cadastro DATE,
 pes_situacao INT
);

ALTER TABLE pessoa ADD CONSTRAINT PK_pessoa PRIMARY KEY (pes_id);


CREATE TABLE pessoa_fisica (
 fis_id INT NOT NULL,
 fis_cpf VARCHAR(14),
 fis_rg VARCHAR(20),
 fis_data_nascimento DATE,
 fis_sexo INT,
 fis_nome VARCHAR(100),
 pes_id INT
);

ALTER TABLE pessoa_fisica ADD CONSTRAINT PK_pessoa_fisica PRIMARY KEY (fis_id);


CREATE TABLE pessoa_juridica (
 jur_id INT NOT NULL,
 jur_cnpj VARCHAR(18),
 jur_ie VARCHAR(20),
 jur_nome_fantasia VARCHAR(100),
 jur_nome_razao VARCHAR(100),
 pes_id INT
);

ALTER TABLE pessoa_juridica ADD CONSTRAINT PK_pessoa_juridica PRIMARY KEY (jur_id);


CREATE TABLE telefone (
 tel_id INT NOT NULL,
 tel_numero VARCHAR(12),
 pes_id INT
);

ALTER TABLE telefone ADD CONSTRAINT PK_telefone PRIMARY KEY (tel_id);


CREATE TABLE unidade_federativa (
 uf_id INT NOT NULL,
 uf_nome VARCHAR(100),
 uf_sigla VARCHAR(2),
 uf_codigo VARCHAR(15),
 pai_id INT
);

ALTER TABLE unidade_federativa ADD CONSTRAINT PK_unidade_federativa PRIMARY KEY (uf_id);


CREATE TABLE usuario (
 usu_id INT NOT NULL,
 usu_nome VARCHAR(100) NOT NULL,
 usu_login VARCHAR(10) NOT NULL,
 usu_senha VARCHAR(10) NOT NULL,
 usu_email VARCHAR(100) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT PK_usuario PRIMARY KEY (usu_id);


CREATE TABLE cidade (
 cid_id INT NOT NULL,
 cid_nome VARCHAR(100),
 cid_data_fundacao DATE,
 cid_codigo VARCHAR(15),
 uf_id INT
);

ALTER TABLE cidade ADD CONSTRAINT PK_cidade PRIMARY KEY (cid_id);


CREATE TABLE distrito (
 dis_id INT NOT NULL,
 dis_inicio_periodo DATE,
 dis_fim_periodo DATE,
 dis_nome VARCHAR(100),
 cid_id INT
);

ALTER TABLE distrito ADD CONSTRAINT PK_distrito PRIMARY KEY (dis_id);


CREATE TABLE logradouro (
 log_id INT NOT NULL,
 log_nome VARCHAR(100),
 cid_id INT
);

ALTER TABLE logradouro ADD CONSTRAINT PK_logradouro PRIMARY KEY (log_id);


CREATE TABLE perfil_acesso (
 pa_id INT NOT NULL,
 pa_descricao VARCHAR(50) NOT NULL
);

ALTER TABLE perfil_acesso ADD CONSTRAINT PK_perfil_acesso PRIMARY KEY (pa_id);


CREATE TABLE usuario_periodo_acesso (
 upa_id INT NOT NULL,
 upa_inicio_periodo DATE,
 upa_fim_periodo DATE NOT NULL,
 usu_id INT,
 pa_id INT
);

ALTER TABLE usuario_periodo_acesso ADD CONSTRAINT PK_usuario_periodo_acesso PRIMARY KEY (upa_id);


CREATE TABLE bairro (
 bai_id INT NOT NULL,
 bai_nome VARCHAR(100),
 cid_id INT
);

ALTER TABLE bairro ADD CONSTRAINT PK_bairro PRIMARY KEY (bai_id);


CREATE TABLE endereco (
 end_id INT NOT NULL,
 end_cep VARCHAR(9),
 end_numero VARCHAR(10),
 end_complemento VARCHAR(50),
 bai_id INT,
 log_id INT
);

ALTER TABLE endereco ADD CONSTRAINT PK_endereco PRIMARY KEY (end_id);


CREATE TABLE faixa_cep (
 fc_id INT NOT NULL,
 fc_faixa_incial VARCHAR(10),
 fc_faixa_final VARCHAR(10),
 bai_id INT,
 log_id INT
);

ALTER TABLE faixa_cep ADD CONSTRAINT PK_faixa_cep PRIMARY KEY (fc_id);


CREATE TABLE item_acesso (
 ia_id CHAR(10) NOT NULL,
 ia_descricao CHAR(10),
 pa_id INT
);

ALTER TABLE item_acesso ADD CONSTRAINT PK_item_acesso PRIMARY KEY (ia_id);


CREATE TABLE pessoa_periodo_endereco (
 ppe_id INT NOT NULL,
 ppe_inicio_periodo DATE,
 ppe_fim_periodo DATE,
 end_id INT,
 pes_id INT
);

ALTER TABLE pessoa_periodo_endereco ADD CONSTRAINT PK_pessoa_periodo_endereco PRIMARY KEY (ppe_id);


ALTER TABLE pessoa_fisica ADD CONSTRAINT FK_pessoa_fisica_0 FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id);


ALTER TABLE pessoa_juridica ADD CONSTRAINT FK_pessoa_juridica_0 FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id);


ALTER TABLE telefone ADD CONSTRAINT FK_telefone_0 FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id);


ALTER TABLE unidade_federativa ADD CONSTRAINT FK_unidade_federativa_0 FOREIGN KEY (pai_id) REFERENCES pais (pai_id);


ALTER TABLE cidade ADD CONSTRAINT FK_cidade_0 FOREIGN KEY (uf_id) REFERENCES unidade_federativa (uf_id);


ALTER TABLE distrito ADD CONSTRAINT FK_distrito_0 FOREIGN KEY (cid_id) REFERENCES cidade (cid_id);


ALTER TABLE logradouro ADD CONSTRAINT FK_logradouro_0 FOREIGN KEY (cid_id) REFERENCES cidade (cid_id);


ALTER TABLE usuario_periodo_acesso ADD CONSTRAINT FK_usuario_periodo_acesso_0 FOREIGN KEY (usu_id) REFERENCES usuario (usu_id);
ALTER TABLE usuario_periodo_acesso ADD CONSTRAINT FK_usuario_periodo_acesso_1 FOREIGN KEY (pa_id) REFERENCES perfil_acesso (pa_id);


ALTER TABLE bairro ADD CONSTRAINT FK_bairro_0 FOREIGN KEY (cid_id) REFERENCES cidade (cid_id);


ALTER TABLE endereco ADD CONSTRAINT FK_endereco_0 FOREIGN KEY (bai_id) REFERENCES bairro (bai_id);
ALTER TABLE endereco ADD CONSTRAINT FK_endereco_1 FOREIGN KEY (log_id) REFERENCES logradouro (log_id);


ALTER TABLE faixa_cep ADD CONSTRAINT FK_faixa_cep_0 FOREIGN KEY (bai_id) REFERENCES bairro (bai_id);
ALTER TABLE faixa_cep ADD CONSTRAINT FK_faixa_cep_1 FOREIGN KEY (log_id) REFERENCES logradouro (log_id);


ALTER TABLE item_acesso ADD CONSTRAINT FK_item_acesso_0 FOREIGN KEY (pa_id) REFERENCES perfil_acesso (pa_id);


ALTER TABLE pessoa_periodo_endereco ADD CONSTRAINT FK_pessoa_periodo_endereco_0 FOREIGN KEY (end_id) REFERENCES endereco (end_id);
ALTER TABLE pessoa_periodo_endereco ADD CONSTRAINT FK_pessoa_periodo_endereco_1 FOREIGN KEY (pes_id) REFERENCES pessoa (pes_id);


