package com.market_place.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.market_place.Validation.CheckEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "clients")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "2 and more symbols in name")
    private String name;

    @Column(name = "email")
    @CheckEmail
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products;

}
