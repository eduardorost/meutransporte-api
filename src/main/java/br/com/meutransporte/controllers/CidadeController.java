package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Cidade;
import br.com.meutransporte.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Cidade>> getAll() {
        return ResponseEntity.ok(cidadeService.getAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Cidade> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cidadeService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Cidade> insert(@RequestBody Cidade cidade) {
        return ResponseEntity.ok(cidadeService.insert(cidade));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Cidade> update(@RequestBody Cidade cidade) {
        if (cidade.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(cidadeService.update(cidade));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        cidadeService.delete(id);

        return ResponseEntity.ok().build();
    }

}
