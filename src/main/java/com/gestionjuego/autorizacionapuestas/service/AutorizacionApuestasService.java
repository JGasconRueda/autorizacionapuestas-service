package com.gestionjuego.autorizacionapuestas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;
import com.gestionjuego.autorizacionapuestas.exceptions.AutorizacionApuestasException;
import com.gestionjuego.autorizacionapuestas.exceptions.TicketErrorException;
import com.gestionjuego.autorizacionapuestas.repository.AutorizacionApuestasRepository;
import com.gestionjuego.autorizacionapuestas.vo.ResponseTemplateVO;
import com.gestionjuego.autorizacionapuestas.vo.Ticket;

@Service
public class AutorizacionApuestasService {

	@Autowired
	private AutorizacionApuestasRepository autorizacionApuestasRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	public AutorizacionApuestas save(AutorizacionApuestas autorizacionapuestas) {
		return autorizacionApuestasRepository.save(autorizacionapuestas);
	}

	public List<ResponseTemplateVO> getall() {
		List<ResponseTemplateVO> list = new ArrayList<>();
		autorizacionApuestasRepository.findAll().forEach(a->list.add(new ResponseTemplateVO(a, getTickeyById(a.getTicketId()))));
        return list;
	}

	public void remove(Long id) {
		autorizacionApuestasRepository.deleteById(id);
		
	}

	public AutorizacionApuestas update(AutorizacionApuestas autorizacionApuestas) {
		Optional<AutorizacionApuestas> optional = autorizacionApuestasRepository.findById(autorizacionApuestas.getId());
		
		if(!optional.isPresent()) {
			throw new AutorizacionApuestasException("AutorizacionApuestas with id "+autorizacionApuestas.getId()+" not found");
		}
		
		AutorizacionApuestas original = optional.get();
		
		BeanUtils.copyProperties(autorizacionApuestas, original);
		
		return autorizacionApuestasRepository.save(original);
	}

	public Optional<ResponseTemplateVO> getAutorizacionApuestasWithTicket(Long id) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Ticket ticket = new Ticket();
		
		Optional<AutorizacionApuestas> apuestas = autorizacionApuestasRepository.findById(id);
		
		if(!apuestas.isPresent()) {
			return Optional.empty();
		}
		try{
			ticket = getTickeyById(apuestas.get().getTicketId());
		}catch (Exception e) {
			return Optional.empty();
		}
		
		vo.setApuestas(apuestas.get());
		vo.setTicket(ticket);
		
		return Optional.of(vo);
	}

	private Ticket getTickeyById(Long id) {
		Ticket ticket = restTemplate.getForObject("http://TICKET-SERVICE/tickets/"+id, Ticket.class);
		return ticket;
	}
}
