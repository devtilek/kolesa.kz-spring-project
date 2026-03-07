package practice.kolesokz.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practice.kolesokz.Entities.Car;
import practice.kolesokz.Entities.Country;
import practice.kolesokz.db.CarRepo;
import practice.kolesokz.db.CountryRepo;

@Controller
public class MainController {

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CarRepo carRepo;

    @GetMapping(value = "/")
    public String index(
            Model model
    ) {
        model.addAttribute("cars", carRepo.findAll());
        return "index";
    }

    @GetMapping(value = "/addCountry")
    public String addCountry() {
        return "addCountry";
    }

    @PostMapping(value = "/addCountry")
    public String addCountryPost(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "url") String url
    ) {
        Country country = new Country(null, name, url);
        countryRepo.save(country);
        return "redirect:/";
    }

    @GetMapping(value = "/addCar")
    public String addCarGet(Model model){
        model.addAttribute("countries", countryRepo.findAll());
        return "addCar";
    }

    @PostMapping(value = "/addCar")
    public String addCarPost(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") double price,
            @RequestParam(name = "desc") String description,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "country_id") Long countryId
    ){
        Country country = countryRepo.findById(countryId).orElse(null);
        Car car = new Car(null, name, price, url, description, country);
        carRepo.save(car);
        return "redirect:/";
    }

    @GetMapping(value = "/update/{id}")
    public String updateCarGet(
            @PathVariable(name = "id") Long id,
            Model model
    ){
        model.addAttribute("car", carRepo.findById(id).orElse(null));
        model.addAttribute("countries", countryRepo.findAll());
        return "updateCar";
    }

    @PostMapping(value = "/update")
    public String updateCarPost(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") double price,
            @RequestParam(name = "desc") String desc,
            @RequestParam(name = "country_id") Long countryId,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "car_id") Long id
    ){
        Country country = countryRepo.findById(countryId).orElse(null);
        Car car = new Car(id, name, price, url, desc, country);
        carRepo.save(car);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteCar(
            @RequestParam(name = "car_id") Long id
    ){
        carRepo.deleteById(id);
        return "redirect:/";
    }
}
