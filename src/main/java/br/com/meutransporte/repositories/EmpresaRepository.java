package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EmpresaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends CrudRepository<EmpresaEntity, Long> {

    List<EmpresaEntity> findByAprovada(boolean aprovada);

}
