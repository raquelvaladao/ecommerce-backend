CREATE TABLE tb_produto(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    medida VARCHAR(255) NOT NULL,
    foto VARCHAR(255),
    preco BIGINT NOT NULL,
    estoque INT NOT NULL,
    dono_id BIGINT,
    FOREIGN KEY (dono_id) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_compra(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valortotal BIGINT NOT NULL,
    comprador_id BIGINT,
    FOREIGN KEY (comprador_id) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_itemcompra(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valorunitario BIGINT NOT NULL,
    valortotal BIGINT NOT NULL,
    quantidade INT NOT NULL,
    compra_id BIGINT,
    produto_id BIGINT,
    FOREIGN KEY (compra_id) REFERENCES tb_compra(id),
    FOREIGN KEY (produto_id) REFERENCES tb_produto(id)
);

CREATE TABLE tb_pedido(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    datavenda VARCHAR(255) NOT NULL,
    total BIGINT NOT NULL,
    comprador_id BIGINT,
    FOREIGN KEY (comprador_id) REFERENCES tb_usuario(id)
);


INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (1, 'Camisa', 'Camisa boa para sair e etc.', 'Unidade', 'foto2.jpg', 121, 11, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (2, 'Mochila', 'Mochila muito boa para trilhas e etc', 'Unidade', 'foto1.jpg', 120, 123, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (3, 'Camisa Slim', 'Camisa azul slim masculina', 'Unidade', 'foto3.jpg', 90, 100, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (4, 'Pulseira', 'Pulseira prata com emblema de dragão masculina', 'Unidade', 'foto4.jpg', 121, 200, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (5, 'Casaco Roxo', 'Casaco de frio para inverno roxo unissex', 'Unidade', 'foto5.jpg', 80, 90, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (6, 'Camisa branca', 'Camisa feminina branca de mangas curtas', 'Unidade', 'foto6.jpg', 30, 100, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (7, 'Camisa vermelha', 'Camisa feminina vermelha de mangas curtas', 'Unidade', 'foto7.jpg', 30, 100, 1);
INSERT INTO `tb_produto` (`id`, `nome`, `descricao`, `medida`, `foto`, `preco`, `estoque`, `dono_id`) VALUES (8, 'Casaco jeans', 'Casaco feminino azul jeans longo', 'Unidade', 'foto8.jpg', 110, 100, 1);
