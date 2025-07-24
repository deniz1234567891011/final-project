package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketResponse {
	private Long id;
	private String brand;
	private String model;
	private String description;
	private Double price;
	private long year;
	private String imgUrl;
    private double rating;
	private Long ownerId;
	private Integer quantity;
}