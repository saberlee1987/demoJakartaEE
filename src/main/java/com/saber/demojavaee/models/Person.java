package com.saber.demojavaee.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "persons")
@NamedQueries(value = {
    @NamedQuery(name = "findAll",query = "select p from Person p")
})
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "firstName",length = 70)
    private String firstName;
    @Column(name = "lastName",length = 90)
    private String lastName;
    @Column(name = "nationalCode",length = 10)
    private String nationalCode;
    @Column(name = "age")
    private Integer age;
    @Column(name = "mobile",length = 11)
    private String mobile;
    @Column(name = "email",length = 35)
    private String email;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @Transient
    private String createdAtPersian;

    @Transient
    private String updatedAtPersian;

    @PrePersist
    public void createdDate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updateDate() {
        this.updatedAt = LocalDateTime.now();
    }
}
