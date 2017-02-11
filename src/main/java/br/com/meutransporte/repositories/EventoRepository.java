package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EventoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends CrudRepository<EventoEntity, Long> {

}
