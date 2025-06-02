package com.adopets.adopets.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = Pet.TABLE_NAME)
public class Pet {
    public static final String TABLE_NAME = "pet";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotBlank
    @Column(name = "type", nullable = false) // Gato, cachorro, papagaio, tartaruga, etc...
    private String type;

    @NotBlank
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    @NotBlank
    @Column(name = "age", nullable = false, length = 2)
    private String age;

    @NotBlank
    @Column(name = "description", nullable = false, length = 300)

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("pets")
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;


    public Pet() {
    }

    public Pet(Long id, String type, String name, String age, User user) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
        this.user = user;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return this.type;
    }

    public void setModel(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet pet)) return false;
        return Objects.equals(id, pet.id) && Objects.equals(type, pet.type) && Objects.equals(name, pet.name) && Objects.equals(age, pet.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, age);
    }
}
