package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.CidadeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends CrudRepository<CidadeEntity, Long> {

    Optional<CidadeEntity> findFirstByNome(String nome);

}
