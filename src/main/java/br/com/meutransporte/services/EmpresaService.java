package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaEntity;
import br.com.meutransporte.models.Empresa;
import br.com.meutransporte.repositories.EmpresaTransporteRepository;
import br.com.meutransporte.repositories.VeiculoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaTransporteRepository empresaTransporteRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Empresa> getAll() {
        Type listType = new TypeToken<List<Empresa>>() { }.getType();
        return modelMapper.map(empresaTransporteRepository.findAll(), listType);
    }

    public Empresa getById(Long id) {
        return modelMapper.map(empresaTransporteRepository.findOne(id), Empresa.class);
    }

    public Empresa insert(Empresa empresa) {
        empresa.setId(null);
        return save(empresa);
    }

    public Empresa update(Empresa empresa) {
        return save(empresa);
    }

    private Empresa save(Empresa empresa) {
        empresa.getVeiculos().forEach(veiculo -> veiculo.setEmpresa(empresa));
        EmpresaEntity empresaEntity = empresaTransporteRepository.save(modelMapper.map(empresa, EmpresaEntity.class));

        return modelMapper.map(empresaEntity, Empresa.class);
    }

    public void delete(Long id) {
        EmpresaEntity empresaEntity = empresaTransporteRepository.findOne(id);
        if (empresaEntity == null)
            throw new IllegalArgumentException();

        empresaTransporteRepository.delete(empresaEntity);
    }
}
