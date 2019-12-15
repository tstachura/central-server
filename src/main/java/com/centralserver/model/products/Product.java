package com.centralserver.model.products;

import com.centralserver.model.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Getter
@Setter
@EnableAutoConfiguration
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
    @Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = null;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "SERIAL_NUMBER", nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonManagedReference
    private ProductType productType;

    @Basic
    @NotNull
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdate;

    @Basic
    @NotNull
    @Column(name = "CREATE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createDate;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonManagedReference
    private Department department;

    public Product() {
    }

    public Product(UUID id, long version) {
        this.id = id;
        this.version = version;
    }
}
