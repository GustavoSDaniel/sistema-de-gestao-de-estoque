package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;


import java.util.List;


@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;


}
