package com.udea.gestiondevuelos.Repository;

import com.udea.gestiondevuelos.Domain.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IFlightRepository extends JpaRepository<Flight,Long>, QuerydslPredicateExecutor<Flight> {
}
