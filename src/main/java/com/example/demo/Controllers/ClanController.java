package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.Clan;
import com.example.demo.Models.Zadatak;
import com.example.demo.Repo.ClanRepo;
import com.example.demo.Validators.ObjectsValidator;

@RestController
public class ClanController {

	@Autowired
	private ClanRepo clanRepo;
	
	private final ObjectsValidator<Clan> validator = new ObjectsValidator<Clan>();
	
	@GetMapping(value = "/1")
	public String getPage1()
	{
		return "Welcome 1";
	}
	
	@GetMapping(value = "/getClanovi")
	public ResponseEntity<?> getClanovi()
	{
		List<Clan> clanovi = clanRepo.findAll();
		if (clanovi.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ne postoji nijedan clan");
		 return ResponseEntity.ok(clanovi);
	}
	
	@PostMapping(value = "/saveClan")
	public ResponseEntity<?> saveClan(@RequestBody Clan clan)
	{
		var violations = validator.validate(clan);
		if (!violations.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(". ", violations));
		
		clanRepo.save(clan);
		return ResponseEntity.status(HttpStatus.OK).body("Uspesno sacuvan clan");
	}
	
	@PutMapping(value = "/updateClan/{id}")
	public ResponseEntity<?> updateClan(@PathVariable long id, @RequestBody Clan noviClan)
	{
		var violations = validator.validate(noviClan);
		if (!violations.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(". ", violations));
		
		Optional<Clan> optionalClan= clanRepo.findById(id);
	    if (!optionalClan.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav clan");
	    Clan clan = optionalClan.get();
	    
		clan.setIme(noviClan.getIme());
		clan.setPrezime(noviClan.getPrezime());
		
		clanRepo.save(clan);
		return ResponseEntity.status(HttpStatus.OK).body("Uspesno azuriran clan");
	}
	
	@DeleteMapping(value = "/deleteClan/{id}")
	public ResponseEntity<?> deleteClan(@PathVariable long id)
	{
		Optional<Clan> optionalClan= clanRepo.findById(id);
	    if (!optionalClan.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav clan");
	    Clan clan = optionalClan.get();
		
	    clan.getClanZadatak().getZadatakClanovi().remove(clan);
		clanRepo.delete(clan);
		return ResponseEntity.status(HttpStatus.OK).body("Uspesno obrisan clan");
	}
}
