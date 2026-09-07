package practice.kolesokz.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.dto.CountryRequest;
import practice.kolesokz.dto.CountryResponse;
import practice.kolesokz.mapper.CountryMapper;
import practice.kolesokz.services.CountryService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryRestController {

    private final CountryService countryService;
    private final CountryMapper countryMapper;

    @GetMapping
    public List<CountryResponse> findAll() {
        return countryService.findAll().stream()
                .map(countryMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CountryResponse findById(@PathVariable Long id) {
        return countryMapper.toResponse(countryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CountryResponse> create(@Valid @RequestBody CountryRequest request) {
        Country saved = countryService.create(countryMapper.toEntity(request));
        return ResponseEntity.created(URI.create("/api/countries/" + saved.getId()))
                .body(countryMapper.toResponse(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
