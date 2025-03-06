package com.example.demo.Models;

import java.util.List;
import java.util.ArrayList;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class Zadatak {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull(message = "Naslov ne sme biti prazan")
    @NotEmpty(message = "Naslov ne sme biti prazan")
	private String naslov;
	
	@Column
	@Size(min = 20, message = "Opis mora biti duzi od 20 karaktera")
	private String opis;
	
	@Column
	@Pattern(regexp = "^(Nezapocet|U toku|Gotov)$", message = "Status moze biti samo: Nezapocet, U toku ili Gotov")
	private String status;
	
	@Column
	@Pattern(regexp = "^(Visok|Nizak|Srednji)$", message = "Prioritet moze biti samo: Visok, Nizak ili Srednji")
	private String prioritet;
	
	@OneToMany(mappedBy = "clanZadatak")							//ovo govori da ce biti foreign key u Clan
	private List<Clan> zadatakClanovi = new ArrayList<>();
	
	
    public long getId() {
        return id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }
    
	////////////////////   Upravaljanje clanovima   ///////////////////////////
    
    public List<Clan> getZadatakClanovi() {
        return zadatakClanovi;
    }
    
    public void addClan(Clan clan) {
    	zadatakClanovi.add(clan);
        clan.setClanZadatak(this);
    }

    public void removeClan(Clan clan) {
    	zadatakClanovi.remove(clan);
        clan.setClanZadatak(null);
    }
}
