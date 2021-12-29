package com.gestionjuego.autorizacionapuestas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;
import com.gestionjuego.autorizacionapuestas.service.AutorizacionApuestasService;

@RestController
@RequestMapping("/autorizacionapuestas")
public class AutorizacionApuestasController {

	@Autowired
	private AutorizacionApuestasService autorizacionApuestasService;
	
	@PostMapping("/save")
	public ResponseEntity<AutorizacionApuestas> saveAutorizacionapuestas(@RequestBody AutorizacionApuestas autorizacionapuestas) {
		return new ResponseEntity<>(autorizacionApuestasService.save(autorizacionapuestas), HttpStatus.CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<AutorizacionApuestas> updateAutorizacionapuestas(@RequestBody AutorizacionApuestas autorizacionApuestas){
		return new ResponseEntity<>(autorizacionApuestasService.update(autorizacionApuestas), HttpStatus.OK);
	}
	
    @GetMapping("/{id}")  
    public ResponseEntity<AutorizacionApuestas> getAutorizacionApuestasById(@PathVariable Long id) { 
    	Optional<AutorizacionApuestas> optional = autorizacionApuestasService.findById(id);
    	
    	return optional.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
    @GetMapping("/all")  
    public ResponseEntity<List<AutorizacionApuestas>> getAllAutorizacionApuestas() { 
        return new ResponseEntity<> (autorizacionApuestasService.getall(), HttpStatus.OK);
    }
  
    @DeleteMapping("/{id}")  
    public ResponseEntity<Object> deleteAutorizacionApuestas(@PathVariable Long id) { 
    	autorizacionApuestasService.remove(id); 
    	return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    } 
	
}
