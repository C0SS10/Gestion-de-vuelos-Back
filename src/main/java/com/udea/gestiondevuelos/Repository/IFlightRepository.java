package com.udea.gestiondevuelos.Repository;

import com.udea.gestiondevuelos.Domain.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<Flight,Long> {
}
