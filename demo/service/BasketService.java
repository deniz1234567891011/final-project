package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.info.BasketEntity;
import com.example.demo.info.ClothingInfo;
import com.example.demo.info.UserEntity;
import com.example.demo.jwt.SecurityUtil;
import com.example.demo.repository.BasketRepository;
import com.example.demo.repository.ClothingRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.BasketResponse;

import jakarta.transaction.Transactional;


@Service
public class BasketService {
	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClothingRepository carRepository;

//	public BasketEntity getBasketid(Long id) {
//	    return BasketRepository.f(id).orElse(null);
//	}

	
	public void addToBasket(long carId) {
	    String username = SecurityUtil.getCurrentUsername();
		BasketEntity basketEntity = new BasketEntity();
	    UserEntity user = userRepository.findByUsername(username).orElseThrow();
	    BasketEntity basket = basketRepository.findByUserIdAndCarId(user.getId(),carId).orElse(new BasketEntity());
	    basket.setUserId(user.getId());
	    basket.setCarId(carId);
	    if (basket.getQuantity() == null) {
	        basket.setQuantity(1);
	    } else {
	        basket.setQuantity(basket.getQuantity() + 1);
	    }
	    basketRepository.save(basket);
	}


	@Transactional
	public void removeFromBasket(Long carId) {
		String username = SecurityUtil.getCurrentUsername();
		UserEntity user = userRepository.findByUsername(username).orElseThrow();
		basketRepository.deleteByUserIdAndCarId(user.getId(), carId);
	}

	public List<BasketResponse> getMyBasket1() {
		String username = SecurityUtil.getCurrentUsername();
		 System.out.println("Username from token: " + username);
		UserEntity user = userRepository.findByUsername(username).orElseThrow();
		List<BasketEntity> baskets = basketRepository.findByUserId(user.getId());
		List<BasketResponse> responses = baskets.stream().map(basket -> {
			ClothingInfo carEntity = carRepository.findById(basket.getCarId()).orElse(null);
			BasketResponse basketResponse = new BasketResponse();
			basketResponse.setId(carEntity.getId());
			basketResponse.setBrand(carEntity.getBrand());
			basketResponse.setModel(carEntity.getCategory());
			basketResponse.setDescription(carEntity.getDescription());
			basketResponse.setPrice(carEntity.getPrice());
			basketResponse.setYear(carEntity.getYear());
			basketResponse.setImgUrl(carEntity.getImageUrl());
			basketResponse.setRating(carEntity.getRating());
			basketResponse.setOwnerId(carEntity.getOwnerId());
			basketResponse.setQuantity(basket.getQuantity());
			return basketResponse;
		}).collect(Collectors.toList());
		return responses;
	}
//	public void updateBasket(Long id, BasketEntity basketEntity) {
//	    Optional<BasketEntity> existing = Optional.ofNullable(getBasket(id));
//	    if(existing.isPresent()) {
//	        BasketEntity basket = existing.get();
//	        basket.setUserId(basketEntity.getUserId());
//	        basket.setCarId(basketEntity.getCarId());
//	        basket.setQuantity(basketEntity.getQuantity());
//	        basketRepository.save(basket);
//	    } else {
//	        throw new RuntimeException("Корзина с id " + id + " не найдена");
//	    }
//	}
//
//
//	}

}