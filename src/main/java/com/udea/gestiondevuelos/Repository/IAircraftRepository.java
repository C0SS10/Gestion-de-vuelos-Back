package com.udea.gestiondevuelos.Repository;

import com.udea.gestiondevuelos.Domain.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IAircraftRepository extends JpaRepository<Aircraft,Long>, QuerydslPredicateExecutor<Aircraft> {
}
