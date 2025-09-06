package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.supplier;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.address.Address;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;

    @Column(nullable = false, unique = true)
    private List<String> phoneNumber;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private List<Address> address;
}
