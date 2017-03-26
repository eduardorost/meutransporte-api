package br.com.meutransporte.services;

import br.com.meutransporte.entities.CidadeEntity;
import br.com.meutransporte.entities.EnderecoEntity;
import br.com.meutransporte.entities.EventoEntity;
import br.com.meutransporte.models.Evento;
import br.com.meutransporte.repositories.CidadeRepository;
import br.com.meutransporte.repositories.EnderecoRepository;
import br.com.meutransporte.repositories.EstadoRepository;
import br.com.meutransporte.repositories.EventoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Evento> getAll() {
        Type listType = new TypeToken<List<Evento>>() {
        }.getType();
        return modelMapper.map(eventoRepository.findAll(), listType);
    }

    public Evento getById(Long id) {
        return modelMapper.map(eventoRepository.findOne(id), Evento.class);
    }

    public Evento insert(Evento evento) {
        evento.setId(null);
        return save(evento);
    }

    public Evento update(Evento evento) {
        return save(evento);
    }

    private Evento save(Evento evento) {
        EventoEntity eventoEntity = modelMapper.map(evento, EventoEntity.class);

        Optional<CidadeEntity> cidadeOptional = cidadeRepository.findFirstByNome(evento.getCidade().getNome());
        CidadeEntity cidadeEntity;

        if(cidadeOptional.isPresent())
            cidadeEntity = cidadeOptional.get();
        else {
            cidadeEntity = modelMapper.map(evento.getCidade(), CidadeEntity.class);
            cidadeEntity.setEstado(estadoRepository.findFirstByUf(evento.getCidade().getUf()));
            cidadeEntity = cidadeRepository.save(cidadeEntity);
        }

        eventoEntity.setEndereco(enderecoRepository.save(modelMapper.map(evento.getEndereco(), EnderecoEntity.class)));
        eventoEntity.setCidade(cidadeEntity);

        eventoEntity = eventoRepository.save(eventoEntity);
        return modelMapper.map(eventoEntity, Evento.class);
    }

    public void delete(Long id) {
        EventoEntity eventoEntity = eventoRepository.findOne(id);
        if (eventoEntity == null)
            throw new IllegalArgumentException();

        eventoRepository.delete(eventoEntity);
    }
}
