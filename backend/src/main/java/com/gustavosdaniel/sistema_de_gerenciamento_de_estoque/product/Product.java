package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.product;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.category.Category;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private String description;

    private LocalDateTime expiryDate;

    private String imageUrl;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;


}
