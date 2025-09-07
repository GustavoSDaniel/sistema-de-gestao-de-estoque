package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retorna os dados do usuário logado
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {

        // Pega o sub do Keycloak
        String keycloakId = jwt.getSubject();

        // Busca o usuário no banco da aplicação
        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(UserNotFoundException::new);

        // Pega as roles direto do JWT
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        List<String> roles = Collections.emptyList();
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            roles = (List<String>) realmAccess.get("roles");
        }

        // Cria e retorna o DTO
        UserResponse userDTO = new UserResponse(
                user.getId(),
                user.getKeycloakId(),
                user.getName(),
                user.getEmail(),
                roles
        );

        return ResponseEntity.ok(userDTO);
    }
}

