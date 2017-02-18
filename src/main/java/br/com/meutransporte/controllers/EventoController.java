package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Evento;
import br.com.meutransporte.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
//    @Secured("ROLE_USER")
    public ResponseEntity<List<Evento>> getAll() {
        return ResponseEntity.ok(eventoService.getAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Evento> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Evento> insert(@RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.insert(evento));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Evento> update(@RequestBody Evento evento) {
        if (evento.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(eventoService.update(evento));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
//    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id) {
        eventoService.delete(id);

        return ResponseEntity.ok().build();
    }

}
