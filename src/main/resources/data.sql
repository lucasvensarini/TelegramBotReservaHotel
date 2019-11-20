-- CIDADES
INSERT INTO CIDADE VALUES (1, 'São Paulo', 'sao paulo');
INSERT INTO CIDADE VALUES (2, 'Rio de Janeiro', 'rio de janeiro');
INSERT INTO CIDADE VALUES (3, 'Fortaleza', 'fortaleza');

-- ENDEREÇOS
INSERT INTO ENDERECO VALUES (1, 'Morumbi', 'São Paulo', 'Rua morumbi', 101, 'Brasil', 1);
INSERT INTO ENDERECO VALUES (2, 'Mooca', 'São Paulo', 'Rua da mooca', 58, 'Brasil', 1);
INSERT INTO ENDERECO VALUES (3, 'Leblon', 'Rio de Janeiro', 'Rua leblonesca', 213, 'Brasil', 2);
INSERT INTO ENDERECO VALUES (4, 'Barra da Tijuca', 'Rio de Janeiro', 'Avenida da barra', 3050, 'Brasil', 2);
INSERT INTO ENDERECO VALUES (5, 'Botafogo', 'Rio de Janeiro', 'Rua da fogueira', 1012, 'Brasil', 2);
INSERT INTO ENDERECO VALUES (6, 'Mucuripe', 'Ceará', 'Rua fortaleza', 1012, 'Brasil', 3);

-- HOTÉIS
INSERT INTO HOTEL VALUES (1, 'CINCO_ESTRELAS', PARSEDATETIME('12:00', 'HH:mm'), PARSEDATETIME('18:00', 'HH:mm'),
'Meridiano', 'http://www.cvc.com.br/images/208063.jpg', 1);

INSERT INTO HOTEL VALUES (2, 'QUATRO_ESTRELAS', PARSEDATETIME('10:00', 'HH:mm'), PARSEDATETIME('18:00', 'HH:mm'),
'Ibis', 'http://www.cvc.com.br/images/188178.jpg', 2);

INSERT INTO HOTEL VALUES (3, 'TRES_ESTRELAS', PARSEDATETIME('12:00', 'HH:mm'), PARSEDATETIME('19:00', 'HH:mm'),
'Social', 'http://img.cvc.com.br/images/188189.jpg', 3);

INSERT INTO HOTEL VALUES (4, 'DUAS_ESTRELAS', PARSEDATETIME('12:00', 'HH:mm'), PARSEDATETIME('20:00', 'HH:mm'),
'Best Western', 'http://img.cvc.com.br/images/188186.jpg', 4);

INSERT INTO HOTEL VALUES (5, 'UMA_ESTRELA', PARSEDATETIME('09:00', 'HH:mm'), PARSEDATETIME('18:00', 'HH:mm'),
'Recanto', 'http://www.cvc.com.br/images/208063.jpg', 5);

INSERT INTO HOTEL VALUES (6, 'CINCO_ESTRELAS', PARSEDATETIME('12:00', 'HH:mm'), PARSEDATETIME('22:00', 'HH:mm'),
'Mercure', 'http://img.cvc.com.br/images/188186.jpg', 6);

-- QUARTOS DO HOTEL 1
INSERT INTO QUARTO VALUES (1, 1, 1, 'SIMPLES', 'http://www.cvc.com.br/images/208063.jpg', 80);
INSERT INTO QUARTO VALUES (2, 2, 1, 'CASAL', 'http://img.cvc.com.br/images/188186.jpg', 170);
INSERT INTO QUARTO VALUES (3, 4, 1, 'FAMILIA', 'http://img.cvc.com.br/images/188189.jpg', 300);

-- QUARTOS DO HOTEL 2
INSERT INTO QUARTO VALUES (4, 1, 2, 'SIMPLES', 'http://www.cvc.com.br/images/208063.jpg', 70);
INSERT INTO QUARTO VALUES (5, 2, 2, 'CASAL', 'http://img.cvc.com.br/images/188186.jpg', 180);
INSERT INTO QUARTO VALUES (6, 3, 2, 'LUXO', 'http://www.cvc.com.br/images/188178.jpg', 240);

-- QUARTOS DO HOTEL 3
INSERT INTO QUARTO VALUES (7, 1, 3, 'SIMPLES', 'http://www.cvc.com.br/images/208063.jpg', 80);
INSERT INTO QUARTO VALUES (8, 2, 3, 'CASAL', 'http://img.cvc.com.br/images/188186.jpg', 170);
INSERT INTO QUARTO VALUES (9, 4, 3, 'FAMILIA', 'http://img.cvc.com.br/images/188189.jpg', 310);

-- QUARTOS DO HOTEL 4
INSERT INTO QUARTO VALUES (10, 1, 4, 'SIMPLES', 'http://www.cvc.com.br/images/208063.jpg', 85);
INSERT INTO QUARTO VALUES (11, 2, 4, 'CASAL', 'http://img.cvc.com.br/images/188186.jpg', 175);
INSERT INTO QUARTO VALUES (12, 4, 4, 'FAMILIA', 'http://img.cvc.com.br/images/188189.jpg', 305);

-- QUARTOS DO HOTEL 5
INSERT INTO QUARTO VALUES (13, 1, 5, 'SIMPLES', 'http://www.cvc.com.br/images/208063.jpg', 80);
INSERT INTO QUARTO VALUES (14, 2, 5, 'CASAL', 'http://img.cvc.com.br/images/188186.jpg', 170);
INSERT INTO QUARTO VALUES (15, 4, 5, 'FAMILIA', 'http://img.cvc.com.br/images/188189.jpg', 300);

-- QUARTOS DO HOTEL 6
INSERT INTO QUARTO VALUES (16, 1, 6, 'SIMPLES', 'http://www.cvc.com.br/images/208063.jpg', 80);
INSERT INTO QUARTO VALUES (17, 2, 6, 'CASAL', 'http://img.cvc.com.br/images/188186.jpg', 170);
INSERT INTO QUARTO VALUES (18, 3, 6, 'LUXO', 'http://www.cvc.com.br/images/188178.jpg', 220);
INSERT INTO QUARTO VALUES (19, 4, 6, 'FAMILIA', 'http://img.cvc.com.br/images/188189.jpg', 300);

-- USUARIO
-- TODO - substituir o 0 pelo id de usuário do telegram
INSERT INTO USUARIO VALUES (1, 0);