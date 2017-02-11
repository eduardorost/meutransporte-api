package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Evento;
import br.com.meutransporte.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("v1/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @RequestMapping
    public List<Evento> getAll() {
        return eventoService.getAll();
    }

}
