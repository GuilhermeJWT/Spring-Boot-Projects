package br.com.systemsgs.clean_architecture.controllers;

public record CreateUserRequest(String nome, String email, String senha) {}
