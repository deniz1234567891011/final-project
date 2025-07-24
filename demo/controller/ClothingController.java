package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.ClothingInfo;
import com.example.demo.jwt.SecurityUtil;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ClothingService;

import org.springframework.web.bind.annotation.RequestBody; 


@RestController
@RequestMapping("/clothes")
@CrossOrigin("*")
public class ClothingController {
	@Autowired
	private ClothingService service;

	@GetMapping
	public List<ClothingInfo> getAllCars() {
		return service.getAllCars();
	}
	@GetMapping("/my-items")
	public List<ClothingInfo> getMyCars() {
		return service.getMyCars();
	}
	@GetMapping("/{id}")
	public Optional<ClothingInfo> getCar(@PathVariable Long id) {
		return service.getCarById(id);
	}

	@GetMapping("/search")
	public List<ClothingInfo> searchCars(@RequestParam String keyword) {
		return service.searchCars(keyword);
	}

	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void addCar(@RequestBody ClothingInfo ClothingInfo) {
		service.addCarForCurrentUser(ClothingInfo);

	}

	@DeleteMapping("delete/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void deleteCar(@PathVariable Long id) {
		service.deleteCar(id);
	}

	@GetMapping("/rating")
	public List<ClothingInfo> byRating(@RequestParam Integer minRating) {
		return service.filterByMinRating(minRating);
	}

	@GetMapping("/sort-by-price-asc")
	public List<ClothingInfo> byPriceAsc() {
		return service.sortByPriceAsc();

	}

	@GetMapping("/sort-by-price-desc")
	public List<ClothingInfo> byPriceDesc() {
		return service.sortByPriceDesc();

	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public void updateCar(@PathVariable Long id, @RequestBody ClothingInfo car) {
		service.uptadeCar(id, car);
	}}

