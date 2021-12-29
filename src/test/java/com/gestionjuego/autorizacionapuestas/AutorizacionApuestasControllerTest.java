package com.gestionjuego.autorizacionapuestas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutorizacionApuestasControllerTest {
	
	@Autowired  
    private MockMvc mvc;
	
	@Test  
    @Order(1)  
    public void testSaveAutorizacionApuestas() throws Exception { 
		this.mvc.perform(post("/autorizacionapuestas/save")    
						.contentType(MediaType.APPLICATION_JSON) 
						.content(getCreateAutorizacionApuestasData().toString()) 
						.accept(MediaType.APPLICATION_JSON)) 
				.andExpect(status().isCreated()); 
	}
	
	@Test  
    @Order(2)  
    public void testGetAutorizacionApuestasById() throws Exception { 
		
		Long AutorizacionApuestasId = getCreateAutorizacionApuestasData().getLong("autorizacionApuestasId");
		
		this.mvc.perform(get("/autorizacionapuestas/"+AutorizacionApuestasId)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(getCreateAutorizacionApuestasData().toString()));
	}
	
	@Test  
    @Order(3)  
    public void testGetAllAutorizacionApuestas() throws Exception { 
		this.mvc.perform(get("/autorizacionapuestas/all")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[" + getCreateAutorizacionApuestasData().toString() + "]"));
	}
	
	@Test
	@Order(4)
	public void testUpdateAutorizacionApuestas() throws Exception {
		this.mvc.perform(post("/autorizacionapuestas/update")    
				.contentType(MediaType.APPLICATION_JSON) 
				.content(getUpdateAutorizacionApuestasData().toString()) 
				.accept(MediaType.APPLICATION_JSON)) 
			.andExpect(status().isOk()); 
	}
	
	@Test  
    @Order(5)  
    public void testdeleteAutorizacionApuestas() throws Exception { 
		
		Long AutorizacionApuestasId = getCreateAutorizacionApuestasData().getLong("autorizacionApuestasId");
		
		this.mvc.perform(delete("/autorizacionapuestas/"+AutorizacionApuestasId))
			.andExpect(status().isNoContent());
	}
	
	public JSONObject getCreateAutorizacionApuestasData() throws JSONException { 
        JSONObject createAutorizacionApuestasData = new JSONObject(); 
        createAutorizacionApuestasData.put("autorizacionApuestasId", 1L); 
        createAutorizacionApuestasData.put("nroRegistro", "TEST"); 
        return createAutorizacionApuestasData; 
    }
    
    public JSONObject getUpdateAutorizacionApuestasData() throws JSONException { 
        JSONObject createAutorizacionApuestasData = new JSONObject(); 
        createAutorizacionApuestasData.put("autorizacionApuestasId", 1L); 
        createAutorizacionApuestasData.put("nroRegistro", "TEST Update"); 
        return createAutorizacionApuestasData; 
    }
}
