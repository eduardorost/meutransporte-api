package br.com.meutransporte.services;

import br.com.meutransporte.entities.EmpresaEntity;
import br.com.meutransporte.entities.PapelEntity;
import br.com.meutransporte.entities.PessoaEntity;
import br.com.meutransporte.entities.UsuarioEntity;
import br.com.meutransporte.enums.ModuloType;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.repositories.EmpresaRepository;
import br.com.meutransporte.repositories.PapelRepository;
import br.com.meutransporte.repositories.PessoaRepository;
import br.com.meutransporte.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PapelRepository papelRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Usuario> getAll() {
        Type listType = new TypeToken<List<Usuario>>() {
        }.getType();
        return modelMapper.map(usuarioRepository.findAll(), listType);
    }

    public Usuario getById(Long id) {
        return modelMapper.map(usuarioRepository.findOne(id), Usuario.class);
    }

    public Usuario insert(Usuario usuario) {
        usuario.setId(null);
        return save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return save(usuario);
    }

    private Usuario save(Usuario usuario) {
        UsuarioEntity usuarioEntity = modelMapper.map(usuario, UsuarioEntity.class);

        usuarioEntity.setEmpresa(null);
        usuarioEntity.setPessoa(null);

        usuarioEntity = usuarioRepository.save(usuarioEntity);

        if(usuario.getEmpresa() != null) {
            EmpresaEntity empresaEntity = modelMapper.map(usuario.getEmpresa(), EmpresaEntity.class);
            empresaEntity.setUsuario(usuarioEntity);

            usuarioEntity.setEmpresa(empresaRepository.save(empresaEntity));
            usuarioEntity.setPapeis(new ArrayList<PapelEntity>() {{ add(new PapelEntity(ModuloType.EMPRESA.toString())); }});
        }

        if(usuario.getPessoa() != null) {
            PessoaEntity pessoaEntity = modelMapper.map(usuario.getPessoa(), PessoaEntity.class);
            pessoaEntity.setUsuario(usuarioEntity);

            usuarioEntity.setPessoa(pessoaRepository.save(pessoaEntity));
            usuarioEntity.setPapeis(new ArrayList<PapelEntity>() {{ add(new PapelEntity(ModuloType.USUARIO.toString())); }});
        }

        for (PapelEntity papelEntity: usuarioEntity.getPapeis()) {
            papelEntity.setUsuario(usuarioEntity);
            papelRepository.save(papelEntity);
        }

        return modelMapper.map(usuarioEntity, Usuario.class);
    }

    public void delete(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findOne(id);
        if (usuarioEntity == null)
            throw new IllegalArgumentException();

        usuarioRepository.delete(usuarioEntity);
    }
}
