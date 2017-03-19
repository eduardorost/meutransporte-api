package br.com.meutransporte.repositories;

import br.com.meutransporte.entities.PapelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface PapelRepository extends CrudRepository<PapelEntity, Long> {

}
