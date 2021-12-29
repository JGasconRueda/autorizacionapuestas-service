package com.gestionjuego.autorizacionapuestas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionjuego.autorizacionapuestas.entity.AutorizacionApuestas;

@Repository
public interface AutorizacionApuestasRepository extends JpaRepository<AutorizacionApuestas, Long> {

}
