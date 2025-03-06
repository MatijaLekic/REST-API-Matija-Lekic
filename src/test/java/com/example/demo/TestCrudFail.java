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
public class TestCrudFail {

    @Autowired
    private MockMvc mockMvc;
        
    @Test
    @Order(1)
    public void testSaveZadatak() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"\","
    			+ "\"opis\":\"Nesto\","
    			+ "\"status\":\"Nesto\","
    			+ "\"prioritet\":\"Nesto\"}";
    	
        mockMvc.perform(post("/saveZadatak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
        		.andExpect(status().isBadRequest())
                .andExpect(content().string("Status moze biti samo: Nezapocet, U toku ili Gotov. Opis mora biti duzi od 20 karaktera. Naslov ne sme biti prazan. Prioritet moze biti samo: Visok, Nizak ili Srednji"));
    }
    
    @Test
    @Order(2)
    public void testGetZadaci() throws Exception {
    	   	
        mockMvc.perform(get("/getZadaci"))
               .andDo(print())  
               .andExpect(status().isBadRequest())
               .andExpect(content().string("Ne postoji nijedan zadatak")); 
    }
    
    @Test
    @Order(3)
    public void testUpdateZadatak1() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"\","
    			+ "\"opis\":\"Nesto\","
    			+ "\"status\":\"Nesto\","
    			+ "\"prioritet\":\"Nesto\"}";
    	
    	mockMvc.perform(put("/updateZadatak/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Status moze biti samo: Nezapocet, U toku ili Gotov. Opis mora biti duzi od 20 karaktera. Naslov ne sme biti prazan. Prioritet moze biti samo: Visok, Nizak ili Srednji"));
    }
    
    @Test
    @Order(4)
    public void testUpdateZadatak2() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"naslov\":\"Test Azuriran Naslov\","
    			+ "\"opis\":\"Test Opis 11111111111111111\","
    			+ "\"status\":\"Nezapocet\","
    			+ "\"prioritet\":\"Visok\"}";
    	
    	mockMvc.perform(put("/updateZadatak/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nije pronadjen takav zadatak"));
    }
    
    @Test
    @Order(5)
    public void testDeleteZadatak() throws Exception {
    	
    	mockMvc.perform(delete("/deleteZadatak/1"))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nije pronadjen takav zadatak"));
    }
    
    @Test
    @Order(6)
    public void testSaveClan() throws Exception {
    	
    	String zadatakJson = "{"
    			+ "\"ime\":\"\","
    			+ "\"prezime\":\"\"}";
    	
        mockMvc.perform(post("/saveClan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(zadatakJson))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Ime ne sme biti prazno. Prezime ne sme biti prazno"));
    }
       
    @Test
    @Order(7)
    public void testAddClan() throws Exception {
    
    	mockMvc.perform(post("/addClan/1/1"))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nije pronadjen takav zadatak"));
    }
       
    @Test
    @Order(8)
    public void testRemoveClan1() throws Exception {
    	
    	mockMvc.perform(delete("/removeClan/1/1"))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nije pronadjen takav zadatak"));
    }
    
    @Test
    @Order(9)
    public void testSaveZadatakSuccess() throws Exception {
    	
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
    @Order(10)
    public void testSaveClanSuccess() throws Exception {
    	
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
    @Order(11)
    public void testRemoveClan() throws Exception {
    	
    	mockMvc.perform(delete("/removeClan/1/1"))
        		.andDo(print()) 
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Clan nije na ovom zadatku"));
    }
}
