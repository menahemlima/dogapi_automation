# language: pt

@regressao @listarTodos
Funcionalidade: Consulta da lista de imagens de Raças da Dog API

 	@listarRacas
  Cenário: Listar todas as raças de cachorros
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para listar todas as raças
    Então o response deve retornar o status code 200
    E o campo 'status' deve ser o valor "success"
    E a lista de raças não deve estar vazia

	@validaRaca
  Cenário: Validar uma raça e sub-raça na lista
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para listar todas as raças
    Então o response deve retornar o status code 200
    E o campo 'status' deve ser o valor "success"
    E a lista de raças não deve estar vazia
    E na lista deve conter a raça "hound"
    E a raça "hound" deve conter a sub-raça "basset"

	@racaInvalida @excecao
  Cenário: Verificar uma raça inválida na lista
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para listar todas as raças
    Então o response deve retornar o status code 200
    E o campo 'status' deve ser o valor "success"
    E a lista de raças não deve estar vazia
    E não deve conter a raça "vira-lata"
    
  @schema @schemaListarTodasRacas
  Cenário: Validar schema da listagem de raças
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para listar todas as raças
    Então o schema da resposta deve ser válido para "listarTodasAsRacasSchema.json"
    
