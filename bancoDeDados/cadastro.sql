drop database if exists cadastro;
create database cadastro;

use cadastro;

#################################### DADOS USUARIO #####################################
create table pessoas(
	pessoa_id int primary key auto_increment,
    nome varchar(100) not null,
    cpf char(11) not null,
    genero varchar(20) not null,
    data_nascimento datetime not null
);
create unique index idx_cpf on pessoas(cpf);

#################################### Endereço #####################################
create table endereco(
	endereco_id int primary key auto_increment,
    pessoa_id int not null,
	cep varchar(8) not null,
    endereco varchar(100) not null,
    numero varchar(4) not null,
    complemento varchar(100),
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(20) not null,
	foreign key(pessoa_id) references pessoas(pessoa_id)
		on update cascade
        on delete restrict
);

#################################### Credenciais #####################################
create table credenciais(
	id_credenciais int primary key auto_increment,
    pessoa_id int not null,
    email varchar(100) not null,
    senha varchar(100) not null,
	foreign key(pessoa_id) references pessoas(pessoa_id)
		on update cascade
        on delete restrict
);
create unique index idx_email on credenciais(email);

#################################### Contato #####################################
create table contato(
	contato_id int primary key auto_increment,
    pessoa_id int not null,
    telefone varchar(11) not null,
    email varchar(100) not null,
    foreign key(pessoa_id) references pessoas(pessoa_id),
    foreign key(email) references credenciais(email)
		on update cascade
        on delete restrict
);

################################## Paises ######################################################
create table paises(
	id_paises int primary key auto_increment,
    pais varchar(100) not null
);
create unique index idx_pais on paises(pais);

################################## Cidades ######################################################
create table cidades(
	id_cidades int primary key auto_increment,
    id_paises int not null,
    cidade varchar(100) not null,
    foreign key(id_paises) references paises(id_paises)
		on update cascade
        on delete restrict
);
create unique index idx_cidade on cidades(cidade);

################################## Empresas ######################################################
create table companhias_aerea(
	id_companhias int primary key auto_increment,
    nome_companhia varchar(50) not null
);
create unique index idx_companhias_aerea on companhias_aerea(nome_companhia);

################################## Preços ######################################################
create table valores(
	id_preco int primary key auto_increment,
    valor float
);
create unique index idx_valores on valores(valor);

################################## Horario de partida ######################################################
create table data_horario_partida(
	id_horario int primary key auto_increment,
    data_hora datetime not null
);
create unique index idx_data_horario_partida on data_horario_partida(data_hora);

################################## Rotas aereas ######################################################
create table rota_aereas(
	id_rotas_aereas int primary key auto_increment,
    empresa varchar(100) not null,
    data_horario_partida datetime not null,
    pais_origem varchar(100) not null,
    cidade_origem varchar(100) not null,
	pais_destino varchar(100)  not null,
    cidade_destino varchar(100) not null,
    preco float not null,
    foreign key(pais_destino) references paises(pais),
    foreign key(cidade_destino) references cidades(cidade),
    foreign key(pais_origem) references paises(pais),
    foreign key(cidade_origem) references cidades(cidade),
    foreign key(empresa) references companhias_aerea(nome_companhia),
    foreign key(data_horario_partida) references data_horario_partida(data_hora),
    foreign key(preco) references valores(valor)
		on update cascade
        on delete restrict
);
#################################################################################
create table poltrona(
	id_poltrona int primary key auto_increment,
    id_rotas_aereas int not null,
    a1 int, a2 int, a3 int, a4 int, a5 int, a6 int,
    b1 int, b2 int, b3 int, b4 int, b5 int, b6 int,
    c1 int, c2 int, c3 int, c4 int, c5 int, c6 int,
    d1 int, d2 int, d3 int, d4 int, d5 int, d6 int,
    foreign key(id_rotas_aereas) references rota_aereas(id_rotas_aereas)
		on update cascade
        on delete restrict
);

create table passageiros(
	id_passageiro int primary key auto_increment,
    id_rotas_aereas int not null,
	pessoa_id int not null,
    assento varchar(3) not null,
    foreign key(pessoa_id) references pessoas(pessoa_id),
    foreign key(id_rotas_aereas) references rota_aereas(id_rotas_aereas)
		on update cascade
        on delete restrict
);


insert into pessoas(nome, cpf, genero, data_nascimento) values ('Eduardo', '86229821060', 'Masculino',"2003-02-16 00:00:00");
insert into endereco(pessoa_id, cep, endereco, numero, complemento, bairro, cidade, uf )
	values(1, '13199635','Avenida Senador Saraiva','32',null,'Cambui','Campinas','São Paulo');
insert into credenciais(pessoa_id, email, senha) values (1,'eduardo.edu@gmail.com','Eduardo1234');
insert into contato(pessoa_id, telefone, email) values (1,'19987457892','eduardo.edu@gmail.com');


insert into pessoas(nome, cpf, genero, data_nascimento) values ('Maria', '58769720029', 'Feminino',"2016-12-16 00:00:00");
insert into endereco(pessoa_id, cep, endereco, numero, complemento, bairro, cidade, uf ) 
	values(2, '13192635','Avenida Brasil','76',null,'Jardim Amanda','Hortolândia','São Paulo');
insert into credenciais(pessoa_id, email, senha) values (2,'maria@hotmail.com','Horto123');
insert into contato(pessoa_id, telefone, email) values (2,'19984558202','maria@hotmail.com');


insert into pessoas(nome, cpf, genero, data_nascimento) values ('Eder', '20758804091', 'Masculino',"2013-02-16 00:00:00");
insert into endereco(pessoa_id, cep, endereco, numero, complemento, bairro, cidade, uf ) 
	values(3, '13197798','Rua Almeida','875','Bloco A','Oreste Ongaro','Hortolândia','São Paulo');
insert into credenciais(pessoa_id, email, senha) values (3,'eder.araujo@gmail.com','araujo@13');
insert into contato(pessoa_id, telefone, email) values (3,'19984557788','eder.araujo@gmail.com');

                  