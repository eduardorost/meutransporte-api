package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaEntity;
import br.com.meutransporte.entities.UsuarioEntity;
import br.com.meutransporte.entities.VeiculoEntity;
import br.com.meutransporte.models.Empresa;
import br.com.meutransporte.repositories.EmpresaRepository;
import br.com.meutransporte.repositories.UsuarioRepository;
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
    private EmpresaRepository empresaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Empresa> getAll() {
        Type listType = new TypeToken<List<Empresa>>() { }.getType();
        return modelMapper.map(empresaRepository.findAll(), listType);
    }

    public Empresa getById(Long id) {
        return modelMapper.map(empresaRepository.findOne(id), Empresa.class);
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
        EmpresaEntity empresaEntity = empresaRepository.save(modelMapper.map(empresa, EmpresaEntity.class));

        return modelMapper.map(empresaEntity, Empresa.class);
    }

    public EmpresaEntity saveEntity(EmpresaEntity empresaEntity, UsuarioEntity usuarioEntity) {
        empresaEntity.setUsuario(usuarioEntity);
        empresaEntity = empresaRepository.save(empresaEntity);

        for (VeiculoEntity veiculoEntity: empresaEntity.getVeiculos()) {
            veiculoEntity.setEmpresa(empresaEntity);
            veiculoRepository.save(veiculoEntity);
        }

        return empresaEntity;
    }

    public void delete(Long id) {
        EmpresaEntity empresaEntity = empresaRepository.findOne(id);
        if (empresaEntity == null)
            throw new IllegalArgumentException();

        usuarioRepository.delete(empresaEntity.getUsuario());
    }

    public Empresa aprovar(Long id) {
        EmpresaEntity empresaEntity = empresaRepository.findOne(id);
        empresaEntity.setAprovada(true);

        return modelMapper.map(empresaRepository.save(empresaEntity), Empresa.class);
    }

    public List<Empresa> getAllAprovar() {
        Type listType = new TypeToken<List<Empresa>>() { }.getType();
        return modelMapper.map(empresaRepository.findByAprovada(false), listType);
    }
}
