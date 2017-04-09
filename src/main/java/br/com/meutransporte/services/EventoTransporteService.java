package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaEntity;
import br.com.meutransporte.entities.EventoTransporteEntity;
import br.com.meutransporte.entities.PessoaEntity;
import br.com.meutransporte.models.EventoTransporte;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.repositories.EventoTransporteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EventoTransporteService {

    @Autowired
    private EventoTransporteRepository eventoTransporteRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EventoTransporte insert(EventoTransporte eventoTransporte, Usuario usuario) {
        eventoTransporte.setId(null);
        return save(eventoTransporte, usuario);
    }

    private EventoTransporte save(EventoTransporte eventoTransporte, Usuario usuario) {
        EventoTransporteEntity eventoTransporteEntity = modelMapper.map(eventoTransporte, EventoTransporteEntity.class);

        eventoTransporteEntity.setEmpresa(modelMapper.map(usuario.getEmpresa(), EmpresaEntity.class));

        EventoTransporteEntity empresaEntity = eventoTransporteRepository.save(eventoTransporteEntity);

        return modelMapper.map(empresaEntity, EventoTransporte.class);
    }
}
