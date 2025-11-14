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
## Gerenciamento (/user, /appointment, /department, /doctor)

|         Metódo        |   Rota    |   Descrição          |
|-----------------------|-----------|----------------------|
|   <mark>POST<mark/>   |   /user   |   Criar um usuário   |   
