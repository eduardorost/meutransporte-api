package br.com.meutransporte.controllers;

import br.com.meutransporte.models.EmpresaTransporte;
import br.com.meutransporte.models.validators.EmpresaTransporteValidator;
import br.com.meutransporte.services.EmpresaTransporteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/api/empresasTransporte")
public class EmpresaTransporteController {

    @Autowired
    private EmpresaTransporteService empresaTransporteService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new EmpresaTransporteValidator());
    }

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
    @ApiResponses(value = { @ApiResponse(code = 200, response = EmpresaTransporte.class, message = "Success"), @ApiResponse(code = 400, response = ObjectError[].class, message = "Bad Request") })
    public ResponseEntity<Object> insert(@Valid @RequestBody EmpresaTransporte empresaTransporte, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        return ResponseEntity.ok(empresaTransporteService.insert(empresaTransporte));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = { @ApiResponse(code = 200, response = EmpresaTransporte.class, message = "Success"), @ApiResponse(code = 400, response = ObjectError[].class, message = "Bad Request") })
    public ResponseEntity<Object> update(@Valid @RequestBody EmpresaTransporte empresaTransporte, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

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
