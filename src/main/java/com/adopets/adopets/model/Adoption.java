package com.adopets.adopets.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = Adoption.TABLE_NAME)
public class Adoption {
    public static final String TABLE_NAME= "adoption";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonIgnoreProperties("adoptions")
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @NotEmpty
    @ManyToMany
    @JoinTable(
        name = "adoption_pet",
        joinColumns = @JoinColumn(name = "adoption_id"),
        inverseJoinColumns = @JoinColumn(name = "pet_id")
    )
    private List<Pet> pets = new ArrayList<>();

    @NotNull
    @Column(name = "adoption_date", nullable = false)
    private LocalDateTime adoptionDate;


    public Adoption() {
    }

    public Adoption(Long id, User user, List<Pet> pets, LocalDateTime adoptionDate) {
        this.id = id;
        this.user = user;
        this.pets = pets;
        this.adoptionDate = adoptionDate;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDateTime getAdoptionDate() {
        return this.adoptionDate;
    }

    public void setAdoptionDate(LocalDateTime adoptionDate) {
        this.adoptionDate = adoptionDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adoption adoption)) return false;
        return Objects.equals(id, adoption.id) && Objects.equals(user, adoption.user) && Objects.equals(pets, adoption.pets) && Objects.equals(adoptionDate, adoption.adoptionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, pets, adoptionDate);
    }
    
}
