package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaTransporteEntity;
import br.com.meutransporte.models.EmpresaTransporte;
import br.com.meutransporte.repositories.EmpresaTransporteRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EmpresaTransporteService {

    @Autowired
    private EmpresaTransporteRepository empresaTransporteRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<EmpresaTransporte> getAll() {
        Type listType = new TypeToken<List<EmpresaTransporte>>() { }.getType();
        return modelMapper.map(empresaTransporteRepository.findAll(), listType);
    }

    public EmpresaTransporte getById(Long id) {
        return modelMapper.map(empresaTransporteRepository.findOne(id), EmpresaTransporte.class);
    }

    public EmpresaTransporte insert(EmpresaTransporte empresaTransporte) {
        empresaTransporte.setId(null);
        return save(empresaTransporte);
    }

    public EmpresaTransporte update(EmpresaTransporte empresaTransporte) {
        return save(empresaTransporte);
    }

    private EmpresaTransporte save(EmpresaTransporte empresaTransporte) {
        EmpresaTransporteEntity eventoEntity = empresaTransporteRepository.save(modelMapper.map(empresaTransporte, EmpresaTransporteEntity.class));
        return modelMapper.map(eventoEntity, EmpresaTransporte.class);
    }

    public void delete(Long id) {
        EmpresaTransporteEntity empresaTransporteEntity = empresaTransporteRepository.findOne(id);
        if (empresaTransporteEntity == null)
            throw new IllegalArgumentException();

        empresaTransporteRepository.delete(empresaTransporteEntity);
    }
}
