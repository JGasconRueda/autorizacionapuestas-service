package com.gestionjuego.autorizacionapuestas.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorizacionApuestas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long autorizacionApuestasId;
	private String nroRegistro;
	private String nifTitular;
	private String nombreTitular;
	private String domicilioTitular;
	private String valTiposApuestas;
	private LocalDate fechaInicio;
	private LocalDate fechaExtincion;
	
}
