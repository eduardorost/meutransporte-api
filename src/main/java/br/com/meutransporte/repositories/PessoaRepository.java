package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.PessoaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaEntity, Long> {

}
