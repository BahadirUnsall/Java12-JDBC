package com.bahadir.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;
    private String identity;

    @Embedded
    private Information information;


    @ManyToMany(mappedBy = "customerList")
    private List<Product> productList;
}
