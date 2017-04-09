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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/api/evento/transportes")
public class EventoTransporteController {

    @Autowired
    private EventoTransporteService eventoTransporteService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Secured("ROLE_EMPRESA")
    public ResponseEntity<EventoTransporte> insert(@RequestBody EventoTransporte eventoTransporte, @AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return ResponseEntity.ok(eventoTransporteService.insert(eventoTransporte, (Usuario) user.getPrincipal()));
    }

}
