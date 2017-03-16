package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Empresa;
import br.com.meutransporte.models.validators.EmpresaTransporteValidator;
import br.com.meutransporte.services.EmpresaService;
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
@RequestMapping("/v1/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new EmpresaTransporteValidator());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Empresa>> getAll() {
        return ResponseEntity.ok(empresaService.getAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Empresa> getById(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.getById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = { @ApiResponse(code = 200, response = Empresa.class, message = "Success"), @ApiResponse(code = 400, response = ObjectError[].class, message = "Bad Request") })
    public ResponseEntity<Object> insert(@Valid @RequestBody Empresa empresa, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        return ResponseEntity.ok(empresaService.insert(empresa));
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    @ApiResponses(value = { @ApiResponse(code = 200, response = Empresa.class, message = "Success"), @ApiResponse(code = 400, response = ObjectError[].class, message = "Bad Request") })
    public ResponseEntity<Object> update(@Valid @RequestBody Empresa empresa, BindingResult result) {
        if (result.hasErrors())
            return ResponseEntity.badRequest().body(result.getAllErrors());

        if (empresa.getId() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(empresaService.update(empresa));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        empresaService.delete(id);

        return ResponseEntity.ok().build();
    }

}
