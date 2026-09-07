package real_estate_portal.service;

import real_estate_portal.model.Property;
import real_estate_portal.repository.PropertyFileRepository;

import java.util.List;
import java.util.ArrayList;

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

    public List<Property> filterByType(String propertyType) {
        List<Property> properties = repository.getAllProperties();
        List<Property> results = new ArrayList<>();

        for (Property property : properties) {

            if (property.getPropertyType().equalsIgnoreCase(propertyType)) {
                results.add(property);
            }
        }

        return results;
    }

    public List<Property> searchAndFilter(String keyword, String propertyType) {
        List<Property> properties = repository.getAllProperties();
        List<Property> results = new ArrayList<>();

        for (Property property : properties) {

            boolean matchesKeyword = property.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || property.getLocation().toLowerCase().contains(keyword.toLowerCase())
                    || property.getPropertyType().toLowerCase().contains(keyword.toLowerCase());

            boolean matchesType = property.getPropertyType().equalsIgnoreCase(propertyType);

            if (matchesKeyword && matchesType) {
                results.add(property);
            }
        }

        return results;
    }

}