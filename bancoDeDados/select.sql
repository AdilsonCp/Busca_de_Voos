select  pessoas.nome, pessoas.cpf, pessoas.genero, pessoas.data_nascimento, 
		contato.telefone, 
		endereco.cep, endereco.endereco, endereco.numero, endereco.complemento, endereco.bairro, endereco.cidade, endereco.uf,
		credenciais.email, credenciais.senha
		from pessoas inner join contato on pessoas.pessoa_id = contato.pessoa_id
					 inner join endereco on pessoas.pessoa_id = endereco.pessoa_id
					 inner join credenciais on pessoas.pessoa_id = credenciais.pessoa_id;