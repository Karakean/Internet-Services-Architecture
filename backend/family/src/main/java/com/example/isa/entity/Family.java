package com.example.isa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "families")
public class Family {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "classification_year")
    private int classificationYear;

    public Family(String name, int classificationYear){
        this.name = name;
        this.classificationYear = classificationYear;
    }

    @Override
    public String toString() {
        return "ID: " + id + "   NAME: " + name + "   CLASSIFICATION YEAR: " + classificationYear;
    }
}
