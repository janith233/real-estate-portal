package real_estate_portal.controller;


import real_estate_portal.model.Apartment;
import real_estate_portal.model.House;
import real_estate_portal.model.Property;
import real_estate_portal.service.PropertyService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PropertyController {

    private PropertyService service;

    public PropertyController() {
        service = new PropertyService();
    }

    @GetMapping("/properties")
    public String showProperties(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "") String propertyType,
            @RequestParam(required = false, defaultValue = "") String minPrice,
            @RequestParam(required = false, defaultValue = "") String maxPrice,
            @RequestParam(required = false, defaultValue = "") String sortBy,
            Model model) {

        List<Property> properties = service.filterProperties(
                keyword,
                propertyType,
                minPrice,
                maxPrice);

        properties = service.sortProperties(properties, sortBy);

        model.addAttribute("properties", properties);
        model.addAttribute("keyword", keyword);
        model.addAttribute("propertyType", propertyType);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sortBy", sortBy);

        return "properties";
    }

    @GetMapping("/properties/{id}")
    public String showPropertyDetails(@PathVariable int id, Model model) {

        Property property = service.getPropertyById(id);

        model.addAttribute("property", property);

        return "property-details";
    }

    @PostMapping("/properties/add")
    public String addProperty(
            @RequestParam String title,
            @RequestParam String location,
            @RequestParam double price,
            @RequestParam String propertyType,
            @RequestParam int extraValue,
            @RequestParam String description) {

        Property property;

        if (propertyType.equals("House")) {

            property = new House(
                    0,
                    title,
                    location,
                    price,
                    description,
                    extraValue);

        } else if (propertyType.equals("Apartment")) {

            property = new Apartment(
                    0,
                    title,
                    location,
                    price,
                    description,
                    extraValue);

        } else {

            property = new Property(
                    0,
                    title,
                    location,
                    price,
                    propertyType,
                    description);
        }

        service.addProperty(property);

        return "redirect:/properties";
    }

    @GetMapping("/properties/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {

        Property property = service.getPropertyById(id);

        int extraValue = 0;

        if (property instanceof House) {

            House house = (House) property;
            extraValue = house.getNumberOfBedrooms();

        } else if (property instanceof Apartment) {

            Apartment apartment = (Apartment) property;
            extraValue = apartment.getFloorNumber();
        }

        model.addAttribute("property", property);
        model.addAttribute("extraValue", extraValue);

        return "property-edit";
    }

    @PostMapping("/properties/update")
    public String updateProperty(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String location,
            @RequestParam double price,
            @RequestParam String propertyType,
            @RequestParam int extraValue,
            @RequestParam String description) {

        Property property;

        if (propertyType.equals("House")) {

            property = new House(
                    id,
                    title,
                    location,
                    price,
                    description,
                    extraValue);

        } else if (propertyType.equals("Apartment")) {

            property = new Apartment(
                    id,
                    title,
                    location,
                    price,
                    description,
                    extraValue);

        } else {

            property = new Property(
                    id,
                    title,
                    location,
                    price,
                    propertyType,
                    description);
        }

        service.updateProperty(property);

        return "redirect:/properties";
    }

    @PostMapping("/properties/delete")
    public String deleteProperty(@RequestParam int id) {

        service.deleteProperty(id);

        return "redirect:/properties";
    }

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }
}