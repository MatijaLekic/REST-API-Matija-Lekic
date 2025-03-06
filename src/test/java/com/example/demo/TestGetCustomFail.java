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
public class TestGetCustomFail {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    @Order(1)
    public void testGetCustomZadaci1() throws Exception {
        
    	mockMvc.perform(get("/getCustomZadaci/Sortiraj/Nezapocet/Visok")
        		.param("page", "1")
                .param("size", "2"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Ne postoji nijedan zadatak")); 
    }
    
    @Test
    @Order(2)
    public void testSaveZadatakSuccess1() throws Exception {
    	
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
    @Order(3)
    public void testSaveZadatakSuccess2() throws Exception {
    	
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
    @Order(4)
    public void testSaveZadatakSuccess3() throws Exception {
    	
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
    @Order(5)
    public void testSaveZadatakSuccess4() throws Exception {
    	
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
    @Order(6)
    public void testSaveZadatakSuccess5() throws Exception {
    	
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
    @Order(7)
    public void testGetCustomZadaci2() throws Exception {
    	        
    	mockMvc.perform(get("/getCustomZadaci/Nesto/x/Visok")
        		.param("page", "1")
                .param("size", "2"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Prvi argument moze biti samo: Sortiraj ili x")); 
    }
    
    @Test
    @Order(8)
    public void testGetCustomZadaci3() throws Exception {
    	        
    	mockMvc.perform(get("/getCustomZadaci/Sortiraj/Nesto/x")
        		.param("page", "1")
                .param("size", "2"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Drugi argument moze biti samo: Nezapocet, U toku, Gotov ili x")); 
    }
    
    @Test
    @Order(8)
    public void testGetCustomZadaci4() throws Exception {
    	        
    	mockMvc.perform(get("/getCustomZadaci/x/Nezapocet/Nesto")
        		.param("page", "1")
                .param("size", "2"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Treci argument moze biti samo: Visok, Nizak, Srednji ili x")); 
    }
    
    @Test
    @Order(9)
    public void testGetCustomZadaci5() throws Exception {
    	        
    	mockMvc.perform(get("/getCustomZadaci/Sortiraj/Gotov/Srednji")
        		.param("page", "1")
                .param("size", "2"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Ne postoji nijedan zadatak")); 
    }
}
