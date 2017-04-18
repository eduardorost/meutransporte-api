package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.EventoEntity;
import br.com.meutransporte.models.Evento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EventoRepository extends CrudRepository<EventoEntity, Long> {

    List<EventoEntity> findByUsuarioId(Long id);

}
