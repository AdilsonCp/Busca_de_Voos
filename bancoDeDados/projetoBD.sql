create view historicoRotas as
select empresa,date_format(data_horario_partida,"%d/%m/%Y - %Hh:%mm") as data_horario_partida, pais_origem, cidade_origem, pais_destino, cidade_destino,preco 
	from rota_aereas where data_horario_partida < utc_timestamp();
    
    
create view clientes as
	select  pessoas.nome, pessoas.cpf, pessoas.genero, pessoas.data_nascimento, 
		contato.telefone, 
		endereco.cep, endereco.endereco, endereco.numero, endereco.complemento, endereco.bairro, endereco.cidade, endereco.uf,
		credenciais.email, credenciais.senha
		from pessoas inner join contato on pessoas.pessoa_id = contato.pessoa_id
					 inner join endereco on pessoas.pessoa_id = endereco.pessoa_id
					 inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id;
                     


drop procedure contarPessoas;
delimiter $$
create procedure contarPessoas ()
begin
	select count(*)  from pessoas;
end
$$

call contarPessoas();