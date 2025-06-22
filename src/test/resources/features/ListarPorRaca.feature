# language: pt
@regressao @listarRaca
Funcionalidade: Consulta de imagens por raça

  @listarPorRaca
  Esquema do Cenário: Obter imagens de uma raça específica
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para obter imagens da raça "<raça>"
    Então o response deve retornar o status code 200
    E o campo 'status' deve ser o valor "success"
    E a resposta deve conter a Url da imagem para a raça "<raça>"

    Exemplos: 
      | raça     |
      | papillon |
      | husky    |
      | shiba    |
      | beagle   |
      | mix      |

  @listarRacaInvalida @excecao
  Esquema do Cenário: Tentar obter imagem de uma raça inválida
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para obter imagens da raça "<raça>"
    Então o response deve retornar o status code 404
    E o campo 'status' deve ser o valor "error"
    E a resposta deve conter a mensagem de erro "<Mensagem de erro>"

    Exemplos: 
      | raça      | Mensagem de erro 															|
      | vira-lata | Breed not found (master breed does not exist) |


  @schema @schemaListarPorRaca
  Esquema do Cenário: Validar schema do endpoint listar por raça
    Dado que a api DogAPI está disponível
    Quando eu enviar uma requisição para obter imagens da raça "<raça>"
    Então o schema da resposta deve ser válido para "imagemPorRacaSchema.json"

    Exemplos: 
      | raça    |
      | pitbull |
