package com.centralserver.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@EnableAutoConfiguration
@Table(name = "USER_ROLE")
@Getter
@Setter
public class UserRole implements Serializable {

    @Id
    @SequenceGenerator(name = "UserRoleGen", sequenceName = "user_role_id_seq",initialValue = 6,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UserRoleGen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id = null;

    @Column(name = "NAME",nullable = false,unique = true)
    private String name;

    @Column(name= "ACTIVE",nullable = false)
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES_AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ROLE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<Authority> authorities;
}
