package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.category;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


}
