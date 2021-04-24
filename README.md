# gokuECommerce - backend

###Definição Funcional
Módulo Brontend para o projeto Goku e-commerce

Este micro-serviço foi desenvolvido em Java 8 e possui alguns frameworks como: 
- SpringBoot
- SpringSecurity
- Swagger
- Maven
- H2

Seu objetivo é disponilizar as funcionalidades requisitadas pelo Goku e-commerce via REST Api.
As funcionalidades desenvolvidas foram:

  - Login de acesso
  - Formulário de usuário para acesso ao sistema:
      - Cadastro
      - Edição
      - Exclusão
      - Busca por Id e nome
  - Formulário de endereços:
      - Cadastro
      - Edição
      - Exclusão
      - Busca de endereço cadastrado por CEP

A autenticação está sendo controlada pelo SpringSecurity, responsável por gerar e controlar os tokens de autorização. 

Para efeito de apresentação/testes, os dados cadastrados estão sendo persistidos no banco de dados H2 (em memória).

O micro-serviço possui uma cobertura de Cache, controlada pelo próprio Spring Boot. Caso fosse um sistema executando em
ambiente de produção estaria sendo utilizado um REDIS ou outra ferramenta mais robusta.

As APIs foram implementadas seguindo o método "contract-first", onde são geradas a partir de um contrato (.yaml) com as 
definições da API.

###Execução
Para executar o módulo, considerando um ambiente de desenvolvimento, deve-se primeiro ser feito o build com o maven, 
para isso use o goals:
- mvn clean install -U package

Se desejar executar a aplicação em alguma IDE (InteliJ, Eclipse), após realizar o build conforma indicado na etapa 
anterior, basta dar o comando Run na classe "GokuECommerceApplication".

Para executar via terminal de comando, deve navegar até a pasta target do projeto (gokuECommerce\target) e executar 
a aplicação com o comando abaixo:
- java -jar gokuECommerce-0.0.1-SNAPSHOT.jar
    
Para consumir os recursos das APIs, pode se usar o módulo Frontend (gokuECommerce.web) ou pode fazer as requisições 
usando alguma ferramenta como o via Postman ou Insonia na URL "http://localhost:9090".
    
O sistema já possui dois usuários cadastrados:
  - Perfil de Administrador:
      - username = admin
      - password = admin

- Perfil não Administrador:
      - username = teste
      - password = teste
      
Segue abaixo o cURL da requisição pra receber o token de autorização:

    curl -X POST \
      http://localhost:9090/auth \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 753dc112-b76f-6386-e5f4-4cc8f5a06b05' \
      -d '{
    	"username": "admin",
    	"password": "admin"
    }'  
    
Os demais recursos das APIs podem ser vistos no .yaml que se encontra aqui:
- https://github.com/herbdesen/gokuECommerce/blob/master/src/main/resources/api/gokuService.yaml
   
Para visualizar os recursos no Editor do Swagger:
- https://editor.swagger.io/    
    
        