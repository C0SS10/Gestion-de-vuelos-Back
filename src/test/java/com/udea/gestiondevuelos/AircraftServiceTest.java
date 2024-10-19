package com.udea.gestiondevuelos;

import com.udea.gestiondevuelos.domain.dto.AircraftDTO;
import com.udea.gestiondevuelos.domain.enums.AircraftModel;
import com.udea.gestiondevuelos.domain.enums.SeatConfiguration;
import com.udea.gestiondevuelos.service.IAircraftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AircraftServiceTest {
  @Mock
  private IAircraftService aircraftService;

  @InjectMocks
  private AircraftServiceTest aircraftServiceTest;

  private AircraftDTO validAircraftDTO;
  private AircraftDTO invalidAircraftDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    // Arrange: Se crea un objeto de tipo AircraftDTO con los datos de prueba
    validAircraftDTO = new AircraftDTO();
    validAircraftDTO.setId(1L);
    validAircraftDTO.setAircraftModel(AircraftModel.A330);
    validAircraftDTO.setMaxSeats(300);
    validAircraftDTO.setSeatConfiguration(SeatConfiguration.THREE_THREE_THREE);

    // Arrange: Se crea un objeto de tipo AircraftDTO con datos incorrectos (caso
    // fallido)
    invalidAircraftDTO = new AircraftDTO();
    invalidAircraftDTO.setAircraftModel(null); // Modelo nulo
    invalidAircraftDTO.setMaxSeats(-10); // Número de asientos inválido
  }

  @Test
  void testCreateAircraft() {
    // Este método se encarga de simular el comportamiento del método createAircraft
    // de la clase AircraftService
    when(aircraftService.createAircraft(validAircraftDTO)).thenReturn(validAircraftDTO);

    // Act: Se llama al método createAircraft de la clase AircraftService
    AircraftDTO createdAircraft = aircraftService.createAircraft(validAircraftDTO);

    // Assert: Se verifica el resultado del método createAircraft de la clase
    // AircraftService
    assertEquals(validAircraftDTO, createdAircraft);
  }

  @Test
  void testCreateAircraftInvalidData() {
    // Arrange: Simulación de error al intentar crear un avión con datos incorrectos
    when(aircraftService.createAircraft(any(AircraftDTO.class)))
        .thenThrow(new IllegalArgumentException("Invalid aircraft data"));

    // Act & Assert: Verifica que se lanza una excepción cuando se pasan datos
    // incorrectos
    assertThrows(IllegalArgumentException.class, () -> {
      aircraftService.createAircraft(invalidAircraftDTO);
    });

    // Verifica que se intentó crear el avión con datos inválidos
    verify(aircraftService, times(1)).createAircraft(invalidAircraftDTO);
  }
}
