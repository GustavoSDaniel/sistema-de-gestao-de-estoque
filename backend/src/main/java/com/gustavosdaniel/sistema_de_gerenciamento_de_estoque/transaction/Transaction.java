package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.transaction;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.product.Product;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.supplier.Supplier;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Column(name = "total_products")
    private Long totalProducts;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String description;

    private String note;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
