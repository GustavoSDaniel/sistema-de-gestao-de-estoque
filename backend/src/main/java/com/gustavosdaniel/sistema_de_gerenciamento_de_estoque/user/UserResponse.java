package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String keycloakId,
        String name,
        String email,
        List<String> roles) {
}
