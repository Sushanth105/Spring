package com.coading.jpa.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data // it contains getter,setter for more details we can see the doc
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "auther_sequence")
    @SequenceGenerator(name = "auther_sequence" , sequenceName = "auther_sequence", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    @Column(
        unique = true,
        nullable = false
        )
    private String email;

    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Course> courses;

    public Author(String firstName, String lastName, Integer age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

}
