package br.com.systemsgs.fileserver1.repository;

import br.com.systemsgs.fileserver1.entity.ModelFileServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FIleServerRepository extends JpaRepository<ModelFileServer, UUID> {}
