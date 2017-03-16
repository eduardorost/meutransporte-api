package br.com.meutransporte.models.validators;

import br.com.meutransporte.models.Empresa;
import br.com.meutransporte.utils.DocumentoUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmpresaTransporteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Empresa.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Empresa empresa = (Empresa) target;

        if(!DocumentoUtil.isValidCNPJ(empresa.getCnpj().toString()))
            errors.rejectValue("cnpj", "cnpj.invalid");
    }
}
