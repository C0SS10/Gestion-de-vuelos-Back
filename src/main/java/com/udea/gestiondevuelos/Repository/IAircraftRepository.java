package com.udea.gestiondevuelos.Repository;

import com.udea.gestiondevuelos.Domain.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAircraftRepository extends JpaRepository<Aircraft,Long>, JpaSpecificationExecutor<Aircraft> {
}
