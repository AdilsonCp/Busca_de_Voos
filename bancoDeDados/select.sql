select  pessoas.nome, pessoas.cpf, pessoas.genero, pessoas.data_nascimento, 
		contato.telefone, 
		endereco.cep, endereco.endereco, endereco.numero, endereco.complemento, endereco.bairro, endereco.cidade, endereco.uf,
		credenciais.email, credenciais.senha
		from pessoas inner join contato on pessoas.pessoa_id = contato.pessoa_id
					 inner join endereco on pessoas.pessoa_id = endereco.pessoa_id
					 inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id;
					
select pessoas.nome, pessoas.pessoa_id
		from pessoas inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id
        where email = "maria@hotmail.com" and senha = "Horto123" ;
                     
                     

select empresa,date_format(data_horario_partida,"%d/%m/%Y - %Hh:%mm") as data_horario_partida, pais_origem, cidade_origem, pais_destino, cidade_destino,preco 
	from rota_aereas where data_horario_partida >= utc_timestamp();


    
#select date_format(now(),"%d/%m/%Y - %h:%m");


