package com.coading.jpa.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

// import jakarta.persistence.DiscriminatorColumn;
// import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name = "resources_type" , discriminatorType = DiscriminatorType.STRING )
public class Resources {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "resources_sequence")
    @SequenceGenerator(name = "resources_sequence",sequenceName = "resources_sequence",allocationSize = 1)
    private Integer id;
    private String name;
    private Integer size;
    private String url;
    
    @OneToOne(mappedBy = "resources")
    @JsonBackReference
    private Lecture lecture;

    public Resources(String name, Integer size, String url) {
        this.name = name;
        this.size = size;
        this.url = url;
    }
    
}
