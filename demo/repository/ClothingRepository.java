package com.example.demo.repository;

import java.util.List;import org.apache.catalina.startup.Catalina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.info.ClothingInfo;


@Repository
public interface ClothingRepository extends JpaRepository<ClothingInfo, Long> {
	List<ClothingInfo> findByOwnerId(Long ownerId);

	List<ClothingInfo> findAllByOrderByPriceAsc();

	List<ClothingInfo> findAllByOrderByPriceDesc();
	
	List<ClothingInfo> findByBrandContainingIgnoreCase(String brand);
	
	List<ClothingInfo> findAllByOrderByRatingDesc();
	
	List<ClothingInfo> findAllByRatingGreaterThanEqual(Integer minRating);
	
	

}