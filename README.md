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
- Cadastro de usuarios e doutores
- Criação de departamentos
- Agendamentos de consultas
  
# Rotas da API
## Gerenciamento (/users)

| Método | Rota                    | Descrição                                |
|--------|-------------------------|------------------------------------------|
| POST   | /users                  | Cria um novo usuário                     |
| GET    | /users                  | Lista todos os usuários                  |
| GET    | /users/{id}             | Busca um usuário pelo ID                 |
| PUT    | /users/{id}             | Atualiza todos os dados de um usuário    |
| DELETE | /users/{id}             | Remove um usuário                        |

