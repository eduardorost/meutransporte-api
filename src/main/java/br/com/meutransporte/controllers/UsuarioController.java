package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Evento;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.services.EventoService;
import br.com.meutransporte.services.EventoTransporteService;
import br.com.meutransporte.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EventoService eventoService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.insert(usuario));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        if (usuario.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(usuarioService.update(usuario));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}/eventos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Evento>> getEventosByUsuarioId(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.getAllByUsuarioId(id));
    }

    @RequestMapping(path = "/{id}/transportes/eventos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Evento>> getEventosWithTransporteByUsuarioId(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.getAllByUsuarioIdWithTransporte(id));
    }

}
