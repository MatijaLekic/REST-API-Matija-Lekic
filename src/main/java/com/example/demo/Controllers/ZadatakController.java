package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.Clan;
import com.example.demo.Models.Zadatak;
import com.example.demo.Repo.ClanRepo;
import com.example.demo.Repo.ZadatakRepo;
import com.example.demo.Validators.ObjectsValidator;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
public class ZadatakController {

	@Autowired
	private ZadatakRepo zadatakRepo;
	
	@Autowired
	private ClanRepo clanRepo;
	
	private final ObjectsValidator<Zadatak> validator = new ObjectsValidator<Zadatak>();
	
	@GetMapping(value = "/")
	public String getPage()
	{
		return "Welcome";
	}
	
	@GetMapping(value = "/getZadaci")			
	public ResponseEntity<?> getZadaci()						//ResponseEntity kako bi mogli da se vrate string ili zadaci
	{
		List<Zadatak> zadaci = zadatakRepo.findAll();
		if (zadaci.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ne postoji nijedan zadatak");
		return ResponseEntity.ok(zadaci);
	}
	
	@PostMapping(value = "/saveZadatak")			
	public ResponseEntity<?> saveZadatak(@RequestBody Zadatak zadatak)
	{
		var violations = validator.validate(zadatak);
		if (!violations.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(". ", violations));
		
		zadatakRepo.save(zadatak);
		return ResponseEntity.status(HttpStatus.OK).body("Uspesno sacuvan zadatak");
	}
	
	@PutMapping(value = "/updateZadatak/{id}")
	public ResponseEntity<?> updateZadatak(@PathVariable long id, @RequestBody Zadatak noviZadatak)
	{
		var violations = validator.validate(noviZadatak);
		if (!violations.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.join(". ", violations));
		
		Optional<Zadatak> optionalZadatak = zadatakRepo.findById(id);
	    if (!optionalZadatak.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav zadatak");
	    Zadatak zadatak = optionalZadatak.get();
	      
		zadatak.setNaslov(noviZadatak.getNaslov());
		zadatak.setOpis(noviZadatak.getOpis());
		zadatak.setStatus(noviZadatak.getStatus());
		zadatak.setPrioritet(noviZadatak.getPrioritet());
		
		zadatakRepo.save(zadatak);
		return ResponseEntity.status(HttpStatus.OK).body("Uspesno azuriran zadatak");
	}
	
	@DeleteMapping(value = "/deleteZadatak/{id}")
	public ResponseEntity<?> deleteZadatak(@PathVariable long id)
	{
		Optional<Zadatak> optionalZadatak = zadatakRepo.findById(id);
	    if (!optionalZadatak.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav zadatak");
	    Zadatak zadatak = optionalZadatak.get();
		
		for (Clan clan : zadatak.getZadatakClanovi()) 
		{
			 clan.setClanZadatak(null);
	         clanRepo.save(clan); 
		}
		
		zadatakRepo.delete(zadatak);
		return ResponseEntity.status(HttpStatus.OK).body("Uspesno obrisan zadatak");
	}
	
	////////////////////   Upravaljanje clanovima   ///////////////////////////
	
	@PostMapping("/addClan/{zadatakId}/{clanId}")
    public ResponseEntity<?> addClan(@PathVariable long zadatakId, @PathVariable long clanId) 
	{
	    Optional<Zadatak> optionalZadatak = zadatakRepo.findById(zadatakId);
	    Optional<Clan> optionalClan = clanRepo.findById(clanId);
	    if (!optionalZadatak.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav zadatak");
	    if (!optionalClan.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav clan");
	    Zadatak zadatak = optionalZadatak.get();
        Clan clan = optionalClan.get();
        
        zadatak.addClan(clan);
        zadatakRepo.save(zadatak);
        clanRepo.save(clan);
        
        return ResponseEntity.status(HttpStatus.OK).body("Uspesno dodat clan");
    }
	
	@DeleteMapping("/removeClan/{zadatakId}/{clanId}")
    public ResponseEntity<?> removeClan(@PathVariable long zadatakId, @PathVariable long clanId) 
	{
	    Optional<Zadatak> optionalZadatak = zadatakRepo.findById(zadatakId);
	    Optional<Clan> optionalClan = clanRepo.findById(clanId);
	    if (!optionalZadatak.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav zadatak");
	    if (!optionalClan.isPresent())
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nije pronadjen takav clan");    
	    Zadatak zadatak = optionalZadatak.get();
        Clan clan = optionalClan.get();
        
        if (!zadatak.getZadatakClanovi().contains(clan))
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Clan nije na ovom zadatku");
         
        zadatak.removeClan(clan);
        zadatakRepo.save(zadatak);
        clanRepo.save(clan);
        
        return ResponseEntity.status(HttpStatus.OK).body("Uspesno oduzet clan");
    }

	////////////////////   Filtriranje  ///////////////////////////
	
	
	@GetMapping(value = "/getCustomZadaci/{sortiraj}/{status}/{prioritet}")
	public ResponseEntity<?> getCustomZadaci(@PathVariable String sortiraj, @PathVariable String status, @PathVariable String prioritet, Pageable pageable)
	{	
		Page<Zadatak> zadaciPage;
	    
		Pageable sortedPageable;
		if (sortiraj.equals("Sortiraj"))
			sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("naslov").ascending());
		else if (sortiraj.equals("x"))
			sortedPageable = pageable;
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Prvi argument moze biti samo: Sortiraj ili x");
			
	    if (status.equals("Nezapocet") || status.equals("U toku") || status.equals("Gotov"))
	    {
	    	if (prioritet.equals("Nizak") || prioritet.equals("Srednji") || prioritet.equals("Visok"))
	    		zadaciPage  = zadatakRepo.findByStatusAndPrioritet(status, prioritet, sortedPageable);
	    	else if (prioritet.equals("x"))
	    		zadaciPage  = zadatakRepo.findByStatus(status, sortedPageable);
	    	else
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Treci argument moze biti samo: Visok, Nizak, Srednji ili x");
	    } 
	    else if (status.equals("x"))
	    {
	    	if (prioritet.equals("Nizak") || prioritet.equals("Srednji") || prioritet.equals("Visok"))
	    		zadaciPage  = zadatakRepo.findByPrioritet(prioritet, sortedPageable);
	    	else if (prioritet.equals("x"))
	    		zadaciPage  = zadatakRepo.findAll(sortedPageable);
	    	else
	    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Treci argument moze biti samo: Visok, Nizak, Srednji ili x");
	    }
	    else
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Drugi argument moze biti samo: Nezapocet, U toku, Gotov ili x");
	    
	    if (zadaciPage.isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ne postoji nijedan zadatak");

	    return ResponseEntity.ok(zadaciPage.getContent());
	}

}
