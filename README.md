# Enterprise Connection BRQ

## Sobre

Desafio realizado pela equipe Trabalhadores para criar uma funcionalidade para a manutenção e busca de candidatos contactados pela equipe de atração da BRQ. 

O trabalho consiste em uma API REST e foi realizado com as tecnologias Java Spring Boot e Banco de dados PostgreSQL, com hospedagem no Heroku. Para testes foi utilizado o banco de dados h2. 

Nossa api possuí um mecanismo de busca personalizada, onde o usuário pode buscar por diversos campos opcionais,
e ordena os candidatos pelo candidatos com mais certificados condizentes quando buscado por uma skill. 

## Links e informações

### Link github : https://github.com/fhilips/BRQEnterpriseConnection

A url base do projeto: https://brq-enterprise.herokuapp.com/ ou http://localhost:8080 para ambiente local

## Endpoints

- Lista todos os candidato não ordenados
```
GET https://brq-enterprise.herokuapp.com/candidatos
```

- Busca personalizada por campos opcionais. Se nenhum campo for passado como parâmetro então
todos os candidatos serão devolvidos. Quando o campo 'nomeSkill' for informado, os candidatos 
com essa skill com mais certificados serão exibidos primeiro.

Campos disponíveis: nome, cpf, genero, nomeSkill, nomeCertificado, dataNasc, dataNascInicio e dataNascFim.

```
GET https://brq-enterprise.herokuapp.com/candidatos/buscarFiltrado
ex de requisição: http://localhost:8080/candidatos/buscarFiltrado?nome=Jefferson%20H&cpf=47953200803521224&genero=M&nomeSkill=Kanban&dataNasc=2021-12-05&dataNascInicio=2021-12-05&dataNascFim=2021-12-05
```

- Busca um candidato por pelo id 
```
GET https://brq-enterprise.herokuapp.com/candidatos/{id}
```

- Deleta todos os dados da base
```
POST https://brq-enterprise.herokuapp.com/candidatos/deleteAll
```

- Cadastra um novo candidato com suas skills e certificações
```
POST https://brq-enterprise.herokuapp.com/candidatos/novoCandidato
ex de corpo de requisiçao: 
{
    "nome": "Jefferson Henrique Sarti dos Santos 2",
    "email": "jeffersonsarti@gmail.com",
    "cpf": "758.132.540-72",
		"dataNascimento": "2021-12-05",
		"genero": "M",
		"telefone": "472648785",
    "skills": [
      {
        "nome": "Scrum",
        "certificados": [
          {
            "nome": "PSM I",
            "codigo": "abc-13344556-asdas-45245"
          }
        ]
      },
      {
        "nome": "Kanban",
        "certificados": [
          {
            "nome": "KMP I",
            "codigo": "abc-13344556-2342-45245"
          },
					{
            "nome": "KMP 2",
            "codigo": "abc-13344556-2342-23442"
          },
					{
            "nome": "KMP 3",
            "codigo": "abc-13344556-2342-34532"
          }
        ]
      }
    ]
  }

