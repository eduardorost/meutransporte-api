package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EnderecoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<EnderecoEntity, Long> {

}
