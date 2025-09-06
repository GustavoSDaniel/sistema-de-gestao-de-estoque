package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
