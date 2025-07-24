package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.*;
import com.example.demo.info.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long>{

}