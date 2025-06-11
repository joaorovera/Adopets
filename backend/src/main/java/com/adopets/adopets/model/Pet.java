package com.adopets.adopets.model;

// import java.util.Objects;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
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
@Table(name = Pet.TABLE_NAME)
public class Pet {
    public static final String TABLE_NAME = "pet";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    
    @NotBlank
    @Column(name = "type", nullable = false) // Gato, cachorro, papagaio, tartaruga, etc...
    private String type;
    
    @NotBlank
    @Column(name = "age", nullable = false, length = 2)
    private String age;

    @NotBlank
    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    @NotBlank
    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @NotBlank
    @Column(name = "status", nullable = false, length = 300)
    private String status;

    @NotBlank
    @Column(name = "description", nullable = false, length = 300)
    private String description;

    @Column(name = "imageUrl", length = 60)
    private String imageUrl;

    // @NotNull
    // @ManyToOne
    // @JsonIgnoreProperties("pets")
    // @JoinColumn(name = "user_id", nullable = false, updatable = false)
    // private User user;

}
