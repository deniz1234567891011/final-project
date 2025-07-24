package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.BasketEntity;
import com.example.demo.info.ClothingInfo;
import com.example.demo.request.UpdateQuantityRequest;
import com.example.demo.response.BasketResponse;
import com.example.demo.service.BasketService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;



@RestController
@RequestMapping("/basket")
@CrossOrigin("*")
public class BasketController {
	@Autowired
	private BasketService basketService;
@GetMapping("/basket")
@PreAuthorize("hasRole('ROLE_USER')")
public List<BasketResponse> getlIList(){
	return basketService.getMyBasket1();
}
@PostMapping("/add/{carId}")
@PreAuthorize("hasRole('ROLE_USER')")
public void addToBasket(@PathVariable Long carId) {
    System.out.println("Метод add вызван!");
    basketService.addToBasket(carId);
}


	@DeleteMapping("/remove/{carId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void removeFromBasket(@PathVariable Long carId) {
		basketService.removeFromBasket(carId);
	}
//	@PutMapping("/update/{id}")
//	@PreAuthorize("hasRole('ROLE_USER')")
//	public void updateCar(@PathVariable Long id, @RequestBody ClothingInfo car) {
//		service.uptadeCar(id, car);
//	}}



}