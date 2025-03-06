package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class TestGetCustomSuccess {

    @Autowired
    private MockMvc mockMvc;
        
    @Test
    @Order(1)
    public void testSaveZadatak1() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Napraviti bazu podataka\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Visok\"}";
    	
        mockMvc.perform(post("/saveZadatak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan zadatak"));
    }
    
    @Test
    @Order(2)
    public void testSaveZadatak2() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Implementirati klijentsku stranu\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Visok\"}";
    	
        mockMvc.perform(post("/saveZadatak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan zadatak"));
    }
    
    @Test
    @Order(3)
    public void testSaveZadatak3() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Objava rada\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Visok\"}";
    	
        mockMvc.perform(post("/saveZadatak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan zadatak"));
    }
    
    
    @Test
    @Order(4)
    public void testSaveZadatak4() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Kreirati serversku stranu\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Srednji\"}";
    	
        mockMvc.perform(post("/saveZadatak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan zadatak"));
    }
    
    @Test
    @Order(5)
    public void testSaveZadatak5() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Testirati\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"U toku\","
    			+ "\"prioritet\":\"Visok\"}";
    	
        mockMvc.perform(post("/saveZadatak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan zadatak"));
    }
    
    @Test
    @Order(6)
    public void testGetZadaci() throws Exception {
    	
    	String zadatakJson = "{\"id\":1,\"naslov\":\"Napraviti bazu podataka\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[]},"
				   		   + "{\"id\":2,\"naslov\":\"Implementirati klijentsku stranu\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[]},"
				   		   + "{\"id\":3,\"naslov\":\"Objava rada\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[]},"
				   		   + "{\"id\":4,\"naslov\":\"Kreirati serversku stranu\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Srednji\",\"zadatakClanovi\":[]},"
				   		   + "{\"id\":5,\"naslov\":\"Testirati\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"U toku\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[]}";
    	
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json("[" + zadatakJson + "]")); 
    }
    
    @Test
    @Order(7)
    public void testGetCustomZadaci() throws Exception {
    	
    	String zadatakJson = "{\"id\":3,\"naslov\":\"Objava rada\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[]}";
        
    	mockMvc.perform(get("/getCustomZadaci/Sortiraj/Nezapocet/Visok")
        		.param("page", "1")
                .param("size", "2"))
               .andDo(print())  
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json("[" + zadatakJson + "]")); 
    }
}
