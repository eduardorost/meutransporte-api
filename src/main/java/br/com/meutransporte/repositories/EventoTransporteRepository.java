package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EventoTransporteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoTransporteRepository extends CrudRepository<EventoTransporteEntity, Long> {

}
