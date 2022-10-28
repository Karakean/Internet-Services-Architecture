package com.example.isa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "families")
public class Family {
    @Id
    @Getter
    @Setter
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "classification_year")
    private int classificationYear;

    @OneToMany(mappedBy = "family")
    @Getter
    private List<Species> species;

    public Family(Long id, String name, int classificationYear) {
        this.id = id;
        this.name = name;
        this.classificationYear = classificationYear;
    }

    @Override
    public String toString() {
        return "ID: " + id + "   NAME: " + name + "   CLASSIFICATION YEAR: " + classificationYear;
    }
}
