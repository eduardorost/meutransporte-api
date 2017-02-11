package br.com.meutransporte.services;

import br.com.meutransporte.models.Evento;
import br.com.meutransporte.repositories.EventoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Evento> getAll() {
        Type listType = new TypeToken<List<Evento>>() {}.getType();
        return modelMapper.map(eventoRepository.findAll(), listType);
    }
}
