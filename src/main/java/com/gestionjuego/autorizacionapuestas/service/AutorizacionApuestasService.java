package com.gestionjuego.autorizacionapuestas.service;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;
import com.gestionjuego.autorizacionapuestas.exceptions.AutorizacionApuestasException;
import com.gestionjuego.autorizacionapuestas.repository.AutorizacionApuestasRepository;
import com.gestionjuego.autorizacionapuestas.vo.ResponseTemplateVO;
import com.gestionjuego.autorizacionapuestas.vo.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorizacionApuestasService {

	@Autowired
	private AutorizacionApuestasRepository autorizacionApuestasRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(AutorizacionApuestasService.class);

	public AutorizacionApuestas save(AutorizacionApuestas autorizacionapuestas) {
		logger.info("AutorizacionApuestasService - save: "+autorizacionapuestas.toString());
		return autorizacionApuestasRepository.save(autorizacionapuestas);
	}

	public List<ResponseTemplateVO> getall() {
		List<ResponseTemplateVO> list = new ArrayList<>();
		autorizacionApuestasRepository.findAll().forEach(a->list.add(new ResponseTemplateVO(a, getTickeyById(a.getTicketId()))));
		logger.info("AutorizacionApuestasService - getall: Return "+list.size()+" autorizaciones");
        return list;
	}

	public void remove(Long id) {
		logger.info("AutorizacionApuestasService - remove:Id "+id);
		autorizacionApuestasRepository.deleteById(id);
	}

	public AutorizacionApuestas update(AutorizacionApuestas autorizacionApuestas) {
		logger.info("AutorizacionApuestasService - update: autorizacionApuestas "+autorizacionApuestas.toString());
		Optional<AutorizacionApuestas> optional = autorizacionApuestasRepository.findById(autorizacionApuestas.getId());
		
		if(!optional.isPresent()) {
			logger.error("AutorizacionApuestasService - update: AutorizacionApuestasNotFound");
			throw new AutorizacionApuestasException("AutorizacionApuestas with id "+autorizacionApuestas.getId()+" not found");
		}
		
		AutorizacionApuestas original = optional.get();
		
		BeanUtils.copyProperties(autorizacionApuestas, original);
		
		return autorizacionApuestasRepository.save(original);
	}

	public Optional<ResponseTemplateVO> getAutorizacionApuestasWithTicket(Long id) {
		logger.info("AutorizacionApuestasService - getAutorizacionApuestasWithTicket:Id "+id);
		ResponseTemplateVO vo = new ResponseTemplateVO();
		Ticket ticket = new Ticket();
		
		Optional<AutorizacionApuestas> apuestas = autorizacionApuestasRepository.findById(id);
		
		if(!apuestas.isPresent()) {
			logger.info("AutorizacionApuestasService - getAutorizacionApuestasWithTicket: Not found");
			return Optional.empty();
		}
		try{
			ticket = getTickeyById(apuestas.get().getTicketId());
		}catch (Exception e) {
			logger.error("AutorizacionApuestasService - getAutorizacionApuestasWithTicket: TicketNotFound");
			return Optional.empty();
		}
		
		vo.setApuestas(apuestas.get());
		vo.setTicket(ticket);
		
		return Optional.of(vo);
	}

	private Ticket getTickeyById(Long id) {
		logger.info("AutorizacionApuestasService - getTickeyById:TicketId "+id);
		Ticket ticket = restTemplate.getForObject("http://TICKET-SERVICE/tickets/"+id, Ticket.class);
		return ticket;
	}
}
