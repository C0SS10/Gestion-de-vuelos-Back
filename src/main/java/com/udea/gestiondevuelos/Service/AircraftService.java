package com.udea.gestiondevuelos.Service;

import com.udea.gestiondevuelos.Domain.DTO.AircraftDTO;
import com.udea.gestiondevuelos.Domain.model.Aircraft;
import com.udea.gestiondevuelos.Mappers.AircraftMappers;
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

    @Autowired
    private AircraftMappers aircraftMappers;

    @Override
    public AircraftDTO createAircraft ( AircraftDTO aircraftDTO){
        return aircraftMappers.toAircraftDTO(aircraftRepository.save(aircraftMappers.toAircraftEntity(aircraftDTO)));
    }

    @Override
    public List<AircraftDTO> getAllAircrafts(){
        List<Aircraft> aircrafts = aircraftRepository.findAll();
        return aircrafts.stream().map(aircraft -> aircraftMappers.toAircraftDTO(aircraft)).collect(Collectors.toList());
    }

    @Override
    public AircraftDTO getAircraftById(Long id){
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("El avion con el ID %d no fue encontrado", id)));
        return aircraftMappers.toAircraftDTO(aircraft);
    }

    @Override
    public AircraftDTO updateAircraft(Long id, AircraftDTO aircraftDTO){
        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("El avion con el ID %d no fue encontrado", id)));
        if(aircraftDTO.getAircraftModel() != null){aircraft.setAircraftModel(aircraftDTO.getAircraftModel());}
        if(aircraftDTO.getMaxSeats() != null){aircraft.setMaxSeats(aircraftDTO.getMaxSeats());}
        if(aircraftDTO.getSeatConfiguration() != null){aircraft.setSeatConfiguration(aircraftDTO.getSeatConfiguration());}
        return aircraftMappers.toAircraftDTO(aircraftRepository.save(aircraft));
    }

    @Override
    public void deleteAircraft(Long id){
        if(!aircraftRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El avion con el ID %d no fue encontrado", id));
        }
        aircraftRepository.deleteById(id);
    }

}
