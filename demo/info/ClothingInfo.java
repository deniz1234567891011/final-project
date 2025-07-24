package com.example.demo.info;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name = "Clothes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand; 
    private String category; 
    private String description;
    private double price;
    private double rating;
    @Column(length = 50000)
    private String imageUrl;
    private Long ownerId;
    private Long year;

    
}

