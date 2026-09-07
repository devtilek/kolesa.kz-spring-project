package practice.kolesokz.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.dto.CarRequest;
import practice.kolesokz.dto.CarResponse;
import practice.kolesokz.mapper.CarMapper;
import practice.kolesokz.services.CarService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarRestController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping
    public List<CarResponse> findAll() {
        return carService.findAll().stream()
                .map(carMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CarResponse findById(@PathVariable Long id) {
        return carMapper.toResponse(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CarResponse> create(@Valid @RequestBody CarRequest request) {
        Car car = carMapper.toEntity(request);
        Car saved = carService.create(car, request.countryId());
        return ResponseEntity.created(URI.create("/api/cars/" + saved.getId()))
                .body(carMapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public CarResponse update(@PathVariable Long id, @Valid @RequestBody CarRequest request) {
        Car car = carMapper.toEntity(request);
        Car updated = carService.update(id, car, request.countryId());
        return carMapper.toResponse(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
