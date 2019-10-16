package com.centralserver.model.products;

import com.centralserver.model.enums.ProductStatus;
import com.centralserver.model.Warehouse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Getter
@Setter
@EnableAutoConfiguration
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @SequenceGenerator(name = "ProductGen", sequenceName = "device_id_seq",initialValue = 10,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ProductGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "SERIAL_NUMBER",nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private ProductType productType;

    @Basic
    @NotNull
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdate;

    @Basic
    @NotNull
    @Column(name = "CREATE_DATE",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonBackReference
    private Warehouse warehouse;

    public Product() {
    }

    public Product(Long id, long version) {
        this.id=id;
        this.version = version;
    }
}
