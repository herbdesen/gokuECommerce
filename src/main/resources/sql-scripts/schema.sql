CREATE TABLE TB_ROLE (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE TB_USER (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE USER_ROLE (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES TB_USER (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES TB_ROLE (id)
);

CREATE TABLE TB_ADDRESS (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    cep varchar(255) NOT NULL,
    logradouro varchar(255) NOT NULL,
    bairro varchar(255) NOT NULL,
    cidade varchar(255) NOT NULL,
    estado varchar(255) NOT NULL,
    numero varchar(255) NOT NULL,
    complemento varchar(255) NOT NULL
);

