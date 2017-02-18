package br.com.meutransporte.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RequestMapping("/v1/api/login")
@RestController
public class AuthController {

    @GetMapping
    Map<String, Object> getToken(HttpSession session) {
        return Collections.singletonMap("session", session.getId());
    }

    @GetMapping(path = "/usuario")
    ResponseEntity<Principal> getUser(@AuthenticationPrincipal Principal user) {
        return ResponseEntity.ok(user);
    }
}

