package com.centralserver.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EnableAutoConfiguration
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ADDRESS")
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "AddressGen", sequenceName = "address_id_seq",initialValue = 13,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "AddressGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Version
    @Column(name = "VERSION")
    private long version;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STREET",nullable = false)
    private String street;

    @Column(name = "BUILDING_NUMBER",nullable = false)
    private String buildingNumber;

    @Column(name = "FLAT_NUMBER")
    private String flatNumber;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted;

}
