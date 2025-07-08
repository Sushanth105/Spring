package com.coading.jpa.models;

// import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// @DiscriminatorValue(value = "FILE")
@PrimaryKeyJoinColumn(name = "file_id")
@EqualsAndHashCode(callSuper = true)
public class File extends Resources {
    private String type;
}
