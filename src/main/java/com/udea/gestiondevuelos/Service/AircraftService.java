package com.udea.gestiondevuelos.Service;

import com.udea.gestiondevuelos.Domain.DTO.AircraftDTO;
import com.udea.gestiondevuelos.Domain.model.Aircraft;
import com.udea.gestiondevuelos.Repository.IAircraftRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AircraftService implements IAircraftService{

    @Autowired
    private IAircraftRepository aircraftRepository;

    @Override
    public AircraftDTO createAircraft ( AircraftDTO aircraftDTO){
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftModel(aircraftDTO.getAircraftModel());
        aircraft.setMaxSeats(aircraftDTO.getMaxSeats());
        aircraft.setSeatConfiguration(aircraftDTO.getSeatConfiguration());
        return toDTO(aircraftRepository.save(aircraft));
    }

    @Override
    public List<AircraftDTO> getAllAircrafts(){
        List<Aircraft> aircrafts = aircraftRepository.findAll();
        return aircrafts.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AircraftDTO getAircraftById(Long id){
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("El avion con el ID %d no fue encontrado", id)));
        return toDTO(aircraft);
    }

    @Override
    public AircraftDTO updateAircraft(Long id, AircraftDTO aircraftDTO){
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("El avion con el ID %d no fue encontrado", id)));
        aircraft.setAircraftModel(aircraftDTO.getAircraftModel());
        aircraft.setMaxSeats(aircraftDTO.getMaxSeats());
        aircraft.setSeatConfiguration(aircraftDTO.getSeatConfiguration());
        return toDTO(aircraftRepository.save(aircraft));
    }

    @Override
    public void deleteAircraft(Long id){
        if(!aircraftRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El avion con el ID %d no fue encontrado", id));
        }
        aircraftRepository.deleteById(id);
    }


    //Method to get the equivalent DTO from an Entity
    private AircraftDTO toDTO(Aircraft aircraft){
        AircraftDTO aircraftDTO = new AircraftDTO();
        aircraftDTO.setId(aircraft.getId());
        aircraftDTO.setAircraftModel(aircraft.getAircraftModel());
        aircraftDTO.setMaxSeats(aircraft.getMaxSeats());
        aircraftDTO.setSeatConfiguration(aircraft.getSeatConfiguration());
        return aircraftDTO;
    }
}
