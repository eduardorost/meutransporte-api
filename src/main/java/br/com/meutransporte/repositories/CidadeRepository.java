package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.CidadeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends CrudRepository<CidadeEntity, Long> {

}
