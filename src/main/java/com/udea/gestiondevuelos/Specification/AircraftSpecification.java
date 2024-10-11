package com.udea.gestiondevuelos.Specification;

import com.udea.gestiondevuelos.Domain.Enums.AircraftModel;
import com.udea.gestiondevuelos.Domain.Enums.SeatConfiguration;
import com.udea.gestiondevuelos.Domain.model.Aircraft;
import org.springframework.data.jpa.domain.Specification;

public class AircraftSpecification {
    public static Specification<Aircraft> filterByModel(AircraftModel model) {
        return (root, query, cb) -> {
            if(model == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("model"), model);
        };
    }

    public static Specification<Aircraft> filterByMaxSeats(Integer maxSeats) {
        return (root, query, cb) -> {
            if(maxSeats == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("maxSeats"), maxSeats);
        };
    }

    public static Specification<Aircraft> filterBySeatConfiguration(SeatConfiguration seatConfiguration) {
        return (root, query, cb) -> {
            if(seatConfiguration == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("seatConfiguration"), seatConfiguration);
        };
    }
}
