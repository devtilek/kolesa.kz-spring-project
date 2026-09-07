package practice.kolesokz.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.services.CountryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable Long id) {
        return countryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Country> create(@Valid @RequestBody Country country) {
        Country saved = countryService.create(country);
        return ResponseEntity.created(URI.create("/api/countries/" + saved.getId())).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
