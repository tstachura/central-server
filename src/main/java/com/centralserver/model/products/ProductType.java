package com.centralserver.model.products;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@Table(name = "PRODUCT_MODEL")
@Getter
@Setter
public class ProductType implements Serializable {

    @Id
    @SequenceGenerator(name = "ProductModelGen", sequenceName = "product_model_id_seq",initialValue = 3,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ProductModelGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false,unique = true)
    private String name;

    @Column(name = "MANUFACTURE" ,nullable = false)
    private String manufacture;

    @Column(name = "COST", nullable = false)
    private long cost;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productType", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Set<Product> products = new HashSet<>();
}
