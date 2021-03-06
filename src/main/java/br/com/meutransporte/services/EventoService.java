package br.com.meutransporte.services;

import br.com.meutransporte.entities.*;
import br.com.meutransporte.models.Evento;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.repositories.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EventoTransporteService eventoTransporteService;
    @Autowired
    private ModelMapper modelMapper;

    public List<Evento> getAll() {
        Type listType = new TypeToken<List<Evento>>() { }.getType();
        List<Evento> eventos = modelMapper.map(eventoRepository.findAll(), listType);
        return eventos.stream().filter(evento -> evento.getData().after(getYesterday())).collect(Collectors.toList());
    }

    public Evento getById(Long id) {
        return modelMapper.map(eventoRepository.findOne(id), Evento.class);
    }

    public Evento insert(Evento evento, Usuario usuario) {
        evento.setId(null);
        return save(evento, usuario);
    }

    public Evento update(Evento evento) {
        return save(evento, null);
    }

    private Evento save(Evento evento, Usuario usuario) {
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

        if(usuario != null)
            eventoEntity.setUsuario(usuarioRepository.findOne(usuario.getId()));

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

    public List<Evento> getAllByUsuarioId(Long id) {
        Type listType = new TypeToken<List<Evento>>() { }.getType();
        List<Evento> eventos = modelMapper.map(eventoRepository.findByUsuarioId(id).stream().filter(eventoEntity -> eventoEntity.getData().after(getYesterday())).collect(Collectors.toList()), listType);

        UsuarioEntity usuarioEntity = usuarioRepository.findOne(id);
        if(usuarioEntity.getPessoa() != null) {
            eventos.forEach(evento ->
                    evento.getTransportes().forEach(eventoTransporte -> eventoTransporte.setVinculoUsuarioLogado(eventoTransporteService.userRegisteredEvento(eventoTransporte, usuarioEntity.getPessoa().getId())))
            );
        }

        return eventos;
    }

    public List<Evento> getAllByUsuarioIdWithTransporte(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findOne(id);

        Type listType = new TypeToken<List<Evento>>() { }.getType();

        List<EventoTransporteEntity> eventoTransporteEntities;
        if(usuarioEntity.getPessoa() != null) eventoTransporteEntities = usuarioEntity.getPessoa().getEventoTransporte();
        else eventoTransporteEntities = usuarioEntity.getEmpresa().getEventoTransportes();

        List<Evento> eventos = modelMapper.map(eventoTransporteEntities.stream().map(EventoTransporteEntity::getEvento).filter(eventoEntity -> eventoEntity.getData().after(getYesterday())).distinct().collect(Collectors.toList()), listType);
        if(usuarioEntity.getPessoa() != null) {
            eventos.forEach(evento ->
                    evento.getTransportes().forEach(eventoTransporte -> eventoTransporte.setVinculoUsuarioLogado(eventoTransporteService.userRegisteredEvento(eventoTransporte, usuarioEntity.getPessoa().getId())))
            );
        }

        return eventos;
    }

    private Date getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
