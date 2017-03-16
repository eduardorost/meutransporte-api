package br.com.meutransporte.config.security;

import br.com.meutransporte.entities.UsuarioEntity;
import br.com.meutransporte.models.Usuario;
import br.com.meutransporte.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class AuthProviderService implements AuthenticationProvider {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
            return new  UsernamePasswordAuthenticationToken(new ModelMapper().map(usuarioEntity, Usuario.class), senha, authorities);
        } else {
            throw new BadCredentialsException("Usuário desativado.");
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}