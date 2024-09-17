package com.udea.gestiondevuelos.Service;

import com.udea.gestiondevuelos.Domain.DTO.FlightDTO;
import com.udea.gestiondevuelos.Domain.model.Flight;
import com.udea.gestiondevuelos.Repository.IFlightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements  IFlightService{

    @Autowired
    public IFlightRepository flightRepository;

    @Override
    public FlightDTO createFlight(FlightDTO input){
        return toDTO(flightRepository.save(toEntity(input)));
    }

    @Override
    public List<FlightDTO> getAllFlights(){
        return flightRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public FlightDTO getFlightById(Long id){
        Flight flight = flightRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("El vuelo con el ID %d no fué encontrado",id)));
        return toDTO(flight);
    }

    @Override
    public FlightDTO updateFlight (Long id, FlightDTO input){
        Flight flight = flightRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(String.format("El vuelo con el ID %d no fue encontrado", id)));
        if(input.getFlightNumber() != null){flight.setFlightNumber(input.getFlightNumber());}
        if(input.getFlightType() != null){flight.setFlightType(input.getFlightType());}
        if(input.getDepartureCity() != null){flight.setDepartureCity(input.getDepartureCity());}
        if(input.getDestinationCity() != null){flight.setDestinationCity(input.getDestinationCity());}
        if(input.getAircraftModel() != null){flight.setAircraftModel(input.getAircraftModel());}
        if(input.getDepartureDate() != null){flight.setDepartureDate(input.getDepartureDate());}
        if(input.getArrivalDate() != null){flight.setArrivalDate(input.getArrivalDate());}
        if(input.getDepartureTime() != null){flight.setDepartureTime(input.getDepartureTime());}
        if(input.getArrivalTime() != null){flight.setArrivalTime(input.getArrivalTime());}
        if(input.getPrice() != null){flight.setPrice(input.getPrice());}
        if(input.getTaxPercentage() != null){flight.setTaxPercentage(input.getTaxPercentage());}
        if(input.getSurcharge() != null){flight.setSurcharge(input.getSurcharge());}
        return toDTO(flight);
    }

    public void deleteFlight(Long id){
        if(!flightRepository.existsById(id)){
            throw new EntityNotFoundException(String.format("El vuelo con el ID %d no fue encontrado",id));
        }
        flightRepository.deleteById(id);
    }

    //Method to get the equivalent DTO from an Entity
    private FlightDTO toDTO(Flight flight){
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setFlightType(flight.getFlightType());
        flightDTO.setDepartureCity(flight.getDepartureCity());
        flightDTO.setDestinationCity(flight.getDestinationCity());
        flightDTO.setAircraftModel(flight.getAircraftModel());
        flightDTO.setDepartureDate(flight.getDepartureDate());
        flightDTO.setArrivalDate(flight.getArrivalDate());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setArrivalTime(flight.getArrivalTime());
        flightDTO.setPrice(flight.getPrice());
        flightDTO.setTaxPercentage(flight.getTaxPercentage());
        flightDTO.setSurcharge(flight.getSurcharge());
        return flightDTO;
    }

    //Method to get the equivalent Entity from an DTO
    private Flight toEntity(FlightDTO flightDTO){
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setFlightType(flightDTO.getFlightType());
        flight.setDepartureCity(flightDTO.getDepartureCity());
        flight.setDestinationCity(flightDTO.getDestinationCity());
        flight.setAircraftModel(flightDTO.getAircraftModel());
        flight.setDepartureDate(flightDTO.getDepartureDate());
        flight.setArrivalDate(flightDTO.getArrivalDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setArrivalTime(flightDTO.getArrivalTime());
        flight.setPrice(flightDTO.getPrice());
        flight.setTaxPercentage(flightDTO.getTaxPercentage());
        flight.setSurcharge(flightDTO.getSurcharge());
        return flight;
    }
}
