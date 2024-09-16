package com.udea.gestiondevuelos.Repository;

import com.udea.gestiondevuelos.Domain.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAircraftRepository extends JpaRepository<Aircraft,Long> {
}
