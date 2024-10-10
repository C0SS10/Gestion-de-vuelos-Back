package com.udea.gestiondevuelos.Service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.udea.gestiondevuelos.Domain.DTO.AircraftDTO;
import com.udea.gestiondevuelos.Domain.Enums.AircraftModel;
import com.udea.gestiondevuelos.Domain.Enums.SeatConfiguration;
import com.udea.gestiondevuelos.Domain.model.Aircraft;
import com.udea.gestiondevuelos.Domain.model.QAircraft;
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
    public List<AircraftDTO> filterAircrafts(String aircraftModel, Integer maxSeats, String seatConfiguration){
        QAircraft aircraft = QAircraft.aircraft;
        BooleanExpression predicate = aircraft.isNotNull();
        if(aircraftModel != null){
            AircraftModel modelEnum = AircraftModel.valueOf(aircraftModel);
            predicate = predicate.and(aircraft.aircraftModel.eq(modelEnum));
        }
        if(maxSeats != null){
            predicate = predicate.and(aircraft.maxSeats.goe(maxSeats));
        }
        if(seatConfiguration != null){
            SeatConfiguration configuration = SeatConfiguration.valueOf(seatConfiguration);
            predicate = predicate.and(aircraft.seatConfiguration.eq(configuration));
        }
        List<Aircraft> aircrafts = (List<Aircraft>) aircraftRepository.findAll(predicate); // Casting a List
        return aircrafts.stream()
                .map(item -> aircraftMappers.toAircraftDTO(item))
                .collect(Collectors.toList());
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
