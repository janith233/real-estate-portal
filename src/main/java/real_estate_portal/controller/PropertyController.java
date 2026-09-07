package real_estate_portal.controller;

import real_estate_portal.model.Property;
import real_estate_portal.service.PropertyService;
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
            Model model) {

        if (keyword.isEmpty()) {
            model.addAttribute("properties", service.getAllProperties());
        } else {
            model.addAttribute("properties", service.searchProperties(keyword));
        }

        model.addAttribute("keyword", keyword);

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
            @RequestParam String description) {

        Property property = new Property(
                0,
                title,
                location,
                price,
                propertyType,
                description
        );

        service.addProperty(property);

        return "redirect:/properties";
    }

    @GetMapping("/properties/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {

        Property property = service.getPropertyById(id);

        model.addAttribute("property", property);

        return "property-edit";
    }

    @PostMapping("/properties/update")
    public String updateProperty(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam String location,
            @RequestParam double price,
            @RequestParam String propertyType,
            @RequestParam String description) {

        Property property = new Property(
                id,
                title,
                location,
                price,
                propertyType,
                description
        );

        service.updateProperty(property);

        return "redirect:/properties";
    }

    @PostMapping("/properties/delete")
    public String deleteProperty(@RequestParam int id) {

        service.deleteProperty(id);

        return "redirect:/properties";
    }
}