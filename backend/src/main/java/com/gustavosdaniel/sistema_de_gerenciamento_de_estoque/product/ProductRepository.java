package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.product;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    Optional<Product> findByName(@Param("name") String name);
}
