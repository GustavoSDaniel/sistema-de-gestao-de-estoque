package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.address;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.supplier.Supplier;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String complement;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @ManyToOne
    private Supplier supplier;
}
