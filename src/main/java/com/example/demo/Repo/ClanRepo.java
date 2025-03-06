package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Clan;

public interface ClanRepo extends JpaRepository<Clan, Long> {

}
