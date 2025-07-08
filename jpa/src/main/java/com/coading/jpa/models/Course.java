package com.coading.jpa.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "course_sequence")
    @SequenceGenerator(name = "course_sequence",sequenceName = "course_sequence",allocationSize = 1)
    private int id;
    private String title;
    private String description;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "course_author",
        joinColumns = {
            @JoinColumn(name = "course_id") //Points to the owner side
        },
        inverseJoinColumns = {
            @JoinColumn(name = "author_id") //Points to the owner side
        }
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Section> sections;
    
    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
}
