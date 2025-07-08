package com.coading.jpa.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "section_sequence")
    @SequenceGenerator(name = "section_sequence",sequenceName = "section_sequence",allocationSize = 1)
    private Integer id;
    private String name;
    private Integer sectionOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @OneToMany(mappedBy = "section" , cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Lecture> lectures;
    
    public Section(String name, Integer sectionOrder) {
        this.name = name;
        this.sectionOrder = sectionOrder;
    }
    
}
