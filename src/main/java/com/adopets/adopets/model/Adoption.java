package com.adopets.adopets.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// import java.util.Objects;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

}
