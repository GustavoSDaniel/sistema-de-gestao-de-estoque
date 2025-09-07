package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.user;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder //
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String keycloakId; // sub do Keycloak

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;


}
