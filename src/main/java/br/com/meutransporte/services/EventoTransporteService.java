package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaEntity;
import br.com.meutransporte.entities.EventoTransporteEntity;
import br.com.meutransporte.entities.PessoaEntity;
import br.com.meutransporte.models.EventoTransporte;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.repositories.EventoRepository;
import br.com.meutransporte.repositories.EventoTransporteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class EventoTransporteService {

    @Autowired
    private EventoTransporteRepository eventoTransporteRepository;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public boolean userRegisteredEvento(EventoTransporte eventoTransporte, Usuario usuario) {
        if(usuario.getPessoa() == null)
            return false;

        return eventoTransporte.getPessoas().stream().anyMatch(pessoaEntity -> Objects.equals(pessoaEntity.getId(), usuario.getPessoa().getId()));
    }

    public boolean userRegisteredEvento(EventoTransporte eventoTransporte, Long idPessoa) {
        return eventoTransporte.getPessoas().stream().anyMatch(pessoaEntity -> Objects.equals(pessoaEntity.getId(), idPessoa));
    }

    private boolean userRegisteredEventoEntity(EventoTransporteEntity eventoTransporteEntity, Usuario usuario) {
        return eventoTransporteEntity.getPessoas().stream().anyMatch(pessoaEntity -> Objects.equals(pessoaEntity.getId(), usuario.getPessoa().getId()));
    }

    public EventoTransporte registerUser(Long id, Usuario usuario) {
        EventoTransporteEntity eventoTransporteEntity = eventoTransporteRepository.findOne(id);

        Optional<EventoTransporteEntity> eventoTransporteEntityOptional = eventoTransporteEntity.getEvento().getEventoTransportes().stream().filter(
                et -> userRegisteredEventoEntity(et, usuario)
        ).findFirst();

        eventoTransporteEntityOptional.ifPresent(ete -> {
            ete.getPessoas().remove(ete.getPessoas().stream().filter(pessoaEntity -> Objects.equals(pessoaEntity.getId(), usuario.getPessoa().getId())).findFirst().get());
            eventoTransporteRepository.save(ete);
        });

        eventoTransporteEntity.getPessoas().add(modelMapper.map(usuario.getPessoa(), PessoaEntity.class));

        return modelMapper.map(eventoTransporteRepository.save(eventoTransporteEntity), EventoTransporte.class);
    }

    public EventoTransporte insert(EventoTransporte eventoTransporte, Long eventoId, Usuario usuario) {
        eventoTransporte.setId(null);
        return save(eventoTransporte, eventoId, usuario);
    }

    private EventoTransporte save(EventoTransporte eventoTransporte, Long eventoId, Usuario usuario) {
        EventoTransporteEntity eventoTransporteEntity = modelMapper.map(eventoTransporte, EventoTransporteEntity.class);

        eventoTransporteEntity.setEmpresa(modelMapper.map(usuario.getEmpresa(), EmpresaEntity.class));
        eventoTransporteEntity.setEvento(eventoRepository.findOne(eventoId));

        return modelMapper.map(eventoTransporteRepository.save(eventoTransporteEntity), EventoTransporte.class);
    }
}
