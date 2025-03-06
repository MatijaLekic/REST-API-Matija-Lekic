package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Zadatak;

public interface ZadatakRepo extends JpaRepository<Zadatak, Long> {
	
	 Page<Zadatak> findByStatusAndPrioritet(String status, String prioritet, Pageable pageable);
	 Page<Zadatak> findByStatus(String status, Pageable pageable);
	 Page<Zadatak> findByPrioritet(String prioritet, Pageable pageable);
	 Page<Zadatak> findAll(Pageable pageable);
}
