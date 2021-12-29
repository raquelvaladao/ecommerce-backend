CREATE TABLE tb_partido(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
sigla VARCHAR(255) NOT NULL
);

CREATE TABLE tb_cargo(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_politico(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
cpf VARCHAR(255) NOT NULL,
telefone VARCHAR(255) NOT NULL,
foto VARCHAR(255),
logradouro VARCHAR(255) NOT NULL,
complemento VARCHAR(255) NOT NULL,
numero VARCHAR(255) NOT NULL,
lider INT NOT NULL,
cep VARCHAR(255) NOT NULL,
projetos INT NOT NULL,
processos INT NOT NULL,
partido_id BIGINT,
cargo_id BIGINT,
FOREIGN KEY (partido_id) REFERENCES tb_partido(id),
FOREIGN KEY (cargo_id) REFERENCES tb_cargo(id)
);

INSERT INTO `tb_partido` (`id`, `nome`, `sigla`) VALUES (1, 'Partido dos Trabalhadores', 'PT');
INSERT INTO `tb_partido` (`id`, `nome`, `sigla`) VALUES (2, 'Partido Social Liberal', 'PSL');
INSERT INTO `tb_partido` (`id`, `nome`, `sigla`) VALUES (3, 'Partido Socialista do Brasil', 'PCdoB');
INSERT INTO `tb_partido` (`id`, `nome`, `sigla`) VALUES (4, 'Partido Democrata Trabalhista', 'PDT');
INSERT INTO `tb_partido` (`id`, `nome`, `sigla`) VALUES (5, 'Partido Socialismo e Liberdade', 'PSOL');

INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (1, 'PRESIDENTE');
INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (2, 'SENADOR');
INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (3, 'DEPUTADO');
INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (4, 'MINISTRO');
INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (5, 'VEREADOR');
INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (6, 'GOVERNADOR');
INSERT INTO `tb_cargo` (`id`, `nome`) VALUES (7, 'PREFEITO');

INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `partido_id`, `cargo_id`, `processos`) VALUES (2, 'Nick Domus', '111.422.100-73', '123 123 123 32', 'perfilM4.jpg', 'Nome da rua', 'Nao', '32', 1, '76962-272', 46, 1, 1, 9);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `partido_id`, `cargo_id`, `processos`) VALUES (3, 'Emmy Rossum', '508.033.397-98', '(83) 2759-4110', 'perfilM4.jpg', 'Rosenbaum Prairie', 'Nao', '91949', 0, '06845-175', 46, 1, 1, 9);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `partido_id`, `cargo_id`, `processos`) VALUES (4, 'Jai Courtney', '317.766.230-45', '(89) 3988-5056', 'perfilH4.jpg', 'Kassulke Extensions', 'Cormier Vista Suite', '713', 0, '73809-240', 46, 2, 2, 9);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `partido_id`, `cargo_id`, `processos`) VALUES (5, 'Jamie Dornan', '366.793.658-30', '(45) 2661-2591', 'perfilH3.jpg', 'Lakin Cliffs', 'Apt. 774', '7632', 0, '59147-060', 9, 2, 3, 9);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `partido_id`, `cargo_id`, `processos`) VALUES (6, 'Alex Pettyfer', '721.034.909-08', '(48) 2389-7059', 'perfilM3.jpg', 'Mitchell Grove Suite', 'Mount Vernon, WY', '6405', 0, '69301-290', 32, 2, 7, 9);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (9, 'Daniela Emilly Neves', '836.727.365-62', '(86) 98972-3306', 'perfilH2.jpg', 'Rua do Triângulo', 'Não', '91', 0, '64028-285', 93, 5, 2, 4);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (10, 'Caleb Filipe Ferreira', '809.314.915-50', '(27) 99518-8384', 'perfilH2.jpg', 'Rua Araras', 'Não', '145', 1, '29146-706', 44, 13, 3, 2);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (11, 'Jaqueline Kamilly das Neves', '551.058.666-49', '(82) 99760-0850', 'perfilM6.png', 'Rua Virgílio Maurício da Rocha', 'Não', '619', 0, '57018-880', 31, 13, 3, 2);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (12, 'Cauê Guilherme Silva', '502.978.785-22', '(69) 99869-5908', 'perfilM6.png', 'Rua Waldemar Servilhere', 'Não', '168', 0, '76913-891', 31, 13, 3, 5);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (13, 'Breno Mateus Melo', '760.856.615-92', '(98) 99380-5926', 'perfilM6.png', 'Rua Trinta e Cinco', 'Não', '704', 0, '65054-846', 11, 2, 3, 5);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (14, 'Carlos Eduardo Danilo Ian Duarte', '940.607.274-23', '(98) 99380-5926', 'perfilM6.png', 'Rua Trinta e Cinco', 'Não', '73', 0, '45003-310', 18, 22, 3, 5);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (15, 'Davi Lucca Barbosa', '211.108.941-98', '(63) 98487-3105', 'perfilM6.png', 'Quadra 108 Norte', 'Não', '73', 0, '45003-310', 18, 22, 3, 3);
INSERT INTO `tb_politico` (`id`, `nome`, `cpf`, `telefone`, `foto`, `logradouro`, `complemento`, `numero`, `lider`, `cep`, `projetos`, `processos`, `partido_id`, `cargo_id`) VALUES (16, 'Pedro Henrique Isaac Castro', '250.117.628-66', '(69) 99980-4954', 'perfilM6.png', 'Rua José Fiorotti', 'Não', '12', 0, '76913-868', 10, 22, 2, 4);
