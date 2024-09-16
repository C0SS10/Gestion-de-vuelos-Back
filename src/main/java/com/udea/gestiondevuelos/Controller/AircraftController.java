package com.udea.gestiondevuelos.Controller;

import com.udea.gestiondevuelos.Domain.DTO.AircraftDTO;
import com.udea.gestiondevuelos.Service.IAircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AircraftController {

    @Autowired
    private IAircraftService aircraftService;

    @QueryMapping(name = "allAircrafts")
    public List<AircraftDTO> allAircrafts(){
        return aircraftService.getAllAircrafts();
    }
}
