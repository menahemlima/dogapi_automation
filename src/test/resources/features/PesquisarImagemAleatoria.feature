# language: pt

@regressao @imagemAleatoria
Funcionalidade: Consulta de imagem aleatória de cachorro usando a Dog API

	@imagemAleatoriaCachorro
  Cenário: Obter uma imagem aleatória de cachorro
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para buscar uma imagem aleatória
    Então o response deve retornar o status code 200
    E o campo 'status' deve ser o valor "success"
    E a URL da imagem do cachorro deve ser válida
    E essa imagem deve corresponder a uma raça existente
    
  @schema @schemaListarImagemAleatoria  
  Cenário: Validar schema da imagem aleatória
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para buscar uma imagem aleatória
    Então o schema da resposta deve ser válido para "imagemAleatoriaSchema.json"
    
