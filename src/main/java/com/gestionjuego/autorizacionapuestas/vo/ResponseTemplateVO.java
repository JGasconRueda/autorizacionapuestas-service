package com.gestionjuego.autorizacionapuestas.vo;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
	
	private AutorizacionApuestas apuestas;
	private Ticket ticket;
}
