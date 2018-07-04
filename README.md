# Projeto
  Proposta feita pela equipe da Uol, para testar os conhecimentos e habilidades na Linguagem Java. Projeto consiste em um cadastro de
  jogadores, onde os campos nome, e-mail e grupo são obrigatórios. De acordo com o grupo selecionado, é verificado se existe codinome
  disponíveis. Os codinomes estão disponíveis nos links abaixos:
  
https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml 
https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json

## Passos feitos
Faça um clone do projeto https://github.com/uolhost/test-backEnd-Java.git - OK<br/>
Crie um novo projeto dentro do seu GitHub (https://github.com) - OK<br/>
Desenvolva um sistema que atenda os casos de uso apresentados - OK<br/>
Para montar seu sistema, leve em consideração a arquitetura de referência dentro da pasta referência - OK<br/>
Criar uma interface em HTML que contenha um formulário para receber nome, e-mail e telefone - OK<br/>
Criar uma interface em HTML que liste os jogadores cadastrados por nome, e-mail, telefone, codinome e lista de referência - OK<br/>
Criar uma ou mais classes que faça(m) uma requisição HTTP para o arquivo referência “Liga da Justiça” - OK<br/>
Criar uma ou mais classes que faça(m) uma requisição HTTP para o arquivo referência “Os Vingadores” - OK<br/>
Criar uma ou mais classes que contenha(m) as regras para persistir e recuperar cadastros de jogadores - OK<br/>
Documente como o projeto deve ser iniciado para que possamos rodar sua aplicação - OK<br/>
Suba a sua proposta para o projeto que você criou no GitHub. Exemplo: https://github.com/seuNome - OK<br/>

## Tecnologias
 - Spring-boot
 - Spring-data-jpa
 - Spring-mvc
 - h2 (DB -> create-drop)
 - JUnit
 
 ### Execução do projeto
  - Para executar o projeto, basta apenas clonar o mesmo, importar na IDE utilizada e executar o springboot.<br/>
  - A rota da página inicial é: localhost:8080/home <br/>
      - A partir desta página, basta ir caminhando conforme deseja.
      
 #### Observações
 - Foi criado um REST inicialmente para realizar os testes (antes de criar as telas), e está disponível para utilização;<br/>
 - Não está no padrão 100% RESTFul pois era apenas para teste, mas acabei deixando;<br/>
 - EndPoints disponíveis: <br/>
     - GET /jogadores (listagem geral)<br/>
     - GET /jogadores/{id} (Busca um único jogador de acordo com o id)<br/>
     - POST /jogadores (cadastrar novo jogador, basta informar nome, email e o grupo -> VINGADORES OU LIGA_DA_JUSTICA)<br/>
     - REMOVE /jogadores/id (excluir um jogador)<br/>
