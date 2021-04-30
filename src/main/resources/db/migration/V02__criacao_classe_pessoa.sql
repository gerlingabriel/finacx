create table pessoa (
	id bigint primary key auto_increment,
    nome varchar(25) not null,
    ativo boolean not null default true,
    logradouro varchar(40),
    numero int,
    complemento varchar(50),
    bairro varchar(30),
    cep varchar(11),
    cidade varchar(25),
    estado varchar(20)
);

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Gabriel', false, 'Rua Ademar Luiz Nepomoceno', '150', 'Bloco D apt 302', 'Jardim Camburi', '29090520', 'Vitória', 'Espírito Santo');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Bárbara', true, 'Rua Eng Luiz', '03', 'Apt 401', 'Jardim Camburi', '2909420', 'Vitória', 'Espírito Santo');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) values ('Rafael', true, 'Rua Ademar Luiz Nepomoceno', '03', 'Apt 306', 'Jardim Camburi', '2909420', 'Vitória', 'Espírito Santo');