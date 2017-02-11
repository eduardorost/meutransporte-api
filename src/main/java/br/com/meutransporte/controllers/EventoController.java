package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Evento;
import br.com.meutransporte.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/v1/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Evento> getAll() {
        return eventoService.getAll();
    }

}
