package practice.kolesokz.dto;

import practice.kolesokz.Entities.CarStatus;
import practice.kolesokz.Entities.FuelType;
import practice.kolesokz.Entities.Transmission;

import java.math.BigDecimal;
import java.time.Instant;

public record CarResponse(
        Long id,
        String name,
        BigDecimal price,
        Integer year,
        Integer mileage,
        FuelType fuelType,
        Transmission transmission,
        String city,
        CarStatus status,
        String url,
        String description,
        Long countryId,
        String countryName,
        Instant createdAt,
        Instant updatedAt
) {}
