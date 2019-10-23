package com.centralserver.model.products;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@EnableAutoConfiguration
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "WAREHOUSE")
@Getter
@Setter
public class Warehouse implements Serializable {

    @Id
    @SequenceGenerator(name = "WarehouseGen", sequenceName = "warehouse_id_seq", initialValue = 10, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WarehouseGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    private Set<Product> devices = new HashSet<>();

}

