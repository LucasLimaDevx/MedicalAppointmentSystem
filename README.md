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
## Gerenciamento (/patient)

| Método | Rota                    | Descrição                                 |
|--------|-------------------------|-------------------------------------------|
| POST   | /patient                | Cria um novo paciente                     |
| GET    | /patient                | Lista todos os pacientes                  |
| GET    | /patient/{id}           | Busca um paciente pelo ID                 |
| PUT    | /patient/{id}           | Atualiza todos os dados de um paciente    |
| DELETE | /patient/{id}           | Remove um paciente                        |

## Gerenciamento (/appointments)

| Método | Rota                          | Descrição                                  |
|--------|-------------------------------|--------------------------------------------|
| POST   | /appointment                  | Cria um novo consulta                      |
| GET    | /appointment                  | Lista todas as consulta                    |
| GET    | /appointment/{id}             | Busca uma consulta pelo ID                 |
| PUT    | /appointment/{id}             | Atualiza todos os dados de uma consulta    |
| DELETE | /appointment/{id}             | Remove uma consulta                        |

## Gerenciamento (/department)

| Método | Rota                         | Descrição                                     |
|--------|------------------------------|-----------------------------------------------|
| POST   | /department                  | Cria um novo departamento                     |
| GET    | /department                  | Lista todos os departamentos                  |
| GET    | /department/{id}             | Busca um departamento pelo ID                 |
| PUT    | /department/{id}             | Atualiza todos os dados de um departamento    |
| DELETE | /department/{id}             | Remove um departamento                        |

## Gerenciamento (/doctor)

| Método | Rota                     | Descrição                               |
|--------|--------------------------|-----------------------------------------|
| POST   | /doctor                  | Cria um novo doutor                     |
| GET    | /doctor                  | Lista todos os doutores                 |
| GET    | /doctor/{id}             | Busca um doutor pelo ID                 |
| PUT    | /doctor/{id}             | Atualiza todos os dados de um doutor    |
| DELETE | /doctor/{id}             | Remove um doutor                        |

