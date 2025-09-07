package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;

    public UserProvisioningFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            String keycloakId = jwt.getSubject(); // sub do Keycloak
            String email = jwt.getClaimAsString("email");
            String name = jwt.getClaimAsString("name");

            // Cria o usuário no banco caso ainda não exista
            userRepository.findByKeycloakId(keycloakId)
                    .map(user -> {
                        // CASO 1: Usuário ENCONTRADO. Apenas atualiza os dados.
                        user.setName(name);
                        user.setEmail(email);
                        return userRepository.save(user);
                    })
                    .orElseGet(() -> {
                        // CASO 2: Usuário NÃO ENCONTRADO. Cria um novo.
                        User newUser = new User();
                        newUser.setKeycloakId(keycloakId);
                        newUser.setName(name);
                        newUser.setEmail(email);
                        return userRepository.save(newUser);
                    });
        }

        filterChain.doFilter(request, response);
    }
}


