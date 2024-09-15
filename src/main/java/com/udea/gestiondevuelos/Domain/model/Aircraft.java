package com.udea.gestiondevuelos.Domain.model;
import com.udea.gestiondevuelos.Domain.Enums.AircraftModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AircraftModel aircraftModel;

    private int maxSeats;

    private String seatConfiguration;

    public Aircraft() {
    }

    public Aircraft(Long id, AircraftModel aircraftModel, int maxSeats, String seatConfiguration) {
        this.id = id;
        this.aircraftModel = aircraftModel;
        this.maxSeats = maxSeats;
        this.seatConfiguration = seatConfiguration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AircraftModel getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(AircraftModel aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getSeatConfiguration() {
        return seatConfiguration;
    }

    public void setSeatConfiguration(String seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }
}