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
    @Setter
    private Long id;

    @Column(unique = true)
    private String name;

    private boolean hallucinogenic;

    @ManyToOne
    @JoinColumn(name = "family")
    private Family family;

    @Override
    public String toString() {
        return "ID: " + id + "   NAME: " + name + "   FAMILY: {" + family + "}   IS HALLUCINOGENIC: " + hallucinogenic;
    }
}
