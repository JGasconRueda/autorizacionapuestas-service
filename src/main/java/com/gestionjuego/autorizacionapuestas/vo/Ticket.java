package com.gestionjuego.autorizacionapuestas.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "details")
@ToString(exclude = "details")
public class Ticket {

	private Long id;
	private Date creationDate;
	private Double totalAmount;
	private Set<Detail> details = new HashSet<>();
}
