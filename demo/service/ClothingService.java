package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.ClothingInfo;
import com.example.demo.info.UserEntity;
import com.example.demo.jwt.SecurityUtil;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.CarRequest;



@Service
public class ClothingService {

    @Autowired
    private ClothingRepository repository;
@Autowired
private UserRepository userRepository;
public void addCarForCurrentUser(ClothingInfo clothingInfo2) {
	String username = SecurityUtil.getCurrentUsername();
	UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
	ClothingInfo ClothingInfo = new ClothingInfo();
	ClothingInfo.setBrand(clothingInfo2.getBrand());
	ClothingInfo.setCategory(clothingInfo2.getCategory());

	ClothingInfo.setDescription(clothingInfo2.getDescription());

	ClothingInfo.setPrice(clothingInfo2.getPrice());

	ClothingInfo.setYear(clothingInfo2.getYear());

	ClothingInfo.setImageUrl(clothingInfo2.getImageUrl());
	ClothingInfo.setRating(clothingInfo2.getRating());
	ClothingInfo.setOwnerId(userEntity.getId());
	repository.save(ClothingInfo);
}


	public List<ClothingInfo> getMyCars() {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
		return repository.findByOwnerId(userEntity.getId());
	}

	public ClothingInfo getMyCarById(Long id) {
		String username = SecurityUtil.getCurrentUsername();
		ClothingInfo ClothingInfo = repository.findById(id).orElseThrow(() -> new RuntimeException());
		Long currentUserId = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException()).getId();

		if (!ClothingInfo.getOwnerId().equals(currentUserId)) {
			throw new RuntimeException("");
		}
		return ClothingInfo;
	}

	public void deleteCar(Long id) {
		ClothingInfo ClothingInfo = getMyCarById(id);
		repository.delete(ClothingInfo);
	}

	public List<ClothingInfo> getAllCars() {
		return repository.findAll();
	}

	public Optional<ClothingInfo> getCarById(Long id) {
		return repository.findById(id);
	}

	public List<ClothingInfo> searchCars(String keyword) {
		return repository.findByBrandContainingIgnoreCase(keyword);
	}

	public List<ClothingInfo> filterByMinRating(Integer minRating) {
		return repository.findAllByRatingGreaterThanEqual(minRating);
	}

	public List<ClothingInfo> sortByPriceAsc() {
		return repository.findAllByOrderByPriceAsc();
	}

	public List<ClothingInfo> sortByPriceDesc() {
		return repository.findAllByOrderByPriceDesc();
	}

	public void uptadeCar(Long id, ClothingInfo ClothingInfo) {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
		Optional<ClothingInfo> oldCar = Optional.ofNullable(getMyCarById(id));
		if (oldCar.isPresent()) {
			ClothingInfo car = oldCar.get();
			car.setBrand(ClothingInfo.getBrand());
			car.setCategory(ClothingInfo.getCategory());

			car.setDescription(ClothingInfo.getDescription());

			car.setPrice(ClothingInfo.getPrice());

			car.setYear(ClothingInfo.getYear());

			car.setImageUrl(ClothingInfo.getImageUrl());
			car.setRating(ClothingInfo.getRating());
			car.setOwnerId(userEntity.getId());
			repository.save(car);

		}

	}

}
