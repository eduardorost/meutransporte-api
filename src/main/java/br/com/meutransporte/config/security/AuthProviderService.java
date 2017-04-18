package br.com.meutransporte.config.security;

import br.com.meutransporte.entities.*;
import br.com.meutransporte.models.*;
import br.com.meutransporte.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Transactional
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login = auth.getName();
        String senha = auth.getCredentials().toString();

        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findFirstByLoginAndSenha(login, senha);

        if (!usuarioOptional.isPresent())
            throw new UsernameNotFoundException("Login e/ou Senha inválidos.");

        UsuarioEntity usuarioEntity = usuarioOptional.get();
        if (usuarioEntity.getStatus()) {
            Collection<? extends GrantedAuthority> authorities = usuarioEntity.getPapeis();
            return new  UsernamePasswordAuthenticationToken(buildUsuario(usuarioEntity), senha, authorities);
        } else {
            throw new BadCredentialsException("Usuário desativado.");
        }
    }

    private Usuario buildUsuario(UsuarioEntity usuarioEntity) {
        Type listType = new TypeToken<List<Papel>>() {}.getType();
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getLogin(),
                usuarioEntity.getSenha(),
                usuarioEntity.getStatus(),
                usuarioEntity.getCadastro(),
                modelMapper.map(usuarioEntity.getPapeis(), listType),
                buildEventos(usuarioEntity.getEventos()),
                buildPessoa(usuarioEntity.getPessoa()),
                buildEmpresa(usuarioEntity.getEmpresa())
        );
    }

    private Empresa buildEmpresa(EmpresaEntity empresaEntity) {
        if(empresaEntity == null)
            return null;

        Type listType = new TypeToken<List<Veiculo>>() {}.getType();
        return new Empresa(
                empresaEntity.getId(),
                empresaEntity.getCnpj(),
                empresaEntity.getNome(),
                empresaEntity.getTelefone(),
                empresaEntity.getEmail(),
                empresaEntity.getRecefitur(),
                modelMapper.map(empresaEntity.getVeiculos(), listType),
                buildEventoTransporte(empresaEntity.getEventoTransportes(), true)
        );
    }

    private Set<Evento> buildEventos(Set<EventoEntity> eventoEntities) {
        return eventoEntities.stream().map(this::buildEvento).collect(Collectors.toSet());
    }

    private Pessoa buildPessoa(PessoaEntity pessoaEntity) {
        if(pessoaEntity == null)
            return null;

        return new Pessoa(
                pessoaEntity.getId(),
                pessoaEntity.getCpf(),
                pessoaEntity.getNome(),
                pessoaEntity.getTelefone(),
                pessoaEntity.getEmail(),
                buildEventoTransporte(pessoaEntity.getEventoTransportes(), true)
        );
    }

    private Set<EventoTransporte> buildEventoTransporte(Set<EventoTransporteEntity> eventoTransporteEntities, boolean withEvento) {
        return eventoTransporteEntities.stream().map(eventoTransporteEntity -> new EventoTransporte(
                eventoTransporteEntity.getId(),
                withEvento ? buildEvento(eventoTransporteEntity.getEvento()) : null
        )).collect(Collectors.toSet());
    }

    private Evento buildEvento(EventoEntity eventoEntity) {
        return new Evento(
                eventoEntity.getId(),
                eventoEntity.getNome(),
                eventoEntity.getDescricao(),
                eventoEntity.getLink(),
                eventoEntity.getFoto(),
                eventoEntity.getTipo(),
                eventoEntity.getData(),
                modelMapper.map(eventoEntity.getEndereco(), Endereco.class),
                modelMapper.map(eventoEntity.getCidade(), Cidade.class),
                buildEventoTransporte(eventoEntity.getEventoTransportes(), false)
        );
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }


}