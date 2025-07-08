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
// @DiscriminatorValue(value = "VIDEO")
@PrimaryKeyJoinColumn(name = "video_id")
@EqualsAndHashCode(callSuper = true)
public class Video extends Resources{
    private Integer length;
}
