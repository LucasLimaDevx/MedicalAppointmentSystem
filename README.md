# MedicalAppointmentSystem

Sistema de agendamentos de consultamentos com Springboot, JPA e MySQL. Este projeto permite o agendamento de consultas de usuarios com seus respectivos médicos e departamento, podendo definir o estado da consulta como agendado, cancelado ou realizado.

## Tecnologias usadas:
- Java 21
- Spring boot
- Spring Web
- Spring Data JPA / Hibernet
- MySQL
- Maven

## Funcionalidades
- CRUD de pacientes, consultas, departamentos e doutores

## Configuração e execução
  1. Clone o repositório:
     
          git clone git@github.com:LucasLimaDevx/MedicalAppointmentSystem.git


  3. No arquivo application.yml configure o banco de dados (Observação: banco de dados já deve está criando no MySQL ou Postgres) com usuário e senha
     

          spring:
          application:
            name: MedicalAppointmentSystem
     
          datasource:
            driver-class-name: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://localhost:3306/(nome_do_banco_de_dados)?useTimezone=true&serverTimezone=UTC
            username: nome_do_usuario
            password: senha_do_usuário
        
          jpa:
            hibernate:
               ddl-auto: update
            properties:
                hibernate:
            show-sql: false
            open-in-view: false
     
    
 4. Use o seguinte comando para entrar na pasta:

        cd MedicalAppointmentSystem/MedicalAppointmentSystem

    
 7. Use o seguinte comando na raíz da pasta:

        mvn spring-boot:run

     
# Rotas da API
## Gerenciamento (/patient)

| Método | Rota                        | Descrição                                 |
|--------|-----------------------------|-------------------------------------------|
| POST   | /api/patient                | Cria um novo paciente                     |
| GET    | /api/patient                | Lista todos os pacientes                  |
| GET    | /api/patient/{id}           | Busca um paciente pelo ID                 |
| PUT    | /api/patient                | Atualiza todos os dados de um paciente    |
| DELETE | /api/patient/{id}           | Remove um paciente                        |

## Gerenciamento (/appointments)

| Método | Rota                              | Descrição                                  |
|--------|-----------------------------------|--------------------------------------------|
| POST   | /api/appointment                  | Cria um novo consulta                      |
| GET    | /api/appointment                  | Lista todas as consulta                    |
| GET    | /api/appointment/{id}             | Busca uma consulta pelo ID                 |
| PUT    | /api/appointment                  | Atualiza todos os dados de uma consulta    |
| DELETE | /api/appointment/{id}             | Remove uma consulta                        |

## Gerenciamento (/department)

| Método | Rota                             | Descrição                                     |
|--------|----------------------------------|-----------------------------------------------|
| POST   | /api/department                  | Cria um novo departamento                     |
| GET    | /api/department                  | Lista todos os departamentos                  |
| GET    | /api/department/{id}             | Busca um departamento pelo ID                 |
| PUT    | /api/department                  | Atualiza todos os dados de um departamento    |
| DELETE | /api/department/{id}             | Remove um departamento                        |

## Gerenciamento (/doctor)

| Método | Rota                         | Descrição                               |
|--------|------------------------------|-----------------------------------------|
| POST   | /api/doctor                  | Cria um novo doutor                     |
| GET    | /api/doctor                  | Lista todos os doutores                 |
| GET    | /api/doctor/{id}             | Busca um doutor pelo ID                 |
| PUT    | /api/doctor                  | Atualiza todos os dados de um doutor    |
| DELETE | /api/doctor/{id}             | Remove um doutor                        |

## Acesse a aplicação usando o POSTMAN

Aplicação estará disponível em http//:localhost:8080

## Autor

Lucas Lima Silva: https://www.linkedin.com/in/lucaslimaitz/
