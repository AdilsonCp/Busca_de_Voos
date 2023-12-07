/*--------------------------------------VIEW--------------------------------------------------*/
drop view if exists	historicoRotas;	
create view historicoRotas as
select empresa,date_format(data_horario_partida,"%d/%m/%Y - %Hh:%mm") as data_horario_partida, pais_origem, cidade_origem, pais_destino, 
cidade_destino,preco 
	from rota_aereas where data_horario_partida < utc_timestamp();
    
drop view if exists	pessoasCadastradas;	
create view pessoasCadastradas as
	select  pessoas.nome, pessoas.cpf, pessoas.genero, date_format(pessoas.data_nascimento,"%d/%m/%Y") as data_nascimento, 
		contato.telefone, 
		endereco.cep, endereco.endereco, endereco.numero, endereco.complemento, endereco.bairro, endereco.cidade, endereco.uf,
		credenciais.email, credenciais.senha
		from pessoas inner join contato on pessoas.pessoa_id = contato.pessoa_id
					 inner join endereco on pessoas.pessoa_id = endereco.pessoa_id
					 inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id;

drop view if exists	passageirosRotas;			
create view passageirosRotas as
select pessoas.nome, pessoas.cpf, rota_aereas.empresa, date_format(rota_aereas.data_horario_partida,"%d/%m/%Y - %Hh:%mm") as data_horario_partida,
 rota_aereas.pais_origem, rota_aereas.cidade_origem, rota_aereas.pais_destino, rota_aereas.cidade_destino, rota_aereas.preco, passageiros.assento
		from passageiros inner join pessoas on pessoas.pessoa_id = passageiros.pessoa_id
					     inner join rota_aereas on rota_aereas.id_rotas_aereas = passageiros.id_rotas_aereas;
	
                     

/*--------------------------------------TRIGGERS--------------------------------------------------*/
/* TRIGGER  poltronasVoo  está inicio do arquivo rotas*/

/* TRIGER atualizarAssento está no inicio do arquivo passageirosPoltronas*/

drop trigger if exists excluirPassageiro;
delimiter $$
create trigger excluirPassageiro after delete on passageiros 
for each row
begin
	case old.assento
		when "a1" then  update poltrona set a1 = null where id_rotas_aereas = old.id_rotas_aereas;
        when "a2" then  update poltrona set a2 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'a3' then  update poltrona set a3 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'a4' then  update poltrona set a4 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'a5' then  update poltrona set a5 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'a6' then  update poltrona set a6 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'b1' then  update poltrona set b1 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'b2' then  update poltrona set b2 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'b3' then  update poltrona set b3 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'b4' then  update poltrona set b4 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'b5' then  update poltrona set b5 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'b6' then  update poltrona set b6 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'c1' then  update poltrona set c1 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'c2' then  update poltrona set c2 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'c3' then  update poltrona set c3 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'c4' then  update poltrona set c4 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'c5' then  update poltrona set c5 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'c6' then  update poltrona set c6 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'd1' then  update poltrona set d1 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'd2' then  update poltrona set d2 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'd3' then  update poltrona set d3 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'd4' then  update poltrona set d4 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'd5' then  update poltrona set d5 = null where id_rotas_aereas = old.id_rotas_aereas;
        when 'd6' then  update poltrona set d6 = null where id_rotas_aereas = old.id_rotas_aereas;
	end case;
end
$$
delimiter ;









/*--------------------------------------PROCEDURES--------------------------------------------------*/
drop procedure if exists deletarPassageiro;
delimiter $$
create procedure deletarPassageiro(in passageiro int)
begin
		#select id_rotas_aereas, assento from passageiros where id_passageiro = passageiro; 
		delete from passageiros where id_passageiro = passageiro;
end
$$
delimiter ;
/*Executar trigger excluirPassageiro*/
set @idPassageiro = (select id_passageiro from passageiros where pessoa_id=7 and id_rotas_aereas=2);
call deletarPassageiro(@idPassageiro);

/*TESTE*/
select pessoas.pessoa_id, pessoas.nome, passageiros.id_rotas_aereas, passageiros.assento
	from passageiros inner join pessoas on pessoas.pessoa_id = passageiros.pessoa_id;

select *from poltrona;





/*PROCEDURE inserir_passageiro no arquivo passageiroPoltronas*/

/*--------------------------------------FUNCTIONS--------------------------------------------------*/
drop function if exists cadastroNaoViajante;
delimiter $$
create function cadastroNaoViajante() returns int deterministic
begin
	return (select count(*) from pessoas where pessoa_id not in (select pessoa_id from passageiros));
end$$
delimiter ;


select cadastroNaoViajante();



drop function if exists contarPassagens;
delimiter $$
create function contarPassagens(idPessoa int) returns int deterministic
begin
	select COUNT(pessoa_id) into idPessoa from passageiros where pessoa_id = idPessoa group by pessoa_id limit 1;
	return idPessoa;
end$$
delimiter ;
select contarPassagens(1);



/*--------------------------------------CURSORES--------------------------------------------------*/

drop procedure if exists cadastroPassageirros;
delimiter $$
create procedure cadastroPassageirros ()
begin
	declare lista varchar(2000) default '';
    declare email varchar(100);
    declare fim boolean default false;
    
    declare nomesEmail cursor for select distinct credenciais.email 
		from passageiros inner join credenciais on  credenciais.pessoa_id = passageiros.pessoa_id;
					
    declare continue handler for not found set fim = true;
    
    open nomesEmail;
    while fim = false do 
		fetch nomesEmail into email;
        
        set lista = concat(lista, email,',');
    end while;
    close nomesEmail;
    
    select lista as  lista_resultado;	
end$$
call cadastroPassageirros();


drop procedure if exists empresas;
delimiter $$
create procedure empresas ()
begin
	declare lista varchar(2000) default '';
    declare nome varchar(100);
    declare fim boolean default false;
    
    declare nomeEmpresa cursor for select distinct nome_companhia 
		from companhias_aerea;
	
    declare continue handler for not found set fim = true;
    
    open nomeEmpresa;
    while fim = false do 
		fetch nomeEmpresa into nome;
        
        set lista = concat(lista, nome,',');
    end while;
    close nomeEmpresa;
    
    select lista as  lista_resultado;	
end$$
call empresas();


/*SELECTS*/
select empresa,date_format(data_horario_partida,"%d/%m/%Y - %Hh:%mm") as data_horario_partida, pais_origem, cidade_origem, pais_destino, 
cidade_destino,preco  from rota_aereas
where data_horario_partida >= utc_timestamp() and pais_origem="BRASIL" and cidade_origem="SÃO PAULO" and preco <=3000;

