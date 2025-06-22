
# 🐾 Projeto de Automação de Testes - Dog API

Automação de testes para validar os principais endpoints públicos da [Dog API](https://dog.ceo/dog-api/), utilizando **Java**, **RestAssured**, **Cucumber**, **JUnit 5** e **Cucumber-report**. O objetivo é garantir a confiabilidade dos dados, a estrutura do JSON e a funcionalidade da API.

---

## 📁 Estrutura do Projeto

```bash
dogapi_automation/
│
├── .github/
│   └── workflows/
│       └── dogapi_automation.yml      # Pipeline CI (GitHub Actions)
│
├── src/
│   └── test/
│       ├── java/com/dogapi/
│       │   ├── api/clients/           # Cliente da API
│       │   ├── api/endpoints/         # Enum com endpoints
│       │   ├── api/responses/         # Classes POJO das respostas
│       │   ├── config/                # Configuração e contexto
│       │   ├── hooks/                 # Hooks Cucumber
│       │   ├── runners/               # Test Runner (JUnit)
│       │   ├── stepDefinitions/       # Step definitions (Cucumber)
│       │   └── utils/                 # Classes utilitárias
│       └── resources/
│           ├── features/              # Features na linguagem Gherkin (Cucumber)
│           ├── schemas/               # Schemas JSON para validação
│           └── log4j2.xml             # Log configurado
│
├── pom.xml                            # Gerenciador de dependências (Maven)
└── README.md                          # Documentação
```

![Estrutura do projeto](https://github.com/user-attachments/assets/ebbcb840-19ef-4fa7-8d58-d9952a5b9db7)

---

## 🧪 Tecnologias Utilizadas

| Tecnologia         | Função                                     |
|--------------------|--------------------------------------------|
| Java 21            | Linguagem de desenvolvimento               |
| RestAssured        | Consumo de API REST                        |
| Cucumber (Gherkin) | Escrita de cenários de teste (BDD)         |
| JUnit 5            | Execução dos testes                        |
| Maven              | Build e gerenciamento de dependências      |
| Cucumber Report    | Geração do relatório de testes             |

---

## 🐶 APIs Testadas
**1. Listar todas as raças**
* Método: GET
* Endpoint: /breeds/list/all
* Objetivo: Validar se todas as raças estão presentes na resposta.
<br>

**2. Obter imagem aleatória de qualquer raça**
* Método: GET
* Endpoint: /breeds/image/random
* Objetivo: Verificar se a imagem retornada é uma URL válida.
<br>

**3. Obter imagem aleatória de uma raça específica**
* Método: GET
* Endpoint: /breed/{raça}/images/random
* Objetivo: Garantir que a imagem pertence à raça solicitada.


## 🎯 Técnicas de Teste Utilizadas

- **Técnica baseada em risco:** Os testes priorizam os cenários críticos de busca de raças da dogAPI.
- **Partição de Equivalência:** Utilizada para agrupar os valores válidos e inválidos das entradas da API, como raças existentes versus raças inexistentes.
- **Casos de Uso:** Baseados nos comportamentos esperados da aplicação a partir de interações reais com a Dog API.


## ✅ Funcionalidades Validadas

- 📋 Listagem de todas as raças disponíveis
- 📸 Imagem aleatória por raça
- 🐶 Imagem aleatória de qualquer raça
- 📄 Validação de estrutura via JSON Schema
- 🛑 Verificações de erro para chamadas inválidas

---

## 📝 Funcionalidades e Cenários

| Feature01                                                 | Cenários                                   |
|-----------------------------------------------------------|--------------------------------------------|
| Consulta de imagens por raça                              | Obter imagens de uma raça específica       |
| Consulta de imagens por raça                              | Tentar obter imagem de uma raça inválida   |
| Consulta de imagens por raça                              | Validar schema do endpoint listar por raça |

![Funcionalidade 01](https://github.com/user-attachments/assets/149c6e18-4dfd-4dcb-8280-9acfc250a298)

| Feature02                                                 | Cenários                                   |
|-----------------------------------------------------------|--------------------------------------------|
| Consulta da lista de imagens de Raças da Dog API          | Listar todas as raças de cachorros         |
| Consulta da lista de imagens de Raças da Dog API          | Validar uma raça e sub-raça na lista       |
| Consulta da lista de imagens de Raças da Dog API          | Verificar uma raça inválida na lista       |
| Consulta da lista de imagens de Raças da Dog API          | Validar schema da listagem de raças        |

![Funcionalidade 02](https://github.com/user-attachments/assets/87160aae-02dc-45f7-bb2f-aaa2526a80c6)

| Feature03                                                 | Cenários                                   |
|-----------------------------------------------------------|--------------------------------------------|
| Consulta de imagem aleatória de cachorro usando a Dog API | Obter uma imagem aleatória de cachorro     |
| Consulta de imagem aleatória de cachorro usando a Dog API | Validar schema da imagem aleatória         |

![Funcionalidade 03](https://github.com/user-attachments/assets/4f24ed4c-ddc5-4ebb-bc9f-95666c81b2b2)

---

## ▶️ Como Executar Localmente

### 1. Pré-requisitos

- Java 21+
- Maven 3.8+
- Git

### 2. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/dogapi-automation.git
cd dogapi-automation
```

### 3. Realizar o build do projeto

```bash
mvn clean install
```

### 4. Executar os testes

```bash
mvn clean test -Dcucumber.filter.tags="@regressao"
```

A tag abaixo também pode ser usada para executar todos os testes:
```bash
mvn clean test
```

### 5. Visualizar relatório dos testes
- O relatório de testes é gerado após a execução dos testes.
    - Acessar o caminho do diretório do projeto.
    - Abrir o arquivo cucumber-html-report.html em um navegador de sua preferência.
```bash
C:\Users\{user}\Documents\git\dogapi_automation\target\cucumber-reports
```

---


## 🏷️ Tags de Teste

- `@regressao` – Executa testes principais de funcionalidade
- `@schema` – Executa apenas testes de validação de schema JSON
- `@imagemAleatoria` – Executa todos os testes da feature "PesquisarImagemAleatoria.feature"
- `@listarTodos` – Executa todos os testes da feature "ListarTodasRacas.feature"
- `@listarRaca` – Executa todos os testes da feature "ListarPorRaca.feature"

---

## ⚙️ CI/CD - GitHub Actions
**O projeto possui uma pipeline configurada no GitHub com as seguintes etapas:**
1. Realiza checkout do código
2. Instala JDK 21
3. Baixa dependências via Maven
4. Executa os testes com Cucumber
5. Gera e armazena os relatórios

**Para executar o projeto no gitHub:**
1. Acessar o respositório: https://github.com/menahemlima/dogapi_automation
2. Clicar em Actions > workflow run
3. Clicar no workflow > Re-run all jobs.

Workflow: `.github/workflows/dogapi_automation.yml`

---

## 📊 Relatórios

- Relatório Cucumber: `target/cucumber-reports/`

![Resumo - Relatório](https://github.com/user-attachments/assets/b1ceb3ff-d101-4ed3-8e01-e757461ba2f7)

![Funcionalidade: Consulta imagens de uma raça específica](https://github.com/user-attachments/assets/710d5da4-0513-44fe-ae33-d6b7eff9b517)

![Funcionalidade: Consulta da lista de imagens de Raças da Dog API](https://github.com/user-attachments/assets/4ad81955-84b0-4562-baf0-328aa496f7f4)

![Funcionalidade: Consulta de imagem aleatória de cachorro usando a Dog API](https://github.com/user-attachments/assets/acdeeb7f-52a3-441d-b8b4-9a8da0a3e1f5)
---

## 📄 Links úteis: 

* Cucumber -  [https://cucumber.io/](https://cucumber.io/)
* Java - [https://www.java.com/pt-BR/download/](https://www.java.com/pt-BR/download/)
* Maven - [https://maven.apache.org/](https://maven.apache.org/)
* Junit - [https://junit.org/](https://junit.org/)
* RestAssured - [https://rest-assured.io/](https://rest-assured.io/)

