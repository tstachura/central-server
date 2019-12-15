package com.centralserver.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@EnableAutoConfiguration
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ADDRESS")
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {
    @Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = null;

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
