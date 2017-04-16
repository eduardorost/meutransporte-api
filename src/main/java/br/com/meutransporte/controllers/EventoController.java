package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Evento;
import br.com.meutransporte.models.EventoTransporte;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.services.EventoService;
import br.com.meutransporte.services.EventoTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;
    @Autowired
    private EventoTransporteService eventoTransporteService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Evento>> getAll(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        List<Evento> eventos = eventoService.getAll();
        eventos.forEach(evento ->
            evento.getTransportes().forEach(eventoTransporte -> eventoTransporte.setVinculoUsuarioLogado(eventoTransporteService.userRegisteredEvento(eventoTransporte, (Usuario) user.getPrincipal())))
        );

        return ResponseEntity.ok(eventos);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Evento> getById(@PathVariable Long id, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        Evento evento = eventoService.getById(id);
        evento.getTransportes().forEach(eventoTransporte -> eventoTransporte.setVinculoUsuarioLogado(eventoTransporteService.userRegisteredEvento(eventoTransporte, (Usuario) user.getPrincipal())));

        return ResponseEntity.ok(evento);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Evento> insert(@RequestBody Evento evento, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        Usuario usuario = (Usuario) user.getPrincipal();
        evento = eventoService.insert(evento, usuario);
        evento.getTransportes().forEach(eventoTransporte -> eventoTransporte.setVinculoUsuarioLogado(eventoTransporteService.userRegisteredEvento(eventoTransporte, usuario)));
        return ResponseEntity.ok(evento);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Evento> update(@RequestBody Evento evento, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        if (evento.getId() == null)
            return ResponseEntity.badRequest().build();

        evento = eventoService.update(evento);
        evento.getTransportes().forEach(eventoTransporte -> eventoTransporte.setVinculoUsuarioLogado(eventoTransporteService.userRegisteredEvento(eventoTransporte, (Usuario) user.getPrincipal())));

        return ResponseEntity.ok(evento);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
//    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id) {
        eventoService.delete(id);

        return ResponseEntity.ok().build();
    }

}
