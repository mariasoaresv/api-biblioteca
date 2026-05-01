INSERT INTO autor (id, nome, nacionalidade, data_nascimento) VALUES (100, 'Clarice Lispector', 'Brasileira', '1920-12-10');

INSERT INTO categoria (id, nome, descricao) VALUES (100, 'Romance', 'Livros do Gênero Romance');
INSERT INTO categoria (id, nome, descricao) VALUES (101, 'Terror', 'Livros do Gênero Terror');

INSERT INTO livro (id, titulo, ano_publicacao, descricao, capa, autor, categoria) VALUES (100, 'A Hora da Estrela', 1977, 'O romance narra a história da datilógrafa alagoana, Macabéa', 'https://m.media-amazon.com/images/I/61TaHURu27L._SY342_.jpg', 100, 100);