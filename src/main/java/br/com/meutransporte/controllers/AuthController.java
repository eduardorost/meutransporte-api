package br.com.meutransporte.controllers;

import br.com.meutransporte.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/api/login")
@RestController
public class AuthController {

    @GetMapping
    ResponseEntity<Usuario> login(@AuthenticationPrincipal UsernamePasswordAuthenticationToken user) {
        return ResponseEntity.ok((Usuario) user.getPrincipal());
    }
}

