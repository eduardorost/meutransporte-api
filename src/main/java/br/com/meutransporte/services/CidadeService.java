package br.com.meutransporte.services;

import br.com.meutransporte.entities.CidadeEntity;
import br.com.meutransporte.models.Cidade;
import br.com.meutransporte.repositories.CidadeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Cidade> getAll() {
        Type listType = new TypeToken<List<Cidade>>() {
        }.getType();
        return modelMapper.map(cidadeRepository.findAll(), listType);
    }

    public Cidade getById(Long id) {
        return modelMapper.map(cidadeRepository.findOne(id), Cidade.class);
    }

    public Cidade insert(Cidade cidade) {
        cidade.setId(null);
        return save(cidade);
    }

    public Cidade update(Cidade cidade) {
        return save(cidade);
    }

    private Cidade save(Cidade cidade) {
        CidadeEntity cidadeEntity = cidadeRepository.save(modelMapper.map(cidade, CidadeEntity.class));
        return modelMapper.map(cidadeEntity, Cidade.class);
    }

    public void delete(Long id) {
        CidadeEntity cidadeEntity = cidadeRepository.findOne(id);
        if (cidadeEntity == null)
            throw new IllegalArgumentException();

        cidadeRepository.delete(cidadeEntity);
    }
}
