package com.example.isa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String author;
    @Column()
    private String description;

    @Column()
    private String extension;

    public Picture(String author, String description, String extension){
        this.author = author;
        this.description = description;
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "ID: " + id + "   AUTHOR: " + author + "   DESCRIPTION: " + description;
    }
}
