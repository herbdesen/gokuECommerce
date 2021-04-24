INSERT INTO TB_ROLE (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User');
INSERT INTO TB_ROLE (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User');

INSERT INTO TB_USER (id, first_name, last_name, password, username) VALUES (1, 'Goku', 'Administrador', '$2a$10$mzdz9X1fXbRDHo1.tSsmy.fYvqYdlS1Yhal31QmggZjcD1zuW3ecO', 'admin');
INSERT INTO TB_USER (id, first_name, last_name, password, username) VALUES (2, 'Teste', 'No Admin', '$2a$10$GTn3jZVFJ5yWf2GllD.6OOuhU49HLsHokPugxoTmmxIOFtX38Oz/y', 'teste');

INSERT INTO USER_ROLE(user_id, role_id) VALUES(1,2);
INSERT INTO USER_ROLE(user_id, role_id) VALUES(2,1);

INSERT INTO TB_ADDRESS(id, cep, logradouro, bairro, cidade, estado, numero, complemento)
VALUES (1, '58424-223', 'Rua de exemplo', 'Bairro de exemplo', 'Cidade de exemplo', 'PB', '160A', 'Proximo do exemplo');



 