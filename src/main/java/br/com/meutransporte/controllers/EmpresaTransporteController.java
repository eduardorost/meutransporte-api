package br.com.meutransporte.controllers;

import br.com.meutransporte.models.EmpresaTransporte;
import br.com.meutransporte.services.EmpresaTransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/api/empresasTransporte")
public class EmpresaTransporteController {

    @Autowired
    private EmpresaTransporteService empresaTransporteService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EmpresaTransporte>> getAll() {
        return ResponseEntity.ok(empresaTransporteService.getAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EmpresaTransporte> getById(@PathVariable Long id) {
        return ResponseEntity.ok(empresaTransporteService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<EmpresaTransporte> insert(@RequestBody EmpresaTransporte empresaTransporte) {
        return ResponseEntity.ok(empresaTransporteService.insert(empresaTransporte));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<EmpresaTransporte> update(@RequestBody EmpresaTransporte empresaTransporte) {
        if (empresaTransporte.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(empresaTransporteService.update(empresaTransporte));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        empresaTransporteService.delete(id);

        return ResponseEntity.ok().build();
    }

}
