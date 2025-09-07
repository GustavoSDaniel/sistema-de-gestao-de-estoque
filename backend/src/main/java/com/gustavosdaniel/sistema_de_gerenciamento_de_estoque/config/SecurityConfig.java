package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.config;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user.UserProvisioningFilter;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    public SecurityConfig(UserRepository userRepository, JwtAuthenticationConverter jwtAuthenticationConverter) {
        this.userRepository = userRepository;
        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
    }

    /**
     * Configura toda a segurança da aplicação
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Define quem pode acessar quais endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll() // endpoints públicos
                        .requestMatchers("/api/admin/**").hasRole("admin") // apenas admins
                        .requestMatchers("/api/manager/**").hasRole("manager") // apenas managers
                        .anyRequest().authenticated() // qualquer outro endpoint precisa de login
                )
                // Desabilita CSRF (não precisa em API stateless com JWT)
                .csrf(csrf -> csrf.disable())
                // Define que a aplicação é stateless (não mantém sessão)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura o Spring para usar JWT do Keycloak
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter))
                )
                // Adiciona o filtro que garante que o usuário exista no banco
                .addFilterAfter(userProvisioningFilter(), BearerTokenAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Filtro que cria ou atualiza o usuário no banco após autenticação
     */
    @Bean
    public UserProvisioningFilter userProvisioningFilter() {
        return new UserProvisioningFilter(userRepository);
    }
}

