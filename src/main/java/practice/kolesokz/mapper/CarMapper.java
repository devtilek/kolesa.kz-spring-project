package practice.kolesokz.mapper;

import org.springframework.stereotype.Component;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.CarStatus;
import practice.kolesokz.dto.CarRequest;
import practice.kolesokz.dto.CarResponse;

@Component
public class CarMapper {

    public Car toEntity(CarRequest request) {
        Car car = new Car();
        apply(car, request);
        return car;
    }

    public void apply(Car car, CarRequest request) {
        car.setName(request.name());
        car.setPrice(request.price());
        car.setYear(request.year());
        car.setMileage(request.mileage());
        car.setFuelType(request.fuelType());
        car.setTransmission(request.transmission());
        car.setCity(request.city());
        car.setStatus(request.status() == null ? CarStatus.ACTIVE : request.status());
        car.setUrl(request.url());
        car.setDescription(request.description());
    }

    public CarResponse toResponse(Car car) {
        return new CarResponse(
                car.getId(),
                car.getName(),
                car.getPrice(),
                car.getYear(),
                car.getMileage(),
                car.getFuelType(),
                car.getTransmission(),
                car.getCity(),
                car.getStatus(),
                car.getUrl(),
                car.getDescription(),
                car.getCountry().getId(),
                car.getCountry().getName(),
                car.getCreatedAt(),
                car.getUpdatedAt()
        );
    }
}
