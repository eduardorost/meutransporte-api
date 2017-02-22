package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.VeiculoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends CrudRepository<VeiculoEntity, Long> {

}
