package practice.kolesokz.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.services.CarService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarRestController {

    private final CarService carService;

    @GetMapping
    public List<Car> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody Car car, @RequestParam Long countryId) {
        Car saved = carService.create(car, countryId);
        return ResponseEntity.created(URI.create("/api/cars/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @Valid @RequestBody Car car, @RequestParam Long countryId) {
        return carService.update(id, car, countryId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
