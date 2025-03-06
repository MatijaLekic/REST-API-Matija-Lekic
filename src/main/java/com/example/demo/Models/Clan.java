package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Clan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	@NotNull(message = "Ime ne sme biti prazno")
    @NotEmpty(message = "Ime ne sme biti prazno")
	private String ime;
	
	@Column
	@NotNull(message = "Prezime ne sme biti prazno")
    @NotEmpty(message = "Prezime ne sme biti prazno")
	private String prezime;
	
	@ManyToOne
	@JsonIgnore
	private Zadatak clanZadatak;
	
	
    public long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    
	////////////////////   Upravaljanje zadatkom   ///////////////////////////
    
    public Zadatak getClanZadatak() {
        return clanZadatak;
    }

    public void setClanZadatak(Zadatak clanZadatak) {
        this.clanZadatak = clanZadatak;
    }
}
