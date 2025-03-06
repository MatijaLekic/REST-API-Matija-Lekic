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
public class TestCrudSuccess {

    @Autowired
    private MockMvc mockMvc;
        
    @Test
    @Order(1)
    public void testSaveZadatak() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Test Naslov\","
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
    public void testGetZadaci() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Test Naslov\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Visok\"}";
    	
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json("[" + zadatakJson + "]")); 
    }
    
    @Test
    @Order(3)
    public void testUpdateZadatak() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Test Azuriran Naslov\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Visok\"}";
    	
    	mockMvc.perform(put("/updateZadatak/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno azuriran zadatak"));
    }
    
    @Test
    @Order(4)
    public void testSaveClan1() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"ime\":\"Pera\","
    			+ "\"prezime\":\"Peric\"}";
    	
        mockMvc.perform(post("/saveClan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan clan"));
    }
    
    @Test
    @Order(5)
    public void testSaveClan2() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"ime\":\"Matija\","
    			+ "\"prezime\":\"Lekic\"}";
    	
        mockMvc.perform(post("/saveClan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno sacuvan clan"));
    }
    
    @Test
    @Order(6)
    public void testAddClan1() throws Exception {
    
    	mockMvc.perform(post("/addClan/1/1"))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno dodat clan"));
    }
    
    @Test
    @Order(7)
    public void testAddClan2() throws Exception {
    
    	
    	mockMvc.perform(post("/addClan/1/2"))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno dodat clan"));
    }
    
    @Test
    @Order(8)
    public void testGetZadaciAfterUpdate() throws Exception {
    	
    	String zadatakJson = "{\"id\":1,\"naslov\":\"Test Azuriran Naslov\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[{\"id\":1,\"ime\":\"Pera\",\"prezime\":\"Peric\"},{\"id\":2,\"ime\":\"Matija\",\"prezime\":\"Lekic\"}]}";		
    			
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json("[" + zadatakJson + "]")); 
    }
    
    @Test
    @Order(9)
    public void testRemoveClan() throws Exception {
    	
    	mockMvc.perform(delete("/removeClan/1/1"))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno oduzet clan"));
    }
    
    @Test
    @Order(10)
    public void testGetZadaciAfterRemoveClan() throws Exception {
    	
    	String zadatakJson = "{\"id\":1,\"naslov\":\"Test Azuriran Naslov\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[{\"id\":2,\"ime\":\"Matija\",\"prezime\":\"Lekic\"}]}";		
    			
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json("[" + zadatakJson + "]")); 
    }
    
    @Test
    @Order(11)
    public void testDeleteClan() throws Exception {
    	
    	mockMvc.perform(delete("/deleteClan/2"))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno obrisan clan"));
    }
    
    @Test
    @Order(12)
    public void testGetZadaciDeleteClan() throws Exception {
    	
    	String zadatakJson = "{\"id\":1,\"naslov\":\"Test Azuriran Naslov\",\"opis\":\"Test Opis 11111111111111111\",\"status\":\"Nezapocet\",\"prioritet\":\"Visok\",\"zadatakClanovi\":[]}";		
    			
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
               .andExpect(content().json("[" + zadatakJson + "]")); 
    }
    
    @Test
    @Order(13)
    public void testDeleteZadatak() throws Exception {
    	
    	mockMvc.perform(delete("/deleteZadatak/1"))
        		.andDo(print()) 
                .andExpect(status().isOk())
                .andExpect(content().string("Uspesno obrisan zadatak"));
    }
    
    @Test
    @Order(14)
    public void testGetZadaciAfterDelete() throws Exception {
    	
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Ne postoji nijedan zadatak")); 
    }
}
