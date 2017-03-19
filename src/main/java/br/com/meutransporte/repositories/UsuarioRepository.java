package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findFirstByLoginAndSenha(String login, String senha);
}
