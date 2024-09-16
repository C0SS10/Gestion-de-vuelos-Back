package com.udea.gestiondevuelos.Domain.DTO;

import com.udea.gestiondevuelos.Domain.Enums.AircraftModel;

public class AircraftDTO {
    private Long id;
    private AircraftModel aircraftModel;
    private Integer maxSeats;
    private String seatConfiguration;

    public AircraftDTO() {
    }

    public AircraftDTO(Long id, AircraftModel aircraftModel, Integer maxSeats, String seatConfiguration) {
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

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getSeatConfiguration() {
        return seatConfiguration;
    }

    public void setSeatConfiguration(String seatConfiguration) {
        this.seatConfiguration = seatConfiguration;
    }
}
