package com.gestionjuego.autorizacionapuestas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;
import com.gestionjuego.autorizacionapuestas.exceptions.AutorizacionApuestasException;
import com.gestionjuego.autorizacionapuestas.repository.AutorizacionApuestasRepository;

@Service
public class AutorizacionApuestasService {

	@Autowired
	private AutorizacionApuestasRepository autorizacionApuestasRepository;

	public AutorizacionApuestas save(AutorizacionApuestas autorizacionapuestas) {
		return autorizacionApuestasRepository.save(autorizacionapuestas);
	}

	public Optional<AutorizacionApuestas> findById(Long id) {
		return autorizacionApuestasRepository.findById(id);
	}

	public List<AutorizacionApuestas> getall() {
		List<AutorizacionApuestas> list = new ArrayList<>();
		autorizacionApuestasRepository.findAll().forEach(list::add);
        return list;
	}

	public void remove(Long id) {
		autorizacionApuestasRepository.deleteById(id);
		
	}

	public AutorizacionApuestas update(AutorizacionApuestas autorizacionApuestas) {
		Optional<AutorizacionApuestas> optional = autorizacionApuestasRepository.findById(autorizacionApuestas.getAutorizacionApuestasId());
		
		if(!optional.isPresent()) {
			throw new AutorizacionApuestasException("AutorizacionApuestas with id "+autorizacionApuestas.getAutorizacionApuestasId()+" not found");
		}
		
		AutorizacionApuestas original = optional.get();
		
		BeanUtils.copyProperties(autorizacionApuestas, original);
		
		return autorizacionApuestasRepository.save(original);
	}
}
