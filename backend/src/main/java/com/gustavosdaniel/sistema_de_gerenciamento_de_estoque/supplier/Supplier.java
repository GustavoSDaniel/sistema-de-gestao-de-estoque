package com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.supplier;

import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.address.Address;
import com.gustavosdaniel.sistema_de_gerenciamento_de_estoque.common.BaseEntity;
import jakarta.persistence.*;
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
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    private String name;

    @ElementCollection
    @CollectionTable(name = "supplier_phone_numbers", joinColumns = @JoinColumn(name = "supplier_id"))
    @Column(name = "phone_number", nullable = false)
    private List<String> phoneNumbers;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
}
