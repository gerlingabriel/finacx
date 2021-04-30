create table role(
    id BIGINT PRIMARY KEY AUTO_INCREMENT ,
    nome_role VARCHAR(20) not null
);

create table autorization(
    pessoa_id BIGINT NOT NULL ,
    role_id BIGINT not NULL ,

    constraint PK_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id),
    constraint PK_role FOREIGN KEY (role_id) REFERENCES role (id)
);



insert into role (nome_role) values ('ROLE_ADM');
insert into role (nome_role) values ('ROLE_USER');
insert into role (nome_role) values ('ROLE_FINAC');
insert into role (nome_role) values ('ROLE_RH');
insert into role (nome_role) values ('ROLE_PROG');


insert into autorization (pessoa_id, role_id) values (1,3);
insert into autorization (pessoa_id, role_id) values (2,1);
insert into autorization (pessoa_id, role_id) values (3,2);
insert into autorization (pessoa_id, role_id) values (2,4);