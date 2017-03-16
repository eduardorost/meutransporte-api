package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EmpresaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaTransporteRepository extends CrudRepository<EmpresaEntity, Long> {

}
