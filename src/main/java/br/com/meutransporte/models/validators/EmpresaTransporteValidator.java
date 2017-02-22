package br.com.meutransporte.models.validators;

import br.com.meutransporte.models.EmpresaTransporte;
import br.com.meutransporte.utils.DocumentoUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmpresaTransporteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EmpresaTransporte.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmpresaTransporte empresaTransporte = (EmpresaTransporte) target;

        if(!DocumentoUtil.isValidCNPJ(empresaTransporte.getRazaoSocial()))
            errors.rejectValue("razaoSocial", "razaoSocial.invalid");
    }
}
