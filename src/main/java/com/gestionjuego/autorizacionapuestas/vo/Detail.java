package com.gestionjuego.autorizacionapuestas.vo;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "ticket")
@ToString(exclude = "ticket")
public class Detail {

	private Long id;
	private Integer lineID;
	@JsonBackReference
    private Ticket ticket;
}
