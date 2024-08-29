package br.com.systemsgs.cleanarchitecture2.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaClienteRepository extends JpaRepository<ClienteEntity, UUID> {}
