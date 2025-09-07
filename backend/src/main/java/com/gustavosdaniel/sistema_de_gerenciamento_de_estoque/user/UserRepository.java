package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) " +
            "LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY u.name ASC")
    List<User> searchByName(@Param("name") String name);

    Optional<User> findByKeycloakId(String keycloakId);

}
