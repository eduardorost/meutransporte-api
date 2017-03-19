package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EstadoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<EstadoEntity, Long> {

    EstadoEntity findFirstByUf(String uf);

}
