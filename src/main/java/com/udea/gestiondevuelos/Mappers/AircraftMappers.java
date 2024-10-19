package com.udea.gestiondevuelos.mappers;

import com.udea.gestiondevuelos.domain.dto.AircraftDTO;
import com.udea.gestiondevuelos.domain.model.Aircraft;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AircraftMappers {
    public Aircraft toAircraftEntity(AircraftDTO aircraftDTO) {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(aircraftDTO.getId());
        aircraft.setAircraftModel(aircraftDTO.getAircraftModel());
        aircraft.setMaxSeats(aircraftDTO.getMaxSeats());
        aircraft.setSeatConfiguration(aircraftDTO.getSeatConfiguration());
        aircraft.setFlights(aircraftDTO.getFlights().stream()
                .map(flightDTO -> new FlightMappers().toFlightEntity(flightDTO)).collect(Collectors.toList()));

        return aircraft;
    }

    public AircraftDTO toAircraftDTO(Aircraft aircraft) {
        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setId(aircraft.getId());
        aircraftDTO.setAircraftModel(aircraft.getAircraftModel());
        aircraftDTO.setMaxSeats(aircraft.getMaxSeats());
        aircraftDTO.setSeatConfiguration(aircraft.getSeatConfiguration());
        aircraftDTO.setFlights(aircraft.getFlights().stream().map(flight -> new FlightMappers().toFlightDTO(flight))
                .collect(Collectors.toList()));

        return aircraftDTO;
    }
}
