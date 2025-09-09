package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getCurrentUser(Jwt jwt) {

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

        return new UserResponse(
                user.getId(),
                user.getKeycloakId(),
                user.getName(),
                user.getEmail(),
                roles
        );
    }


}
