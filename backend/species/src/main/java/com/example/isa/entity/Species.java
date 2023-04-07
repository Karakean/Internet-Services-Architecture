package com.example.isa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "species")
public class Species {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean hallucinogenic;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;

    public Species(String name, boolean hallucinogenic, Family family){
        this.name = name;
        this.hallucinogenic = hallucinogenic;
        this.family = family;
    }

    @Override
    public String toString() {
        return "ID: " + id + "   NAME: " + name + "   FAMILY: {" + family + "}   IS HALLUCINOGENIC: " + hallucinogenic;
    }
}
