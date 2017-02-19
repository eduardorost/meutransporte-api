package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EmpresaTransporteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaTransporteRepository extends CrudRepository<EmpresaTransporteEntity, Long> {

}
