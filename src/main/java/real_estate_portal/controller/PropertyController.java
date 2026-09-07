package real_estate_portal.controller;

import real_estate_portal.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertyController {

    private PropertyService service;

    public PropertyController() {
        service = new PropertyService();
    }

    @GetMapping("/properties")
    public String showProperties(Model model) {
        model.addAttribute("properties", service.getAllProperties());

        return "properties";
    }
}