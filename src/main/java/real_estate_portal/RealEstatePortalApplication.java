package real_estate_portal;

import real_estate_portal.model.Property;
import real_estate_portal.repository.PropertyFileRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RealEstatePortalApplication {

    public static void main(String[] args) {

        SpringApplication.run(RealEstatePortalApplication.class, args);

        PropertyFileRepository repository = new PropertyFileRepository();

        List<Property> properties = repository.getAllProperties();

        for (Property property : properties) {
            System.out.println(property.getId() + " - " + property.getTitle());
        }
    }

}