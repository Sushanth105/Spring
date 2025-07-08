package com.coading.jpa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "lecture_sequence")
    @SequenceGenerator(name = "lecture_sequence",sequenceName = "lecture_sequence", allocationSize = 1)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonBackReference
    private Section section;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resources_id")
    @JsonManagedReference
    private Resources resources;
    
    public Lecture(String name) {
        this.name = name;
    }
    
}
