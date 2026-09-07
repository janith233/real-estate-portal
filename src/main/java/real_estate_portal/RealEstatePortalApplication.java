package real_estate_portal;

import real_estate_portal.model.Property;
import real_estate_portal.service.PropertyService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RealEstatePortalApplication {

    public static void main(String[] args) {

        SpringApplication.run(RealEstatePortalApplication.class, args);

        PropertyService service = new PropertyService();

        List<Property> properties = service.getAllProperties();

        for (Property property : properties) {
            System.out.println(property.getId() + " - " + property.getTitle());
        }
    }
}