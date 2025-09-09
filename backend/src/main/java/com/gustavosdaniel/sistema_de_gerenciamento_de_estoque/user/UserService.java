package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import org.springframework.security.oauth2.jwt.Jwt;


public interface UserService {

    UserResponse getCurrentUser(Jwt jwt);
}
