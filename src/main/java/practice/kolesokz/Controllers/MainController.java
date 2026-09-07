package practice.kolesokz.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.services.CarService;
import practice.kolesokz.services.CountryService;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CountryService countryService;
    private final CarService carService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cars", carService.findAll());
        return "index";
    }

    @GetMapping("/addCountry")
    public String addCountry() {
        return "addCountry";
    }

    @PostMapping("/addCountry")
    public String addCountryPost(@RequestParam String name, @RequestParam String url) {
        countryService.create(new Country(null, name, url));
        return "redirect:/";
    }

    @GetMapping("/addCar")
    public String addCarGet(Model model) {
        model.addAttribute("countries", countryService.findAll());
        return "addCar";
    }

    @PostMapping("/addCar")
    public String addCarPost(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam(name = "desc") String description,
            @RequestParam String url,
            @RequestParam(name = "country_id") Long countryId) {
        carService.create(new Car(null, name, price, url, description, null), countryId);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateCarGet(@PathVariable Long id, Model model) {
        model.addAttribute("car", carService.findById(id));
        model.addAttribute("countries", countryService.findAll());
        return "updateCar";
    }

    @PostMapping("/update")
    public String updateCarPost(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam(name = "desc") String description,
            @RequestParam(name = "country_id") Long countryId,
            @RequestParam String url,
            @RequestParam(name = "car_id") Long id) {
        carService.update(id, new Car(id, name, price, url, description, null), countryId);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam(name = "car_id") Long id) {
        carService.delete(id);
        return "redirect:/";
    }
}
