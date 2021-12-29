package com.gestionjuego.autorizacionapuestas.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;
import com.gestionjuego.autorizacionapuestas.repository.AutorizacionApuestasRepository;

@Component
@Profile("default")
public class DataLoaderPostgres implements CommandLineRunner {
	
	private final AutorizacionApuestasRepository autorizacionApuestasRepository;
    
    public DataLoaderPostgres(AutorizacionApuestasRepository autorizacionApuestasRepository) {
        this.autorizacionApuestasRepository = autorizacionApuestasRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
    	if(autorizacionApuestasRepository.count() == 0L) {
    		loadData();
    	}
    	else {
    		System.out.println("Postgres DataLoaded...");
    	}
    }

    private void loadData() {

    	AutorizacionApuestas apuestas = new AutorizacionApuestas("A-1234", "9988774455V", "Pepe Perez", "Calle Nueva", 
    															"Apuestas Deportivas", LocalDate.now(), LocalDate.of(2025, 12, 31), 1L);	
    	autorizacionApuestasRepository.save(apuestas);
    
    	apuestas = new AutorizacionApuestas("A-9999", "12457836P", "Juan Gomez", "Avenida de Extremadura", 
    															"Bingo", LocalDate.now(), LocalDate.of(2030, 12, 31), 2L);	
    	autorizacionApuestasRepository.save(apuestas);
    	
        System.out.println("Loaded Postgre Data...");
    }
}
