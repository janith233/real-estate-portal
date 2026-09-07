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

    public void addProperty(Property property) {

        List<Property> properties = repository.getAllProperties();

        int nextId = 1;

        for (Property existingProperty : properties) {
            if (existingProperty.getId() >= nextId) {
                nextId = existingProperty.getId() + 1;
            }
        }

        property.setId(nextId);

        repository.addProperty(property);
    }

    public void updateProperty(Property property) {
        repository.updateProperty(property);
    }

    public void deleteProperty(int id) {
        repository.deleteProperty(id);
    }

    public List<Property> searchProperties(String keyword) {
        return repository.searchProperties(keyword);
    }
}