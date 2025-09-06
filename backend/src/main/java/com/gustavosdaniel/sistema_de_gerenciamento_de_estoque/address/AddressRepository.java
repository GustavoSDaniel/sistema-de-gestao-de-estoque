package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.address;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
