package real_estate_portal.service;

import real_estate_portal.model.Property;
import real_estate_portal.repository.PropertyFileRepository;

import java.util.List;

public class PropertyService {

    private PropertyFileRepository repository;

    public PropertyService() {
        repository = new PropertyFileRepository();
    }

    public List<Property> getAllProperties() {
        return repository.getAllProperties();
    }

    public Property getPropertyById(int id) {
    List<Property> properties = repository.getAllProperties();

    for (Property property : properties) {
        if (property.getId() == id) {
            return property;
        }
    }

    return null;
}
}