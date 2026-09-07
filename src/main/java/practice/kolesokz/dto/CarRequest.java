package practice.kolesokz.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import practice.kolesokz.Entities.CarStatus;
import practice.kolesokz.Entities.FuelType;
import practice.kolesokz.Entities.Transmission;

import java.math.BigDecimal;

public record CarRequest(
        @NotBlank @Size(max = 150) String name,
        @NotNull @DecimalMin("0.01") BigDecimal price,
        @NotNull @Min(1950) @Max(2100) Integer year,
        @NotNull @Min(0) Integer mileage,
        @NotNull FuelType fuelType,
        @NotNull Transmission transmission,
        @NotBlank @Size(max = 100) String city,
        CarStatus status,
        @Size(max = 1000) String url,
        @Size(max = 5000) String description,
        @NotNull Long countryId
) {}
