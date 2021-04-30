create table lancamento(
	id bigint primary key auto_increment,
    descricao varchar(50) not null,
    data_vencimento date not null,
    data_pagamento date,
    valor decimal(10,2) not null,
    observacao varchar(100), 
    tipo varchar(20) not null,
    id_categoria bigint not null,
    id_pessoa bigint not null,
    foreign key (id_categoria) references categoria(id),
    foreign key (id_pessoa) references pessoa(id)
	);
    
    
		insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Salário mensal', '2021-07-02', null, '1500.00', 'Salário de fulano', 'DESPESA', 7, 1);
    
        insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Aluguel do estabelecimento', '2021-01-01', null, '4500.00', 'Aluguel do estabelecimento', 'DESPESA', 4, 1);
    
        insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Mensalidade da empresa Vix', '2020-09-05', null, '900.00', 'Valor referente a prestação de serviço', 'RECEITA', 6, 2);
    
        insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Mensalidade da empresa BBB Sistema', '2021-05-15', null, '1800.00', 'Valor referente a prestação de serviço', 'RECEITA', 5, 3);
    
        insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Material de escritório', '2021-08-13', null, '750.00', 'Mouses, teclados, fones, cabos', 'DESPESA', 1, 3);
    
        insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Luz', '2021-05-02', '2021-05-02', '600.00', 'Luz de Maio 2021', 'DESPESA', 3, 1);
    
        insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa)
    values ('Laches e alimentos', '2021-03-03', null, '200.00', 'Café da manha, bolos de mêsversários', 'DESPESA', 2, 1);