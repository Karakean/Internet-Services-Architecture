package com.example.isa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Override
    public String toString() {
        return "ID: " + id + "   NAME: " + name + "   CLASSIFICATION YEAR: " + classificationYear;
    }
}
