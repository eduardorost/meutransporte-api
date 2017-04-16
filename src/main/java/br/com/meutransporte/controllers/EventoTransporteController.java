package br.com.meutransporte.controllers;

import br.com.meutransporte.models.EventoTransporte;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.services.EventoTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/api/eventos")
public class EventoTransporteController {

    @Autowired
    private EventoTransporteService eventoTransporteService;

    @RequestMapping(path = "/{eventoId}/transportes", method = RequestMethod.POST)
    @ResponseBody
    @Secured("ROLE_EMPRESA")
    public ResponseEntity<EventoTransporte> insert(@PathVariable Long eventoId, @RequestBody EventoTransporte eventoTransporte, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return ResponseEntity.ok(eventoTransporteService.insert(eventoTransporte, eventoId, (Usuario) user.getPrincipal()));
    }

    @RequestMapping(path = "/transportes/{transporteid}/vincular/pessoa", method = RequestMethod.POST)
    @ResponseBody
    @Secured("ROLE_USUARIO")
    public ResponseEntity<EventoTransporte> registerUser(@PathVariable Long transporteid, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return ResponseEntity.ok(eventoTransporteService.registerUser(transporteid, (Usuario) user.getPrincipal()));
    }

}
