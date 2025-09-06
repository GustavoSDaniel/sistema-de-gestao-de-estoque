package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
